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

import com.cqupt.movies.member.dao.WatchedMovieDao;
import com.cqupt.movies.member.entity.WatchedMovieEntity;
import com.cqupt.movies.member.service.WatchedMovieService;


@Service("watchedMovieService")
public class WatchedMovieServiceImpl extends ServiceImpl<WatchedMovieDao, WatchedMovieEntity> implements WatchedMovieService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WatchedMovieEntity> page = this.page(
                new Query<WatchedMovieEntity>().getPage(params),
                new QueryWrapper<WatchedMovieEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public WatchedMovieEntity getByMemberIdAndMovieId(InfoMovieVo infoMovieVo) {
        WatchedMovieEntity watchedMovieEntity = this.baseMapper.selectOne(new QueryWrapper<WatchedMovieEntity>().eq("member_id", infoMovieVo.getMemberId()).eq("movie_id", infoMovieVo.getMovieId()));
        return watchedMovieEntity;
    }

    @Override
    public R deleteByMemberIdAndMovieId(InfoMovieVo infoMovieVo) {
        int delete = this.baseMapper.delete(new QueryWrapper<WatchedMovieEntity>().eq("member_id", infoMovieVo.getMemberId()).eq("movie_id", infoMovieVo.getMovieId()));
        if (delete==1){
         return R.ok();
        }else {
         return R.error(1,"删除失败");
        }
    }

}