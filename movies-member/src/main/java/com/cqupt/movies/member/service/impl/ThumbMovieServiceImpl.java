package com.cqupt.movies.member.service.impl;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.Query;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.member.entity.CollectMovieEntity;
import com.cqupt.movies.member.vo.InfoMovieVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cqupt.movies.member.dao.ThumbMovieDao;
import com.cqupt.movies.member.entity.ThumbMovieEntity;
import com.cqupt.movies.member.service.ThumbMovieService;


@Service("thumbMovieService")
public class ThumbMovieServiceImpl extends ServiceImpl<ThumbMovieDao, ThumbMovieEntity> implements ThumbMovieService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThumbMovieEntity> page = this.page(
                new Query<ThumbMovieEntity>().getPage(params),
                new QueryWrapper<ThumbMovieEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public ThumbMovieEntity getByMemberIdAndMovieId(InfoMovieVo infoMovieVo) {
        ThumbMovieEntity thumbMovieEntity = this.baseMapper.selectOne(new QueryWrapper<ThumbMovieEntity>().eq("member_id", infoMovieVo.getMemberId()).eq("movie_id", infoMovieVo.getMovieId()));
        return thumbMovieEntity;
    }

    @Override
    public R deleteByMemberIdAndMovieId(InfoMovieVo infoMovieVo) {
        int delete = this.baseMapper.delete(new QueryWrapper<ThumbMovieEntity>().eq("member_id", infoMovieVo.getMemberId()).eq("movie_id", infoMovieVo.getMovieId()));
        if (delete==1){
            return R.ok();
        }else {
            return R.error(1,"删除失败");
        }

    }

    @Override
    public List<ThumbMovieEntity> getThumbMovieEntityByMemberId(Long memberId) {
        List<ThumbMovieEntity> member_id = this.baseMapper.selectList(new QueryWrapper<ThumbMovieEntity>().eq("member_id", memberId));
        return member_id;
    }

}