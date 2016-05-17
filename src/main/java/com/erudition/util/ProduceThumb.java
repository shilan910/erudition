package com.erudition.util;

import java.io.IOException;
import java.util.List;

/**
 * Created by sl on 16-5-17.
 */
public class ProduceThumb {

    public static void main(String args[]) throws IOException {

        if(new ProduceThumb().processImg("testtest.flv")){
            System.out.println("get!!!");
        }

    }


    /**
     * 生成视频缩略图
     * @param filerealname 待生成的视频文件含后缀名
     * @return 返回是否转换成功
     */
    public static boolean processImg(String filerealname) {

        List commend = new java.util.ArrayList();
        commend.add("/opt/ffmpeg/bin/ffmpeg");
        commend.add("-i");
//        commend.add(videoRealPath + filerealname + ".flv");
        commend.add("src/main/webapp/assets/file/video/" + filerealname);


        commend.add("-y");
        commend.add("-f");
        commend.add("image2");
        commend.add("-ss");
        commend.add("3");
        commend.add("-t");
        commend.add("0.001");
        commend.add("-s");
        commend.add("320x240");
        commend.add("src/main/webapp/assets/file/thumb/" + filerealname + ".jpg");
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            builder.start();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
