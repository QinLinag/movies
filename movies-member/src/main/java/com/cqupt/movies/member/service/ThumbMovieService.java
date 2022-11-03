package com.cqupt.movies.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.member.entity.CollectMovieEntity;
import com.cqupt.movies.member.entity.ThumbMovieEntity;
import com.cqupt.movies.member.vo.InfoMovieVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-10-26 08:29:08
 */
public interface ThumbMovieService extends IService<ThumbMovieEntity> {

    PageUtils queryPage(Map<String, Object> params);


    //通过电影id和用户id查询用户点赞该电影的信息
    ThumbMovieEntity getByMemberIdAndMovieId(InfoMovieVo infoMovieVo);

    //删除一条用户点赞该电影的信息，
    R deleteByMemberIdAndMovieId(InfoMovieVo infoMovieVo);

    List<ThumbMovieEntity> getThumbMovieEntityByMemberId(Long memberId);
}

