package com.zhl.lamada.test;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Test {
    public static void main(String[] args) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String str = "2007-9-26";
        date = format.parse(str);
        format = DateFormat.getDateInstance(DateFormat.FULL);
        String format1 = format.format(date);
        System.out.println(format1);
    }
}
