package com.erudition.util;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sl on 16-5-17.
 */
public class ProduceThumb {

    public static void main(String args[]) throws IOException {

        String path = new ProduceThumb().processVideoThumb("/home/sl/test/数据/学习资源推荐系统演示视频.wmv");
        if(path != null){
            System.out.println("get!!!\n"+path);
        }

    }


    /**
     * 生成视频缩略图
     * @return 返回是否转换成功
     */
    public static String processVideoThumb(String filePath) {

        String fileName = filePath.substring(filePath.lastIndexOf('/')+1,filePath.length());

        String thumbPath = filePath.substring(0,filePath.lastIndexOf('/')) +"/thumb/"+ fileName + ".jpg";

        List commend = new java.util.ArrayList();
        commend.add("/opt/ffmpeg/bin/ffmpeg");
        commend.add("-i");
        //视频文件路径
        commend.add(filePath);
        commend.add("-y");
        commend.add("-f");
        commend.add("image2");
        commend.add("-ss");
        commend.add("3");
        commend.add("-t");
        commend.add("0.001");
        commend.add("-s");
        commend.add("250x252");
        //生成的视频缩略图存放路径
//        String thumbPath = picPath +"/"+ filerealname + ".jpg";
        commend.add(thumbPath);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            builder.start();
            return thumbPath;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }



    public static String processPictureThumb(String filePath) throws IOException {

        String fileName = filePath.substring(filePath.lastIndexOf('/')+1,filePath.length());

        String thumbPath = filePath.substring(0,filePath.lastIndexOf('/')) +"/thumb/"+ fileName + ".jpg";
        Thumbnails.of(filePath)
                .size(250,252)
                .toFile(thumbPath);
        return thumbPath;
    }






    public static final String FILETYPE_JPG = "jpg";

    public static final String SUFF_IMAGE = "." + FILETYPE_JPG;



            /**
             05
             * 将指定pdf文件的首页转换为指定路径的缩略图
             06
             *@param filepath 原文件路径，例如d:/test.pdf
            07
             *@param imagepath 图片生成路径，例如 d:/test-1.jpg
            08
             *@param zoom     缩略图显示倍数，1表示不缩放，0.3则缩小到30%
            09
             */

//    public static void tranfer(String filepath, String imagepath, float zoom) throws PDFException, PDFSecurityException, IOException {
//
//        Document document = null;
//
//        float rotation = 0f;
//        document = new Document();
//        document.setFile(filepath);
//        BufferedImage img = (BufferedImage) document.getPageImage(0,
//                GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, rotation, zoom);
//
//        Iterator iter = ImageIO.getImageWritersBySuffix(FILETYPE_JPG);
//
//        ImageWriter writer = (ImageWriter) iter.next();
//
//        File outFile = new File(imagepath);
//
//        FileOutputStream out = new FileOutputStream(outFile);
//
//        ImageOutputStream outImage = ImageIO.createImageOutputStream(out);
//
//        writer.setOutput(outImage);
//        writer.write(new IIOImage(img, null, null));
//    }

}
