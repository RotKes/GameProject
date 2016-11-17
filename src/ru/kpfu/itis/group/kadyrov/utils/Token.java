package ru.kpfu.itis.group.kadyrov.utils;

import java.security.SecureRandom;

/**
 * Created by Амир on 02.11.2016.
 */
public class Token {
    public static String getToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String token = bytes.toString();
        return token;
    }
}
