package com.cqupt.movies.member.listener;


import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.member.controller.to.CreateMemberInfoTo;
import com.cqupt.movies.member.entity.GradeMoviedEntity;
import com.cqupt.movies.member.feign.MovieSFeignService;
import com.cqupt.movies.member.service.GradeMoviedService;
import com.cqupt.movies.member.vo.InfoMoiveStatusVo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RabbitListener(queues = "memberinfo.release.memberinfo.queue")   //监听
@Service
public class CreateMovieInfoListener {

    @Autowired
    private MovieSFeignService movieSFeignService;

    @Autowired
    private GradeMoviedService gradeMoviedService;

    @RabbitHandler
    public void handleCreateMemberInfoRelease(CreateMemberInfoTo to, Message message){
        System.out.println("---------------------------------------");
        Long id = to.getId();
        if (id==null){//如果id是空，那么说明本身gradeMovieInfoEntity插入数据库就失败了，不用回滚，
            //本身gradeMovieInfoEntity插入数据库就失败，无需回滚
            System.out.println("22222222222222222222222222");
        }else {
            //gradeMovieInfoEntity插入数据库成功，  但是到底要不要回滚，还要看movies服务中，infoMovie修改是不是成功，查看status状态是否已完成
            R r = movieSFeignService.getById(to.getStatusEntityId());
            InfoMoiveStatusVo data = r.getData("data", new TypeReference<InfoMoiveStatusVo>() {
            });
            if (data==null||data.getStatus()==0) {   //movies服务中的状态信息都没有了，  或者状态显示未处理，回滚
                deleteMemberGradeInfo(to);   // 删除保存了的信息，
            }
        }
    }

    public void deleteMemberGradeInfo(CreateMemberInfoTo to){
        System.out.println("333333333333333333333333333333");
        gradeMoviedService.remove(new QueryWrapper<GradeMoviedEntity>().eq("memeber_id",to.getMemberId()).eq("movie_id",to.getMovieId()));
    }

}
