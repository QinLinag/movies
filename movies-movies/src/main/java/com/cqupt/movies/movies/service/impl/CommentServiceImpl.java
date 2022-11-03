package com.cqupt.movies.movies.service.impl;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.Query;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.movies.entity.CommentReplyEntity;
import com.cqupt.movies.movies.service.CommentReplyService;
import com.cqupt.movies.movies.vo.CommentUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.cqupt.movies.movies.dao.CommentDao;
import com.cqupt.movies.movies.entity.CommentEntity;
import com.cqupt.movies.movies.service.CommentService;


@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentDao, CommentEntity> implements CommentService {

    @Autowired
    CommentReplyService commentReplyService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommentEntity> page = this.page(
                new Query<CommentEntity>().getPage(params),
                new QueryWrapper<CommentEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R deleteCommentByCommentId(Long id) {
        //先查出着条评论
        CommentEntity commentDb = this.baseMapper.selectById(id);
        if (commentDb!=null){
            //先删除该评论的所有回复信息，
            //查出所有回复
            List<CommentReplyEntity> commentReplyEntities= commentReplyService.listByCommentId(id);
            if (commentReplyEntities!=null&&commentReplyEntities.size()>0) {  //回复不为空，
                List<Long> commentReplyIds = commentReplyEntities.stream().map(item -> {
                    return item.getId();
                }).collect(Collectors.toList());

                //删除所有回复
                // commentReplyService.removeBatchByIds(commentReplyIds);
            }
            //删除改评论
            this.baseMapper.deleteById(id);
            return R.ok();
        }else {
            return R.error(1,"评论不存在");
        }

    }

    @Override
    public R updateCommentPraise(CommentUpdateVo commentUpdateVo) {
        //先查询数据库是否有这条评论
        CommentEntity commentEntityDb = this.baseMapper.selectById(commentUpdateVo.getId());
        if (commentEntityDb!=null){
            //给点赞加一就好了
            commentEntityDb.setPraseCount(commentEntityDb.getPraseCount()+1);
            this.baseMapper.insert(commentEntityDb);
            return R.ok();
        }else {
            return R.error(1,"数据库中没有");
        }
    }

}