/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: UserService
 * Author:   111
 * Date:     2021/3/7 18:15
 * Description: 大章的service业务逻辑代码
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.server.service;

import com.course.server.domain.User;
import com.course.server.domain.UserExample;
import com.course.server.dto.UserDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.UserMapper;
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
public class UserService {

    @Resource
    private UserMapper userMapper;

    // 前端传递过来的类再重新获取返回void关键字

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        UserExample userExample = new UserExample();
        List<User> userList = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        pageDto.setTotal(pageInfo.getTotal());
        List<UserDto> userDtoList = CopyUtil.copyList(userList, UserDto.class);
        pageDto.setList(userDtoList);
    }


    /**
     * 保存，有值时更新，无值是新增
     * @param userDto
     */
    public void save(UserDto userDto) {
        User user = CopyUtil.copy(userDto, User.class);
        if (StringUtils.isEmpty(user.getId())) {
            this.insert(user);
        } else {
            this.update(user);
        }
    }

    /**
     * 新增
     * @param user
     */
    private void insert(User user) {

        user.setId(UuidUtil.getShortUuid());
        userMapper.insert(user);
    }

    /**
     * 更新
     * @param user
     */
    private void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
