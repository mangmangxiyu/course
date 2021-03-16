/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: CategoryController
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

import com.course.server.dto.CategoryDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CategoryService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 111
 * @create 2021/3/3
 * @since 1.0.0
 */
@RequestMapping("/admin/category")
@RestController
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    public static final String BUSINESS_NAME = "分类";
    @Resource
    private CategoryService categoryService;

    /**
     *  列表查询
     * @param null
     * @return responseDto
     */
    @PostMapping("/all") // 只支持post请求
    public ResponseDto all() {//@RequestBody接收表单的形式，不加接收的是json流的方式
        ResponseDto responseDto = new ResponseDto();
        List<CategoryDto> categoryDtoList = categoryService.all();
        responseDto.setContent(categoryDtoList);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值是新增
     * @param categoryDto
     * @return responseDto
     */
//    @RequestMapping("/save")
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CategoryDto categoryDto) {

        // 保存的有效性校验
        ValidatorUtil.require(categoryDto.getParent(), "父id");
        ValidatorUtil.require(categoryDto.getName(), "名称");
        ValidatorUtil.length(categoryDto.getName(), "名称", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        categoryService.save(categoryDto);
        responseDto.setContent(categoryDto);
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
        categoryService.delete(id);
        return responseDto;
    }
}
