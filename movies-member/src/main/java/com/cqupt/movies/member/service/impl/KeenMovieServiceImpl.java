package com.cqupt.movies.member.service.impl;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.Query;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.member.vo.InfoMovieVo;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cqupt.movies.member.dao.KeenMovieDao;
import com.cqupt.movies.member.entity.KeenMovieEntity;
import com.cqupt.movies.member.service.KeenMovieService;


@Service("keenMovieService")
public class KeenMovieServiceImpl extends ServiceImpl<KeenMovieDao, KeenMovieEntity> implements KeenMovieService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<KeenMovieEntity> page = this.page(
                new Query<KeenMovieEntity>().getPage(params),
                new QueryWrapper<KeenMovieEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public KeenMovieEntity getByMemberIdAndMovieId(InfoMovieVo infoMovieVo) {
        KeenMovieEntity keenMovieEntity = this.baseMapper.selectOne(new QueryWrapper<KeenMovieEntity>().eq("member_id", infoMovieVo.getMemberId()));
        return keenMovieEntity;
    }

    @Override
    public R deleteByMemberIdAndMovieId(InfoMovieVo infoMovieVo) {
        int delete = this.baseMapper.delete(new QueryWrapper<KeenMovieEntity>().eq("member_id", infoMovieVo.getMemberId()).eq("movie_id", infoMovieVo.getMovieId()));
        if (delete==1){
            return R.ok();
        }else {
            return R.error(1,"删除失败");
        }
    }

}