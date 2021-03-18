/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: TeacherController
 * Author:   111
 * Date:     2021/3/3 13:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.business.controller.admin;/**
 * Created by 111 on 2021/3/3.
 */

import com.course.server.dto.TeacherDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.TeacherService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 111
 * @create 2021/3/3
 * @since 1.0.0
 */
@RequestMapping("/admin/teacher")
@RestController
public class TeacherController {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherController.class);
    public static final String BUSINESS_NAME = "讲师";
    @Resource
    private TeacherService teacherService;

    /**
     *  列表查询
     * @param pageDto
     * @return responseDto
     */
    @PostMapping("/list") // 只支持post请求
    public ResponseDto list(@RequestBody PageDto pageDto) {//@RequestBody接收表单的形式，不加接收的是json流的方式
        ResponseDto responseDto = new ResponseDto();
        teacherService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值是新增
     * @param teacherDto
     * @return responseDto
     */
//    @RequestMapping("/save")
    @PostMapping("/save")
    public ResponseDto save(@RequestBody TeacherDto teacherDto) {

        // 保存的有效性校验
        ValidatorUtil.require(teacherDto.getName(), "姓名");
        ValidatorUtil.length(teacherDto.getName(), "姓名", 1, 50);
        ValidatorUtil.length(teacherDto.getNickname(), "昵称", 1, 50);
        ValidatorUtil.length(teacherDto.getImage(), "头像", 1, 100);
        ValidatorUtil.length(teacherDto.getPosition(), "职位", 1, 50);
        ValidatorUtil.length(teacherDto.getMotto(), "座右铭", 1, 50);
        ValidatorUtil.length(teacherDto.getIntro(), "简介", 1, 500);

        ResponseDto responseDto = new ResponseDto();
        teacherService.save(teacherDto);
        responseDto.setContent(teacherDto);
        return responseDto;
    }

    /**
     * 删除
     * @param id
     * @return responseDto
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        teacherService.delete(id);
        return responseDto;
    }
}
