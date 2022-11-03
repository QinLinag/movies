package com.cqupt.movies.movies.service.impl;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.Query;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.movies.vo.CommentReplyVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cqupt.movies.movies.dao.CommentReplyDao;
import com.cqupt.movies.movies.entity.CommentReplyEntity;
import com.cqupt.movies.movies.service.CommentReplyService;


@Service("commentReplyService")
public class CommentReplyServiceImpl extends ServiceImpl<CommentReplyDao, CommentReplyEntity> implements CommentReplyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommentReplyEntity> page = this.page(
                new Query<CommentReplyEntity>().getPage(params),
                new QueryWrapper<CommentReplyEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CommentReplyEntity> listByCommentId(Long id) {
        List<CommentReplyEntity> commentReplyEntities = this.baseMapper.selectList(new QueryWrapper<CommentReplyEntity>().eq("comment_id", id));
        return commentReplyEntities;

    }

    @Override
    public R saveCommentReply(CommentReplyVo commentReplyVo) {
        if (commentReplyVo.getContent()!=null) {
            CommentReplyEntity commentReplyEntity = new CommentReplyEntity();
            BeanUtils.copyProperties(commentReplyVo, commentReplyEntity);

            commentReplyEntity.setCreateTime(new Date());
            commentReplyEntity.setPraseCount(0);
            this.baseMapper.insert(commentReplyEntity);
            return R.ok();
        }else {
            return R.error(1,"内容为空");
        }
    }

    @Override
    public R removeCommentReply(CommentReplyVo commentReplyVo) {
        //先查询数据库中是否有该回复，
        CommentReplyEntity commentReplyEntity = this.baseMapper.selectById(commentReplyVo.getId());
        if (commentReplyEntity!=null){
            this.baseMapper.deleteById(commentReplyVo.getId());
            return R.ok();
        }else {
            return R.error(1,"该回复不存在");
        }

    }

}