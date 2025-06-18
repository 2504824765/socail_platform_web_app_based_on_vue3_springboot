package com.cz.springboot_demo.controller;

import com.cz.springboot_demo.exception.InvalidFileFormatException;
import com.cz.springboot_demo.pojo.dto.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

// Since 2025/5/14 by CZ
@RestController
@CrossOrigin
@RequestMapping("/api/upload")
public class FileUploadController {
    @Operation(summary = "用户上传头像")
    @PostMapping("/userImage")
    public ResponseMessage uploadUserImage(String userName, MultipartFile photo, HttpServletRequest request) throws IOException {
        // 部署时的路径
//        String path = request.getSession().getServletContext().getRealPath("/upload/");
        // 模拟路径
        String path = "/Users/zanderc/Documents/Runtime/social_platform/userImage/";
        System.out.println(path);
        System.out.println(photo.getOriginalFilename());
        System.out.println(photo.getContentType());
        if (photo.getContentType().contains("image")) {
            saveFile(photo, userName, path);
            long timestamp = System.currentTimeMillis(); // 毫秒级时间戳
            String imageUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploads/" + userName + ".jpg";
            return ResponseMessage.success("Upload Success", imageUrl);
        } else {
            throw new InvalidFileFormatException("Invalid file format");
        }
    }

    @Operation(summary = "上传商品图片")
    @PostMapping("/productImage")
    public ResponseMessage uploadProductImage(String productName, MultipartFile photo, HttpServletRequest request) throws IOException {
        String path = "/Users/zanderc/Documents/Runtime/social_platform/productImage/";
        System.out.println(path);
        System.out.println(photo.getOriginalFilename());
        System.out.println(photo.getContentType());
        if (photo.getContentType().contains("image")) {
            saveFile(photo, productName, path);
            long timestamp = System.currentTimeMillis();
            String imageUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploads/" + productName + ".jpg";
            return ResponseMessage.success("Upload Success", imageUrl);
        } else {
            throw new InvalidFileFormatException("Invalid file format");
        }
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
