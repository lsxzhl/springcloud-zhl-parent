package com.zhl.servicehi.interview.poi;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2020/1/11 13:31
 */
public class TestWordTransformPdf {

    public static void main(String[] args) throws Exception {

        /*FileInputStream fileInputStream = new FileInputStream("C:\\code\\file\\insecure.docx");
        XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
        PdfOptions pdfOptions = PdfOptions.create();
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\code\\file\\poi笔记.pdf");
        PdfConverter.getInstance().convert(xwpfDocument, fileOutputStream, pdfOptions);
        fileInputStream.close();
        fileOutputStream.close();*/

        /*String filepath = "C:/code/file/insecure.docx";
        String outpath = "C:/code/file/poiDhd.pdf";

        InputStream source = new FileInputStream(filepath);
        OutputStream target = new FileOutputStream(outpath);


        PdfOptions options = PdfOptions.create();

        wordConverterToPdf(source, target, options);*/


    }

    /**
     * 将word文档， 转换成pdf, 中间替换掉变量
     *
     * @param source 源为word文档， 必须为docx文档
     * @param target 目标输出
     * @throws Exception
     */
    public static void wordConverterToPdf(InputStream source,
                                          OutputStream target) throws Exception {
        wordConverterToPdf(source, target, PdfOptions.create().fontEncoding("windows-1250"));
    }

    /**
     * 将word文档， 转换成pdf, 中间替换掉变量
     *
     * @param source  源为word文档， 必须为docx文档
     * @param target  目标输出
     * @param options PdfOptions.create().fontEncoding( "windows-1250" ) 或者其他
     * @throws Exception
     */
    public static void wordConverterToPdf(InputStream source, OutputStream target,
                                          PdfOptions options) throws Exception {
        XWPFDocument doc = new XWPFDocument(source);
        // 中文字体处理
       /* options.fontProvider((familyName, encoding, size, style, color) -> {
            try {
                BaseFont bfChinese = BaseFont.createFont("Identity-H", "Identity-V", BaseFont.NOT_EMBEDDED);
                Font fontChinese = new Font(bfChinese, size, style, color);
                if (familyName != null) {
                    fontChinese.setFamily(familyName);
                }
                return fontChinese;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
*/
        PdfConverter.getInstance().convert(doc, target, options);
    }

}
