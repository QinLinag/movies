package com.cqupt.movies.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.Query;
import com.cqupt.movies.member.exception.PhoneExistException;
import com.cqupt.movies.member.exception.UsernameExistException;
import com.cqupt.movies.member.service.MemberService;
import com.cqupt.movies.member.vo.MemberLoginVo;
import com.cqupt.movies.member.vo.MemberRegistVo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqupt.movies.member.dao.Dao;
import com.cqupt.movies.member.entity.Entity;


@Service("Service")
public class MemberServiceImpl extends ServiceImpl<Dao, Entity> implements MemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Entity> page = this.page(
                new Query<Entity>().getPage(params),
                new QueryWrapper<Entity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void regist(MemberRegistVo vo) {
        Entity entity = new Entity();

        //设置用户名和手机号，查看是否早已有人使用过，如果有就抛出异常
        checkPhoneUnique(vo.getPhone());
        entity.setPhone(vo.getPhone());

        checkUserNameUnique(vo.getUserName());
        entity.setName(vo.getUserName());


        //密码进行加密存储
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(vo.getPassword());
        entity.setPassword(encode);

        //其他信息全部设置为null，用户自己以后完善
        entity.setAge(null);
        entity.setDate(null);
        entity.setGender(null);
        this.baseMapper.insert(entity);
    }

    @Override
    public void checkUserNameUnique(String username) throws UsernameExistException {
        Long count = this.baseMapper.selectCount(new QueryWrapper<Entity>().eq("name", username));
        if (count>0){
            throw new UsernameExistException();
        }
    }

    @Override
    public void checkPhoneUnique(String phone) throws PhoneExistException {
        Long count = this.baseMapper.selectCount(new QueryWrapper<Entity>().eq("phone", phone));
        if (count>0){
            throw new PhoneExistException();
        }
    }

    @Override
    public Entity login(MemberLoginVo vo) {
        String password = vo.getPassword();
        String loginacct=vo.getLoginacct();
        String phone = vo.getPhone();

        //1.查询数据库 用手机或用户名作为账号
        Entity entity = this.baseMapper.selectOne(new QueryWrapper<Entity>().eq("username", loginacct).or().eq("phone", phone));
        if (null==entity) {
            //登入失败
            return null;
        }else {
            //1.获取数据库的password，加密的
            String passwordDb = entity.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //2.密码匹配
            boolean matches = passwordEncoder.matches(password, passwordDb);
            if (matches==true){
                return entity;
            }else {
                return null;
            }
        }
    }


















}