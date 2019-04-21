package com.fuya.fuyautil;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ImageUtil {
    public static String getImage(MultipartFile multipartFile) throws IOException {

        String fileName=multipartFile.getOriginalFilename();
        String suffixName=fileName.substring(fileName.lastIndexOf("."));
        String filepath="C://Image";
        fileName= uuidUtil.getuuidUtil()+suffixName;
        File file=new File(filepath,fileName);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdir();
        }
       multipartFile.transferTo(file);
        String filename = "/Image/" + fileName;
        return filename;
    }
}
