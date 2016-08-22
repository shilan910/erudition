package com.erudition.util;

/**
 * Created by sl on 16-8-21.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;


public class Compress {

    public static void main(String[] args) throws Exception {
        Compress compress = new Compress();
        File file = new File("/home/sl/test/test.zip");
//        unZipFiles(file,"/home/sl/test/zip/");
    }



    /**
     * zip解压缩
     *
     * @param zipfile
     *            File 需要解压缩的文件
     * @param descDir
     *            String 解压后的目标目录
     */
    public List<String> unZipFiles(File zipfile, String descDir) {

        List<String> fileNames = new ArrayList<>();

        try {
            ZipFile zf = new ZipFile(zipfile);
            for (Enumeration entries = zf.getEntries(); entries
                    .hasMoreElements();) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String zipEntryName = entry.getName();
                fileNames.add(zipEntryName);
                InputStream in = zf.getInputStream(entry);
                OutputStream out = new FileOutputStream(descDir + zipEntryName);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
                System.out.println("解压缩完成.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileNames;
    }


}
