package com.cqupt.movies.movies.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cqupt.movies.common.constant.MovieConstant;
import com.cqupt.movies.common.map.MovieTagsMap;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.Query;
import com.cqupt.movies.movies.interceptor.MovieInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cqupt.movies.movies.dao.MovieDao;
import com.cqupt.movies.movies.entity.MovieEntity;
import com.cqupt.movies.movies.service.MovieService;


@Service("movieService")
public class MovieServiceImpl extends ServiceImpl<MovieDao, MovieEntity> implements MovieService {


    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MovieEntity> page = this.page(
                new Query<MovieEntity>().getPage(params),
                new QueryWrapper<MovieEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<MovieEntity> listByName(String name) {
        List<MovieEntity> entities = this.getBaseMapper().selectList(new QueryWrapper<MovieEntity>().like("name", name));
        return entities;
    }

    @Override
    public List<MovieEntity> listAllMovies() {
        List<MovieEntity> entities = this.baseMapper.selectList(null);
        return entities;
    }

    @Override
    public List<MovieEntity> listByTags(List<Integer> tags) {
        System.out.println(tags.get(1));
        List<MovieEntity> entities = listAllMovies();  //先查出所有的电影
        //前端返回的tags的id转化为对应在数据库中的tag名字
        Map<Integer, String> map = MovieTagsMap.map;
        List<String> tagsString = tags.stream().map(tag -> {
            System.out.println(map.get(tag));
            return map.get(tag);   //对应映射
        }).collect(Collectors.toList());
        System.out.println(tagsString.toString());


        List<MovieEntity> collect = entities.stream().filter((entity) -> {   //根据tags进行过滤
            String[] strings = entity.getTags().split(",");    //分割
            for (String tag : tagsString) {
                for (String string : strings) {
                    if (tag==string) {
                        return true;
                    }
                }
            }
            return false;
        }).collect(Collectors.toList());

        //将查出来的电影保存在redis缓存中，
        String tagJsonMovies = JSONObject.toJSONString(collect);
        redisTemplate.opsForValue().set(MovieInterceptor.threadLocal.get().getUserKey()+ MovieConstant.TAG_NAME,tagJsonMovies);

        System.out.println(collect.toString());
        return collect;
    }

}