package com.cqupt.movies.movies.feign;


import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.movies.vo.InfoMovieVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("movies-member")
public interface MemberFeignService {

    @GetMapping("/member/thumbmovie/thumb/memberidandmovieid")
    R selectThumbByMemberIdAndMovieId(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo);


    @RequestMapping("/member/thumbmovie/save")
    R saveThumbInfo(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo);

    @RequestMapping("/member/thumbmovie/delete")
    R deleteThumbInfo(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo);





    @GetMapping("/member/watchedmovie/watched/memberidandmovieid")
    R selectWatchedByMemberIdAndMovieId(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo);

    @RequestMapping("/member/watchedmovie/save")
    R saveWatchedInfo(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo);

    @RequestMapping("/member/watchedmovie/delete")
    R deleteWatchedInfo(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo);




    @GetMapping("/member/keenmovie/keen/memberidandmovieid")
    R selectKeenByMemberIdAndMovieId(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo);

    @RequestMapping("/member/keenmovie/save")
    R saveKeenInfo(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo);

    @RequestMapping("/member/keenmovie/delete")
    R deleteKeenInfo(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo);



    @GetMapping("/member/badmovie/bad/memberidandmovieid")
    R selectBadByMemberIdAndMovieId(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo);

    @RequestMapping("/member/badmovie/save")
    R saveBadInfo(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo);

    @RequestMapping("/member/badmovie/delete")
    R deleteBadInfo(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo);

}

