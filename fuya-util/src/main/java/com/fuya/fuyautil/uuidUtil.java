package com.fuya.fuyautil;

import java.util.UUID;

public class uuidUtil {
    public static String getuuidUtil() {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();


        return uuid;
    }
}
