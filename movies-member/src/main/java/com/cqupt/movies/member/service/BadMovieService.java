package com.cqupt.movies.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.member.entity.BadMovieEntity;
import com.cqupt.movies.member.vo.InfoMovieVo;

import java.util.Map;

/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-03 10:03:41
 */
public interface BadMovieService extends IService<BadMovieEntity> {

    PageUtils queryPage(Map<String, Object> params);

    BadMovieEntity getByMemberIdAndMovieId(InfoMovieVo infoMovieVo);

    R deleteByMemberIdAndMovieId(InfoMovieVo infoMovieVo);
}

