package com.erudition.util.nlpir;

import com.erudition.util.TextRead;

/**
 * Created by sl on 16-6-2.
 */
public class WordFrequency {

    public String[] wordFre(String filePath , int keyNum){
        NLPIR nlpir = new NLPIR();
        nlpir.Instance.NLPIR_Init(nlpir.getSystemFolder(),nlpir.getCharsetType() ,nlpir.getSystemCharset());
        String nativeBytes = null;

        nativeBytes = nlpir.Instance.NLPIR_GetFileKeyWords(filePath,keyNum,false);
        System.out.println("result : " + nativeBytes);

        String[] keywords = nativeBytes.split("#");

        String result = null;
        for(String keyword : keywords){
            result = result + " " + keyword;
        }
        return keywords;
    }

    public String[] wordFreByWord(String filePath , int keyNum){

        TextRead textRead = new TextRead();
        String txtUrl = textRead.getTextFromWord(filePath);

        NLPIR nlpir = new NLPIR();
        nlpir.Instance.NLPIR_Init(nlpir.getSystemFolder(),nlpir.getCharsetType() ,nlpir.getSystemCharset());
        String nativeBytes = null;

//        nativeBytes = nlpir.Instance.NLPIR_GetKeyWords(text,keyNum,false);
        nativeBytes = nlpir.Instance.NLPIR_GetFileKeyWords(txtUrl,keyNum,false);
        System.out.println("result : " + nativeBytes);

        String[] keywords = nativeBytes.split("#");

        return keywords;
    }


}
