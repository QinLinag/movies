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

import com.cqupt.movies.member.dao.BadMovieDao;
import com.cqupt.movies.member.entity.BadMovieEntity;
import com.cqupt.movies.member.service.BadMovieService;


@Service("badMovieService")
public class BadMovieServiceImpl extends ServiceImpl<BadMovieDao, BadMovieEntity> implements BadMovieService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BadMovieEntity> page = this.page(
                new Query<BadMovieEntity>().getPage(params),
                new QueryWrapper<BadMovieEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public BadMovieEntity getByMemberIdAndMovieId(InfoMovieVo infoMovieVo) {
        BadMovieEntity badMovieEntity = this.baseMapper.selectOne(new QueryWrapper<BadMovieEntity>().eq("member_id", infoMovieVo.getMemberId()).eq("movie_id", infoMovieVo.getMovieId()));
        return badMovieEntity;

    }

    @Override
    public R deleteByMemberIdAndMovieId(InfoMovieVo infoMovieVo) {
        int delete = this.baseMapper.delete(new QueryWrapper<BadMovieEntity>().eq("member_id", infoMovieVo.getMemberId()).eq("movie_id", infoMovieVo.getMovieId()));
        if (delete==1){
            return R.ok();
        }else {
            return R.error(1,"删除失败");
        }
    }

}