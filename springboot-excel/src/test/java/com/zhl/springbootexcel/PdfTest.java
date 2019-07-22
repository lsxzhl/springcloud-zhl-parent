package com.zhl.springbootexcel;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/5/21 16:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PdfTest {

    private static final String PATh = "C:\\code\\test.pdf";
    /**
     * itext不支持中文，需要加入字体文件
     */
    private static final String FONT = "font/STSONG.TTF";

    @Test
    public void writePdf() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter instance = PdfWriter.getInstance(document, new FileOutputStream(PATh));
        document.open();
        Font font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        document.add(new Paragraph("测试第一个pdf", font));
        document.close();
        instance.close();
    }


}
