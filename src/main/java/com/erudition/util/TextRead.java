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
            try{
                FileInputStream fis = new FileInputStream(file);
                WordExtractor wordExtractor = new WordExtractor(fis);
                result = wordExtractor.getText();
                System.out.println(result);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            };
            return result;
        }




}
