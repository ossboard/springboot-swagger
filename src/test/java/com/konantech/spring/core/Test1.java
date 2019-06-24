package com.konantech.spring.core;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Test1 {

    @Test
    public void test1() {

        Date date = new Date();
        String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
        SimpleDateFormat sdf = new SimpleDateFormat(ISO_FORMAT);
        TimeZone utc = TimeZone.getTimeZone("UTC");
        sdf.setTimeZone(utc);

        System.out.println(sdf.format(date));

    }
}
