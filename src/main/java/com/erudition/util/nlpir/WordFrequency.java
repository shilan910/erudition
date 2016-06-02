package com.erudition.util.nlpir;

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
        return keywords;

    }


}
