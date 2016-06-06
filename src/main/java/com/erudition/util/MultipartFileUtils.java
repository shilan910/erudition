package com.erudition.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sl on 16-4-4.
 */
public class MultipartFileUtils {

    /**
     * 存储文件
     * 将MultipartFile格式的文件存储到存储介质上，命名格式为MD5Hash(name+UNIX时间戳)
     * 将存储的绝对路径返回
     * @param multipartFile 待存储的文件
     * @param path 存储的路径
     * @return 文件的绝对路径
     */
    public static String saveFile(MultipartFile multipartFile,String path,String type){

        long unixTime = System.currentTimeMillis();
        String name = HashUtils.HashPath(multipartFile.getOriginalFilename()+unixTime);
        String multipartUrl = path+"/"+name+"."+type;

        System.out.println("filePath : "+multipartUrl);

        File file = null;
        try {
            file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
            byte[] buffer = multipartFile.getBytes();
            FileOutputStream fStream = new FileOutputStream(multipartUrl);
            fStream.write(buffer);
//            System.out.println(buffer);
            fStream.close();
        } catch (IOException e) {
            multipartUrl = "";
            e.printStackTrace();
        }
        return multipartUrl;
    }



    public static String updateFile(MultipartFile multipartFile, String path ,String type){
        removeFile(path);
        int lastIndex = path.lastIndexOf("/");
        path = path.substring(0, lastIndex);
        long unixTime = System.currentTimeMillis();
        path += HashUtils.HashPath(multipartFile.getOriginalFilename()+unixTime);
        String multipartUrl = saveFile(multipartFile, path , type);
        return multipartUrl;
    }
    /**
     * 删除文件
     * @param path 待删除的文件路径
     */
    public static void removeFile(String path){
        File file = null;
        file = new File(path);
        if(file.exists()){
            file.delete();
        }
    }
    /**
     * 删除文件夹
     * @param file 待删除的文件夹
     */
    public static void deleteFile(File file) {
        File[] files = file.listFiles();
        if (files != null)
            for (File f : files)
                deleteFile(f);
        file.delete();
    }
    /**
     * 删除文件夹
     * @param //file 待删除的文件夹路径
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        if (files != null)
            for (File f : files)
                deleteFile(f);
        file.delete();
    }

}
