package com.cqupt.movies.movies.feign;


import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.movies.vo.InfoMovieVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("movies-member")
public interface MemberFeignService {

    @PostMapping("/member/grademovie/memberidandmovieid")
    R selectGradeByMemberIdAndMovieId(@RequestBody InfoMovieVo infoMovieVo);

    @RequestMapping("/member/grademovie/save")
    R saveGrade(@RequestBody InfoMovieVo infoMovieVo);





    @PostMapping("/member/thumbmovie/thumb/memberidandmovieid")
    R selectThumbByMemberIdAndMovieId(@RequestBody InfoMovieVo infoMovieVo);

    @RequestMapping("/member/thumbmovie/save")
    R saveThumbInfo(@RequestBody InfoMovieVo infoMovieVo);

    @RequestMapping("/member/thumbmovie/delete")
    R deleteThumbInfo(@RequestBody InfoMovieVo infoMovieVo);





    @PostMapping("/member/watchedmovie/watched/memberidandmovieid")
    R selectWatchedByMemberIdAndMovieId(@RequestBody InfoMovieVo infoMovieVo);

    @RequestMapping("/member/watchedmovie/save")
    R saveWatchedInfo(@RequestBody InfoMovieVo infoMovieVo);

    @RequestMapping("/member/watchedmovie/delete")
    R deleteWatchedInfo(@RequestBody InfoMovieVo infoMovieVo);




    @PostMapping("/member/keenmovie/keen/memberidandmovieid")
    R selectKeenByMemberIdAndMovieId(@RequestBody InfoMovieVo infoMovieVo);

    @RequestMapping("/member/keenmovie/save")
    R saveKeenInfo(@RequestBody InfoMovieVo infoMovieVo);

    @RequestMapping("/member/keenmovie/delete")
    R deleteKeenInfo(@RequestBody InfoMovieVo infoMovieVo);



    @GetMapping("/member/badmovie/bad/memberidandmovieid")
    R selectBadByMemberIdAndMovieId(@RequestBody InfoMovieVo infoMovieVo);

    @RequestMapping("/member/badmovie/save")
    R saveBadInfo(@RequestBody InfoMovieVo infoMovieVo);

    @RequestMapping("/member/badmovie/delete")
    R deleteBadInfo(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo);

}

