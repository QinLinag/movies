package com.cqupt.movies.movies.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyMQConfig {


//    @RabbitListener(queues = "movieinfo.release.movieinfo.queue")
//    public void handle(Message message){
//
//    }


    @Bean
    public Queue movieInfoDelayQueue(){
        //死信路由，
        Map<String,Object> arguments=new HashMap<>();

        arguments.put("x-dead-letter-exchange","memberinfo-event-exchange");//这个很重要，如果时间一到，就会通过这个交换机发出去
        arguments.put("x-dead-letter-routing-key","memberinfo.release.memberinfo");//以这个路由key发送到指定的队列，
        arguments.put("x-message-ttl",60000);
        Queue queue=new Queue("movieinfo.delay.queue",true,false,false,arguments);
        return queue;
    }


    @Bean
    public Queue movieInfoReleaseOrderQueue(){
        Queue queue = new Queue("movieinfo.release.movieinfo.queue", true, false, false);
        return queue;
    }

    @Bean
    public Exchange orderEventExchange(){
        return new TopicExchange("movieinfo-event-exchange",true,false);
    }


    @Bean
    public Binding movieInfoCreateMovieInfoBinding(){
        return new Binding("movieinfo.delay.queue",
                Binding.DestinationType.QUEUE,
                "movieinfo-event-exchange",
                "movieinfo.create.movieinfo",
                null);
    }

    @Bean
    public Binding orderReleaseMovieInfoBinding(){
        return new Binding("movieinfo.release.movieinfo.queue",
                Binding.DestinationType.QUEUE,
                "movieinfo-event-exchange",
                "movieinfo.release.movieinfo",
                null);
    }

}
