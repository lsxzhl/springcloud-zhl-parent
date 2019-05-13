package com.example.demo.exportcsv;

import com.csvreader.CsvWriter;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/5/6 17:01
 */
public class ExportCsv {


    public static <T> void writeCSV(Collection<T> dataset, String csvFilePath, String[] csvHeaders) {
        try {
            // 定义路径，分隔符，编码
            CsvWriter csvWriter = new CsvWriter(csvFilePath, ';', Charset.forName("UTF-8"));
            // 写表头
            csvWriter.writeRecord(csvHeaders);
            // 写内容
            //遍历集合
            Iterator<T> it = dataset.iterator();
            while (it.hasNext()) {
                T t = (T) it.next();
                //获取类属性
                Field[] fields = t.getClass().getDeclaredFields();
                String[] csvContent=new String[fields.length];
                for (short i = 0; i < fields.length; i++) {


                    Field field = fields[i];
                    String fieldName = field.getName();
                    String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    try {
                        Class tCls = t.getClass();
                        Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
                        Object value = getMethod.invoke(t, new Object[] {});
                        if (value == null) {
                            continue;
                        }
                        //取值并赋给数组
                        String textvalue=value.toString();
                        csvContent[i]=textvalue;
                        //System.out.println("fieldname="+fieldName+"||getMethodname="+getMethodName+"||textvalue="+textvalue);
                    }catch (Exception e) {
                        e.getStackTrace();
                    }
                }
                //迭代插入记录
                csvWriter.writeRecord(csvContent);
            }
            csvWriter.close();
            System.out.println("<--------CSV文件写入成功-------->");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class StudentForTest{
        private int age;
        private String email;
        private String name;
        private String phone;
        private String sex;
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getPhone() {
            return phone;
        }
        public void setPhone(String phone) {
            this.phone = phone;
        }
        public String getSex() {
            return sex;
        }
        public void setSex(String sex) {
            this.sex = sex;
        }

    }



    public static void main(String[] args) {

        StudentForTest s=new StudentForTest();
        s.setAge(21);
        s.setEmail("11");
        s.setName("guoshipeng");
        s.setPhone("157");
        s.setSex("1");

        StudentForTest s1=new StudentForTest();
        s1.setAge(22);
        s1.setEmail("11");
        s1.setName("guoshipeng");
        s1.setPhone("157");
        s1.setSex("0");
        List<StudentForTest> l=new ArrayList<StudentForTest>();
        l.add(s);
        l.add(s1);
        String csvFilePath = "C:\\code\\guoshipeng.csv";
        //String[] csvHeaders = { "年龄", "邮件", "姓名","手机","性别" };

        final String[] csvHeaders = {"序号","保单号","投保单号","归属机构代码","归属机构名称","渠道代码","渠道名称","出单网点代码",
                "出单网点名称","出单员代码","出单员名称","录单日期","起保日期","终保日期","被保险人数","（第一）被保险人姓名",
                "（第一）被保险人证件类型","（第一）被保险人证件号码","投保人姓名（名称）","投保人证件类型","投保人证件号码",
                "投保人电话","支付方式","支付类型","最终总保费","旅游团号/其他标识","目的地国家/地区名（旅行险）","备注"};
        writeCSV(l,csvFilePath,csvHeaders);
    }
}
