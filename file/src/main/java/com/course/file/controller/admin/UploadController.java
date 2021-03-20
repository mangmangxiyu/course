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
import enums.FileUseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    public ResponseDto upload(@RequestParam MultipartFile shard,
                              String use,
                              String name,
                              String suffix,
                              Integer size,
                              Integer shardIndex,
                              Integer shardSize,
                              Integer shardTotal) throws IOException {//@RequestParam用来接收表单参数的

        // 保存文件到本地
        // 入参use:T,useEnum:TEACHER("T", "讲师");
        LOG.info("文件开始上传");

        FileUseEnum useEnum = FileUseEnum.getByCode(use);
        String key = UuidUtil.getShortUuid();

        // 如果文件不存在则创建dir:teacher
        String dir = useEnum.name().toLowerCase();
        File fullDir = new File(FILE_PATH + dir);
        if (!fullDir.exists()) {
            fullDir.mkdir();
        }

        // File.separator 文件分隔符：\虽然斜杠"/"也能识别，最好用反斜杠"\"
        String path = dir + File.separator + key +  "." + suffix;
        String fullPath = FILE_PATH + path;
        File dest = new File(fullPath);// dest 生成的目标位置
        shard.transferTo(dest);// 文件写到目标位置
        LOG.info(dest.getAbsolutePath());// 打印目标位置全路径

        LOG.info("保存文件记录开始");
        FileDto fileDto = new FileDto();
        fileDto.setPath(path);
        fileDto.setName(name);
        fileDto.setSize(size);
        fileDto.setSuffix(suffix);
        fileDto.setUse(use);
        fileDto.setShardIndex(shardIndex);
        fileDto.setSize(shardSize);
        fileDto.setShardTotal(shardTotal);
        fileDto.setKey(key);
        fileService.save(fileDto);

        ResponseDto responseDto = new ResponseDto();
        fileDto.setPath(FILE_DOMAIN + path);
        responseDto.setContent(fileDto);
        return responseDto;
    }

    @GetMapping("/merge")
    public ResponseDto merge() throws Exception {
        File newFile = new File(FILE_PATH + "course/test123.mp4");
        FileOutputStream outputStream = new FileOutputStream(newFile, true);// 文件追加写入
        FileInputStream fileInputStream = null;// 分片文件
        byte[] byt = new byte[10 * 1024 * 1024];
        int len;

        try {
            // 读取第一个分片
            fileInputStream = new FileInputStream(new File(FILE_PATH + "course/ALjhUDHf.blob"));
            while((len = fileInputStream.read(byt)) != -1) {
                outputStream.write(byt, 0, len);
            }

            // 读取第二个分片
            fileInputStream = new FileInputStream(new File(FILE_PATH + "course/a1QCTGzH.blob"));
            while ((len = fileInputStream.read(byt)) != -1) {
                outputStream.write(byt, 0, len);
            }

            // 读取第三个分片
            fileInputStream = new FileInputStream(new File(FILE_PATH + "course/dDk20pJS.blob"));
            while ((len = fileInputStream.read(byt)) != -1) {
                outputStream.write(byt, 0, len);
            }
        } catch (IOException e) {
            LOG.error("分片合并异常", e);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                outputStream.close();
                LOG.info("IO流关闭");
            } catch (IOException e) {
                LOG.error("IO流关闭", e);
            }
        }
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }
}
