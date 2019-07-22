package com.zhl.springbootexcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.zhl.springbootexcel.util.DataUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/5/18 14:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelTest {

    @Test
    public void writeExcel1() throws IOException {
        //文件输出位置
        OutputStream outputStream = new FileOutputStream("C:\\code\\test.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
        //写仅有一个Sheet 的excel文件
        Sheet sheet = new Sheet(1,0,WriteModel.class);
        //第一个sheet名称
        sheet.setSheetName("人员统计");
        //写数据到Writer上下文中
        //入参1：创建要写入的模型数据
        //入参2：要写入的目标 sheet
        writer.write(createModelList(), sheet);
        //将上下文中最终OutputStream写入到指定文件中
        writer.finish();
        outputStream.close();
    }

    public List<WriteModel> createModelList(){
        List<WriteModel> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            WriteModel writeModel = WriteModel.builder().name("李哈").password("123456").age(i + 1).build();
            list.add(writeModel);
        }
        return list;
    }

    /**
     * 自定义表头
     * @throws IOException
     */
    @Test
    public void writeExcel2() throws IOException {
        //文件输出位置
        OutputStream outputStream = new FileOutputStream("C:\\code\\test8.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
        //写仅有一个Sheet 的excel文件
        Sheet sheet = new Sheet(1,0);
        //第一个sheet名称
        sheet.setSheetName("人员统计");
        //创建一个表格，用于sheet中使用
        Table table = new Table(1);

        //无注解的模式   动态添加表头
        table.setHead(DataUtil.createTestListStringHead());

        //写数据到Writer上下文中
        //入参1：创建要写入的模型数据
        //入参2：要写入的目标 sheet
        writer.write1(createDynamicModelList(), sheet, table);
        //将上下文中最终OutputStream写入到指定文件中
        writer.finish();
        outputStream.close();
    }

    /**
     * 无注解的实体类
     *
     * @return
     */
    private List<List<Object>> createDynamicModelList() {
        // 所有行数据
        List<List<Object>> rows = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            // 一行数据
            List<Object> row = new ArrayList<>();
            row.add("字符串" + i);
            row.add(Long.valueOf(187837834l + i));
            row.add(Integer.valueOf(2233 + i));
            row.add("到南京");
            row.add("大数据");
            rows.add(row);
        }

        return rows;
    }

    /**
     * 自定义样式
     * @throws IOException
     */
    @Test
    public void writeExcel3() throws IOException {
        //文件输出位置
        OutputStream outputStream = new FileOutputStream("C:\\code\\test3.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
        //写仅有一个Sheet 的excel文件
        Sheet sheet = new Sheet(1,0);
        //第一个sheet名称
        sheet.setSheetName("人员统计");
        //创建一个表格，用于sheet中使用
        Table table = new Table(1);

        table.setTableStyle(DataUtil.createTableStyle());

        //无注解的模式   动态添加表头
        table.setHead(DataUtil.createTestListStringHead());

        //写数据到Writer上下文中
        //入参1：创建要写入的模型数据
        //入参2：要写入的目标 sheet
        writer.write1(createDynamicModelList(), sheet, table);
        //将上下文中最终OutputStream写入到指定文件中
        writer.finish();
        outputStream.close();
    }

    /**
     * 合并单元格
     * @throws IOException
     */
    @Test
    public void writeExcel4() throws IOException {
        long startTime = System.currentTimeMillis();
        //文件输出位置
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //OutputStream outputStream = new FileOutputStream("C:\\code\\测试一百万.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
        //写仅有一个Sheet 的excel文件
        Sheet sheet = new Sheet(1,0);
        //第一个sheet名称
        sheet.setSheetName("人员统计");
        //创建一个表格，用于sheet中使用
        Table table = new Table(1);

        table.setTableStyle(DataUtil.createTableStyle());

        //无注解的模式   动态添加表头
        table.setHead(DataUtil.createTestListStringHead());

        //写数据到Writer上下文中
        //入参1：创建要写入的模型数据
        //入参2：要写入的目标 sheet
        writer.write1(createDynamicModelList(), sheet, table);
        //将上下文中最终OutputStream写入到指定文件中
        writer.merge(5,6,0,4);
        writer.finish();
        outputStream.close();
        System.out.println("excel:" + outputStream.toByteArray());
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (endTime - startTime));
    }






}
