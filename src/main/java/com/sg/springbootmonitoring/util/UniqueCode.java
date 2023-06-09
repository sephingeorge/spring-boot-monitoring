package com.sg.springbootmonitoring.util;

import java.util.Random;

public class UniqueCode {

    private static final Random random = new Random();

    public static Long appendUniqueCode() {
        return random.nextLong();
    }
}
