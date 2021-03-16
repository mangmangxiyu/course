/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: CourseService
 * Author:   111
 * Date:     2021/3/7 18:15
 * Description: 大章的service业务逻辑代码
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.server.service;

import com.course.server.domain.Course;
import com.course.server.domain.CourseExample;
import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseMapper;
import com.course.server.mapper.my.MyCourseMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈大章的service业务逻辑代码〉
 *
 * @author 111
 * @create 2021/3/7
 * @since 1.0.0
 */
@Service
public class CourseService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CourseService.class);

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private MyCourseMapper myCourseMapper;

    // 前端传递过来的类再重新获取返回void关键字

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseExample courseExample = new CourseExample();
        courseExample.setOrderByClause("sort asc");
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<>(courseList);
        pageDto.setTotal(pageInfo.getTotal());
        List<CourseDto> courseDtoList = CopyUtil.copyList(courseList, CourseDto.class);
        pageDto.setList(courseDtoList);
    }


    /**
     * 保存，有值时更新，无值是新增
     * @param courseDto
     */
    public void save(CourseDto courseDto) {
        Course course = CopyUtil.copy(courseDto, Course.class);
        if (StringUtils.isEmpty(course.getId())) {
            this.insert(course);
        } else {
            this.update(course);
        }
    }

    /**
     * 新增
     * @param course
     */
    private void insert(Course course) {
        Date now = new Date();
        course.setCreatedAt(now);
        course.setUpdatedAt(now);
        course.setId(UuidUtil.getShortUuid());
        courseMapper.insert(course);
    }

    /**
     * 更新
     * @param course
     */
    private void update(Course course) {
        course.setUpdatedAt(new Date());
        courseMapper.updateByPrimaryKey(course);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        courseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新时长
     * @param courseId
     */
    public void updateTime(String courseId) {
        LOG.info("更新课程时长：{}", courseId);
        myCourseMapper.updateTime(courseId);
    }
}
