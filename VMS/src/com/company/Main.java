package com.company;

import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,2019);
        cal.set(Calendar.MONTH,8);
        cal.set(Calendar.DAY_OF_MONTH,13);
        cal.set(Calendar.HOUR,17);
        cal.set(Calendar.MINUTE,30);
        cal.set(Calendar.SECOND,0);

        Date date1 = cal.getTime();

        cal.set(2019,8,14,5,29,0);
        Date date2 = cal.getTime();

        System.out.println(date1.toString());
        System.out.println(date2.toString());

        if(date1.after(date2)){
            System.out.println("Data1 este dupa data2");
        }else{
            System.out.println("Data2 este dupa data1");
        }
    }
}
