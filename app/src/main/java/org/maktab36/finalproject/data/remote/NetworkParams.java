package org.maktab36.finalproject.data.remote;

import java.util.HashMap;
import java.util.Map;

public class NetworkParams {
    public static final String BASE_PATH ="https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String CONSUMER_KEY = "ck_d124bb3fa24ca9de0ace0562ebcdfbd38c41a777";
    public static final String CONSUMER_SECRET = "cs_54318c25c795b798fc0ef70ade345a9413900d92";

    public static Map<String, String> BASE_OPTIONS = new HashMap<String,String>(){{
        put("consumer_key",CONSUMER_KEY);
        put("consumer_secret",CONSUMER_SECRET);
    }};

    public static Map<String, String> getProducts(){
        return BASE_OPTIONS;
    }
}