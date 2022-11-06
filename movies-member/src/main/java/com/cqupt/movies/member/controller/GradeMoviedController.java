package com.cqupt.movies.member.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cqupt.movies.member.entity.GradeMoviedEntity;
import com.cqupt.movies.member.service.GradeMoviedService;



/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-06 14:49:44
 */
@RestController
@RequestMapping("member/grademovied")
public class GradeMoviedController {
    @Autowired
    private GradeMoviedService gradeMoviedService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:grademovied:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = gradeMoviedService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:grademovied:info")
    public R info(@PathVariable("id") Long id){
		GradeMoviedEntity gradeMovied = gradeMoviedService.getById(id);

        return R.ok().put("gradeMovied", gradeMovied);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:grademovied:save")
    public R save(@RequestBody GradeMoviedEntity gradeMovied){
		gradeMoviedService.save(gradeMovied);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:grademovied:update")
    public R update(@RequestBody GradeMoviedEntity gradeMovied){
		gradeMoviedService.updateById(gradeMovied);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:grademovied:delete")
    public R delete(@RequestBody Long[] ids){
		gradeMoviedService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
