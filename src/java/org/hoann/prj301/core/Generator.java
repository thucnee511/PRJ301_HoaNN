package org.hoann.prj301.core;

import java.util.UUID;

public final class Generator {

    private static final int UUID_LENGTH = 10;

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        String current = Long.toString(System.currentTimeMillis());
        return (current + uuid.toString())
                .replaceAll("-", "")
                .replaceAll("\\D", "")
                .substring(0, UUID_LENGTH);
    }
}
