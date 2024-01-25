package com.example.puphub.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
    public static List<String> getBeforeSevenDays2() {
        // 获取当前日期
        Date currentDate = new Date();

        // 创建Calendar实例
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //当天月日
        List<String> list = new ArrayList<>();
        list.add(sdf.format(new Date()));
        // 遍历并输出前6天的日期
        for (int i = 0; i < 6; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date previousDate = calendar.getTime();
            list.add(sdf.format(previousDate));
        }

        return list;

    }


    //测试main方法
    public static void main(String[] args) {
        List<String> beforeSevenDays2 = getBeforeSevenDays2();
        for (int i = 0; i < beforeSevenDays2.size(); i++) {
            System.out.println(beforeSevenDays2.get(i));
        }
    }
}
