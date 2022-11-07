package com.cqupt.movies.member.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyRabbitmqConfig {

    //配置发消息使用json方式进行消息转换
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }


//    @RabbitListener(queues = "memberinfo.release.memberinfo.queue")
//    public void handle(Message message){
//
//    }


    @Bean
    public Exchange MemberInfoEventExchange(){
        return new TopicExchange("memberinfo-event-exchange",true,false);
    }


    @Bean
    public Queue MemberInfoReleaseMemberInfoQueue(){
        return new Queue("memberinfo.release.memberinfo.queue",true,false,false);
    }

    @Bean
    public Queue MemberInfoDelayQueue(){
        //死信路由，
        Map<String,Object> arguments=new HashMap<>();

        arguments.put("x-dead-letter-exchange","memberinfo-event-exchange");//这个很重要，如果时间一到，就会通过这个交换机发出去
        arguments.put("x-dead-letter-routing-key","memberinfo.release");//以这个路由key发送到指定的队列，
        arguments.put("x-message-ttl",5000);
        return new Queue("memberinfo.delay.queue",true,false,false,arguments);
    }

    @Bean
    public Binding MemberInfoReleaseBinding(){
        return new Binding("memberinfo.release.memberinfo.queue",
                Binding.DestinationType.QUEUE,
                "memberinfo-event-exchange",
                "memberinfo.release",
                null);
    }

    @Bean
    public Binding MemberInfoCreateMemberInfoBinding(){
        return new Binding("memberinfo.delay.queue",
                Binding.DestinationType.QUEUE,
                "memberinfo-event-exchange",
                "memberinfo.delay.queue",
                null);
    }


}
