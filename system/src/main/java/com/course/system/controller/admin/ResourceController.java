/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: ResourceController
 * Author:   111
 * Date:     2021/3/3 13:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.system.controller.admin;/**
 * Created by 111 on 2021/3/3.
 */

import com.course.server.dto.PageDto;
import com.course.server.dto.ResourceDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ResourceService;
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
@RestController
@RequestMapping("/admin/resource")
public class ResourceController {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceController.class);
    public static final String BUSINESS_NAME = "资源";
    @Resource
    private ResourceService resourceService;

    /**
     *  列表查询
     * @param pageDto
     * @return responseDto
     */
    @PostMapping("/list") // 只支持post请求
    public ResponseDto list(@RequestBody PageDto pageDto) {//@RequestBody接收表单的形式，不加接收的是json流的方式
        ResponseDto responseDto = new ResponseDto();
        resourceService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值是新增
     * @param resourceDto
     * @return responseDto
     */
//    @RequestMapping("/save")
    @PostMapping("/save")
    public ResponseDto save(@RequestBody String jsonStr) {

        // 保存校验
        ValidatorUtil.require(jsonStr, "资源 ");

        ResponseDto responseDto = new ResponseDto();
        resourceService.saveJson(jsonStr);
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
        resourceService.delete(id);
        return responseDto;
    }

    /**
     * 资源树查询
     */
    @GetMapping("/load-tree")
    public ResponseDto loadTree() {
        ResponseDto responseDto = new ResponseDto();
        List<ResourceDto> resourceDtoList = resourceService.loadTree();
        responseDto.setContent(resourceDtoList);
        return responseDto;
    }
}
