package com.erudition.util.nlpir;

/**
 * Created by sl on 16-6-1.
 */
import com.sun.jna.Library;

public interface CLibrary extends Library {

    public int NLPIR_Init(String sDataPath, int encoding,
                          String sLicenceCode);

    public String NLPIR_ParagraphProcess(String sSrc, int bPOSTagged);

    public String NLPIR_GetKeyWords(String sLine, int nMaxKeyLimit,
                                    boolean bWeightOut);
    public String NLPIR_GetFileKeyWords(String sLine, int nMaxKeyLimit,
                                        boolean bWeightOut);
    public int NLPIR_AddUserWord(String sWord);//add by qp 2008.11.10
    public int NLPIR_DelUsrWord(String sWord);//add by qp 2008.11.10
    public int NLPIR_ImportUserDict(String sWord,boolean bOverWrite);
    public String NLPIR_WordFreqStat(String sWord);
    public String NLPIR_GetLastErrorMsg();
    public void NLPIR_Exit();
}
