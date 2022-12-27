package com.fis.sdc.checkfile.CONTROLLER;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class tachchuoi {
    public static void main(String[] args) {


        String str = "abc.def.ghi";
        String strBeforeFirstDot = str.substring(0, str.indexOf('.'));
            System.out.println(strBeforeFirstDot);
//       Date date = new Date();
//
//        DateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy");
//        System.out.println(new Timestamp(date.getTime()));
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        DateFormat f1 = new SimpleDateFormat("dd.MM.yyyy");
        String d = f1.format(date);
        System.out.println(d);

        String st= "Xin chào bạn#fdfd";
        st = st.replaceAll("[^a-zA-Z0-9]", "");
        System.out.println(st);
        System.out.println(st.length());


//        Charset cs = Charset.defaultCharset();
//
//        System.out.println("The default charset of the machine is :" + cs.displayName());
    }

}


