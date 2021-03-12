/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: ChapterController
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

import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
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
@RequestMapping("/admin/chapter")
@RestController
public class ChapterController {

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);

    @Resource
    private ChapterService chapterService;

    // 查询大章
//    @RequestMapping("/list")
    @PostMapping("/list") // 只支持post请求
    public ResponseDto list(@RequestBody PageDto pageDto) {//@RequestBody接收表单的形式，不加接收的是json流的方式
        LOG.info("pageDto: {}", pageDto);
        ResponseDto responseDto = new ResponseDto();
        chapterService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    // 新增大章
//    @RequestMapping("/save")
    @PostMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto) {
        LOG.info("chapterDto: {}", chapterDto);

        /*try {*//* 加try处理空值递交后页面一直loading的图标，必须刷新页面才能解决的bug。
            后端出异常导致前后收不到结果，vue中.then()没有执行 ，等待框没有关闭导致不能继续任何操作，只能刷新页面*//*
            // 保存前进行数据校验
            ValidatorUtil.require(chapterDto.getName(), "名称");
            ValidatorUtil.require(chapterDto.getCourseId(), "课程ID");
            ValidatorUtil.length(chapterDto.getCourseId(), "课程ID", 1, 8);
        } catch (ValidatorException e) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(false);
            responseDto.setMessage(e.getMessage());// 校验里抛出异常的文本

        }*/
        ValidatorUtil.require(chapterDto.getName(), "名称");
        ValidatorUtil.require(chapterDto.getCourseId(), "课程ID");
        ValidatorUtil.length(chapterDto.getCourseId(), "课程ID", 1, 8);

        ResponseDto responseDto = new ResponseDto();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }

    // 新增大章
//    @RequestMapping("/delete/{id}")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        LOG.info("id: {}", id);
        ResponseDto responseDto = new ResponseDto();
        chapterService.delete(id);
        return responseDto;
    }
}
