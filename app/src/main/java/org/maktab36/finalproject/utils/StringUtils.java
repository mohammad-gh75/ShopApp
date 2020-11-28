package org.maktab36.finalproject.utils;

import android.text.Html;

import java.util.Locale;

public class StringUtils {

    public static String getFormattedPrice(String price){
        return String.format(new Locale("fa"),"%,d",Long.parseLong(price));
    }

    public static String getProductDescription(String description){
        return Html
                .fromHtml(description)
                .toString()
                .replace(":"," :");
    }
}
