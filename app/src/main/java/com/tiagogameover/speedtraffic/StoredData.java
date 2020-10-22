package com.tiagogameover.speedtraffic;

import java.util.ArrayList;
import java.util.List;


public class StoredData {

    protected static List<Long> downloadList = new ArrayList<>();
    protected static List<Long> uploadList = new ArrayList<>();

    protected static long downloadSpeed = 0;
    protected static long uploadSpeed = 0;
    protected static boolean isSetData = false;

    public static void setZero() {
        isSetData = true;
        // return if listed is full
        for (int i = 0; i < 300; i++) {
            downloadList.add(0L);
            uploadList.add(0L);

        }

    }
}
