package com.zhl.springbootexcel.util;

import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.TableStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/5/20 10:40
 */
public class DataUtil {

    /**
     *动态表头
     * @return
     */
    public static List<List<String>> createTestListStringHead() {

        // 模型上没有注解，表头数据动态传入
        List<List<String>> head = new ArrayList<List<String>>();
        List<String> headCoulumn1 = new ArrayList<String>();
        List<String> headCoulumn2 = new ArrayList<String>();
        List<String> headCoulumn3 = new ArrayList<String>();
        List<String> headCoulumn4 = new ArrayList<String>();
        List<String> headCoulumn5 = new ArrayList<String>();

        headCoulumn1.add("第一列");
        headCoulumn1.add("第一列");
        headCoulumn1.add("第一列");

        headCoulumn2.add("第一列");
        headCoulumn2.add("第一列");
        headCoulumn2.add("第一列");

        headCoulumn3.add("第二列");
        headCoulumn3.add("第二列");
        headCoulumn3.add("第二列");

        headCoulumn4.add("第三列");
        headCoulumn4.add("第三列2");
        headCoulumn4.add("第三列2");

        headCoulumn5.add("第一列");
        headCoulumn5.add("第3列");
        headCoulumn5.add("第4列");

        head.add(headCoulumn1);
        head.add(headCoulumn2);
        head.add(headCoulumn3);
        head.add(headCoulumn4);
        head.add(headCoulumn5);
        return head;
    }

    /**
     * 自定义样式
     * @return
     */
    public static TableStyle createTableStyle() {
        TableStyle tableStyle = new TableStyle();
        // 设置表头样式
        Font headFont = new Font();
        // 字体是否加粗
        headFont.setBold(true);
        // 字体大小
        headFont.setFontHeightInPoints((short)12);
        // 字体
        headFont.setFontName("楷体");
        tableStyle.setTableHeadFont(headFont);
        // 背景色
        tableStyle.setTableHeadBackGroundColor(IndexedColors.BLUE);


        // 设置表格主体样式
        Font contentFont = new Font();
        contentFont.setBold(true);
        contentFont.setFontHeightInPoints((short)12);
        contentFont.setFontName("黑体");
        tableStyle.setTableContentFont(contentFont);
        tableStyle.setTableContentBackGroundColor(IndexedColors.GREEN);
        return tableStyle;
    }
}
