package com.erudition.util.nlpir;

/**
 * Created by sl on 16-6-2.
 */
public class WordFrequency {

    public void wordFre(String filePath , int keyNum , boolean hasWeight){
        NLPIR nlpir = new NLPIR();
        nlpir.Instance.NLPIR_Init(nlpir.getSystemFolder(),nlpir.getCharsetType() ,nlpir.getSystemCharset());
        String nativeBytes = null;

        nativeBytes = nlpir.Instance.NLPIR_GetFileKeyWords(filePath,keyNum,hasWeight);
        System.out.println("result : " + nativeBytes);
    }


}
