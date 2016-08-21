package com.erudition.util.nlpir;

/**
 * Created by sl on 16-6-1.
 */
import com.sun.jna.Native;

public class NLPIR {
    public static CLibrary Instance;
    private String systemCharset;
    private String systemFolder;
    int charsetType;

    public NLPIR(){
        systemFolder = "/home/sl/tools/NLPIR";  //ICTCLAS的目录,包括字典文件等
        systemCharset = "UTF-8";  //默认字符集
        charsetType = 1;


        String argu = "/home/sl/tools/NLPIR/Data";


        //Instance.NLPIR_Init(argu, charsetType, "0");


        Instance = (CLibrary) Native.loadLibrary(
                "/home/sl/tools/NLPIR/lib/linux64/libNLPIR.so", CLibrary.class);  //ICTCLAS的so文件地址
    }

    public static CLibrary getInstance() {
        return Instance;
    }

    public static void setInstance(CLibrary instance) {
        Instance = instance;
    }

    public String getSystemCharset() {
        return systemCharset;
    }

    public void setSystemCharset(String systemCharset) {
        this.systemCharset = systemCharset;
    }

    public String getSystemFolder() {
        return systemFolder;
    }

    public void setSystemFolder(String systemFolder) {
        this.systemFolder = systemFolder;
    }

    public int getCharsetType() {
        return charsetType;
    }

    public void setCharsetType(int charsetType) {
        this.charsetType = charsetType;
    }
}