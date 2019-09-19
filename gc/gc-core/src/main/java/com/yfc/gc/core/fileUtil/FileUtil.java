package com.yfc.gc.core.fileUtil;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author: Jdx
 * @Date: 2019-3-19 9:57
 * @Description: TODO FileUtil工具类
 */
public class FileUtil {
    //文件上传工具类服务方法
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}