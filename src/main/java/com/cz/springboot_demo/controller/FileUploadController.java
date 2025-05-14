package com.cz.springboot_demo.controller;

import com.cz.springboot_demo.exception.InvalidFileFormatException;
import com.cz.springboot_demo.pojo.dto.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

// Since 2025/5/14 by CZ
@RestController
@RequestMapping("/api/upload")
public class FileUploadController {
    @PostMapping
    public ResponseMessage uploadUserImage(String userName, MultipartFile photo, HttpServletRequest request) throws IOException {
        // 部署时的路径
//        String path = request.getSession().getServletContext().getRealPath("/upload/");
        // 模拟路径
        String path = "/Users/meyerchen/Documents/Temp/social_platform/";
        System.out.println(path);
        System.out.println(photo.getOriginalFilename());
        System.out.println(photo.getContentType());
        if (photo.getContentType().contains("image")) {
            saveFile(photo, userName, path);
        } else {
            throw new InvalidFileFormatException("Invalid file format");
        }
        return ResponseMessage.success("Upload success");
    }

    private void saveFile(MultipartFile file, String userName, String path) throws IOException {
        // 存储的目录是否存在，不存在就创建
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File saveFile = new File(path + userName + ".jpg");
        file.transferTo(saveFile);
    }
}
