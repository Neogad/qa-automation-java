package com.tinkoff.edu.app;

/**
 *
 */
public class LoanCalcController {
    /**
     *TODO Validate and logs Request
     */
    public static int createRequest() {
        LoancalcLogger.log();
        return LoanCalcService.createRequest();

    }
}
