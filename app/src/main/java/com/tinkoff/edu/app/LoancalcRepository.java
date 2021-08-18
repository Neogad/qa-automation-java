package com.tinkoff.edu.app;

public class LoancalcRepository {
    private static int requestId;

    /**
     * TODO persists request
     * @return request id
     */
    public  int save(LoanRequest loanRequest) {
        return ++requestId;
    }
}
