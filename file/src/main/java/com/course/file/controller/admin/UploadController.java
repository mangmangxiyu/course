/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: TestController
 * Author:   111
 * Date:     2021/3/3 13:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.file.controller.admin;/**
 * Created by 111 on 2021/3/3.
 */

import com.course.server.dto.FileDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.FileService;
import com.course.server.service.TestService;
import com.course.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 111
 * @create 2021/3/3
 * @since 1.0.0
 */

@RequestMapping("/admin")
@RestController
public class UploadController {

    @Value("${file.path}")
    private String FILE_PATH;

    @Value("${file.domain}")
    private String FILE_DOMAIN;
    
    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);
    public static final String BUSINESS_NAME = "文件上传";

    @Resource
    private TestService testService;

    @Resource
    private FileService fileService;

    @RequestMapping("/upload")
    public ResponseDto upload(@RequestParam MultipartFile file) throws IOException {//@RequestParam用来接收表单参数的
        LOG.info("上传开始文件：{}", file);
        LOG.info(file.getOriginalFilename());
        LOG.info(String.valueOf(file.getSize()));

        // 保存文件到本地
        String key = UuidUtil.getShortUuid();
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        String path = "teacher/" + key + "." + suffix;
        String fullPath = FILE_PATH + path;
        File dest = new File(fullPath);// dest 生成的目标位置
        file.transferTo(dest);// 文件写到目标位置
        LOG.info(dest.getAbsolutePath());// 打印目标位置全路径

        LOG.info("保存文件记录开始");
        FileDto fileDto = new FileDto();
        fileDto.setPath(path);
        fileDto.setName(fileName);
        fileDto.setSize(Math.toIntExact(file.getSize()));
        fileDto.setSuffix(suffix);
        fileDto.setUse("");
        fileService.save(fileDto);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(FILE_DOMAIN + path);
        return responseDto;
    }
}
