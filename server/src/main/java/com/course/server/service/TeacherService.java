/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: TeacherService
 * Author:   111
 * Date:     2021/3/7 18:15
 * Description: 大章的service业务逻辑代码
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.server.service;

import com.course.server.domain.Teacher;
import com.course.server.domain.TeacherExample;
import com.course.server.dto.TeacherDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.TeacherMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
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
public class TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    // 前端传递过来的类再重新获取返回void关键字

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        TeacherExample teacherExample = new TeacherExample();
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherList);
        pageDto.setTotal(pageInfo.getTotal());
        List<TeacherDto> teacherDtoList = CopyUtil.copyList(teacherList, TeacherDto.class);
        pageDto.setList(teacherDtoList);
    }


    /**
     * 保存，有值时更新，无值是新增
     * @param teacherDto
     */
    public void save(TeacherDto teacherDto) {
        Teacher teacher = CopyUtil.copy(teacherDto, Teacher.class);
        if (StringUtils.isEmpty(teacher.getId())) {
            this.insert(teacher);
        } else {
            this.update(teacher);
        }
    }

    /**
     * 新增
     * @param teacher
     */
    private void insert(Teacher teacher) {

        teacher.setId(UuidUtil.getShortUuid());
        teacherMapper.insert(teacher);
    }

    /**
     * 更新
     * @param teacher
     */
    private void update(Teacher teacher) {
        teacherMapper.updateByPrimaryKey(teacher);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        teacherMapper.deleteByPrimaryKey(id);
    }
}
