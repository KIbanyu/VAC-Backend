package com.kibzdev.globalFarm.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;

public class Constants {



    public static String getTodayDate() {

        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        long now = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);

        return formatter.format(calendar.getTime());
    }


    public static String getTimeStamp() {
        LocalDateTime current = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyymmddhhmmss");
        String formatted = current.format(formatter);


        return formatted;
    }



    public static String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }

        return sb.toString();
    }

}
