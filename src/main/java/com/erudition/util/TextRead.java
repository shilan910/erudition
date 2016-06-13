package com.erudition.util;

import java.io.*;
import org.apache.poi.hwpf.extractor.WordExtractor;

/**
 * Created by sl on 16-6-3.
 */
public class TextRead {

        /**
         * @param filePath 文件路径
         * @return 读出的Word的内容
         */
        public String getTextFromWord(String filePath){
            String result = null;
            File file = new File(filePath);
            File tempFile = null;
            try{
                FileInputStream fis = new FileInputStream(file);
                WordExtractor wordExtractor = new WordExtractor(fis);
                result = wordExtractor.getText();
//                System.out.println(result);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }

            try {
                tempFile =new File(filePath+".txt");
//                if(!tempFile.exists()){
//                    tempFile.mkdirs();
//                }
                byte[] buffer = result.getBytes();
                FileOutputStream fStream = new FileOutputStream(filePath+".txt");
                fStream.write(buffer);
//            System.out.println(buffer);
                fStream.close();
            } catch (IOException e) {
               // multipartUrl = "";
                e.printStackTrace();
            }
            return tempFile.getPath();
        }




}
