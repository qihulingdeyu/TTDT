package com.qing.ttdt.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MusicInfoUtils {

    public static String formatDuration(int duration){
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss", Locale.CHINA);
        return sdf.format(duration);
    }
}
