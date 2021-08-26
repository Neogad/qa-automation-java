package com.tinkoff.edu.app.loggers;

import com.tinkoff.edu.app.LoanRequest;

public class LoancalcLogger {
    public static void log(LoanRequest loanRequest) {
        System.out.println("log: "+loanRequest);
    }
}
