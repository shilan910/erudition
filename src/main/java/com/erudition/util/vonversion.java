package com.erudition.util;

/**
 * Created by tsj on 16-6-1.
 */
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.codec.Base64;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

public class vonversion{
    //将Word内容写入pdf MultipartFile
    public boolean writePDF(MultipartFile partfile,String filePath) throws IOException{
        System.out.println("==============================");
        System.out.println("Write to pdf start:===========");
        boolean flag = false;
        final int margin = 10;
        //  String contents = getContents(partfile);

        //将 MultipartFile 转换为file
        CommonsMultipartFile cf= (CommonsMultipartFile)partfile; //这个partfile是MultipartFile的
        DiskFileItem fi = (DiskFileItem)cf.getFileItem();
        File file = fi.getStoreLocation();
        String cont = getWord(file);
//        File file = new File(filePath);
        if(file.length()>0){
            Document doc = null;
            FileOutputStream fos = null;
            PdfWriter pdfWriter = null;
            try {
                BaseFont baseFT = BaseFont.createFont("c://windows//fonts//simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                //BaseFont baseFT = BaseFont.CreateFont("c://windows//fonts//simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                //BaseFont bFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
                Font font = new Font(baseFT);
                font.setSize(8);
                doc = new Document(PageSize.A4, margin, margin, margin, margin);

                //转换后的PDF的文件路径
                fos = new FileOutputStream("C:/Users/Administrator/Desktop/aaa.pdf");
                pdfWriter = PdfWriter.getInstance(doc, fos);

                doc.open();
                doc.addTitle(filePath.replace("D:\\", ""));
                doc.add(new Paragraph(cont, font));

                flag=true;
                System.out.println("Write to pdf end:===========");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            } finally{
                if(doc != null){
                    doc.close();
                }

                if(pdfWriter != null){
                    pdfWriter.close();
                }

                if(fos != null){
                    fos.close();
                }

            }
        }
        return flag;
    }

    //读取Word
    public static String getWord(File file) {
        System.out.println("==============================");
        System.out.println("Extract word start:===========");
        // File file = new File(path);
        String returnString = "";
        Base64.InputStream is;
        try {
            is = new FileInputStream(file);
            WordExtractor extractor = new WordExtractor(is);
            returnString=extractor.getText();
            System.out.println("Extract word end:===========");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnString;
    }

}
