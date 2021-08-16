package com.tinkoff.edu.app;

public class LoancalcRepository {
    private static int requestId=0;

    /**
     * TODO persists request
     * @return request id
     */
    public static int save() {
        return ++requestId;
    }
}
