package com.tinkoff.edu.app;

/**
 *
 */
public class LoanCalcController {
    /**
     *TODO Validate and logs Request
     */
    public  LoanResponce createRequest(LoanRequest loanRequest) {
        LoanCalcService loanCalcService = new LoanCalcService();
        LoancalcLogger.log(loanRequest);

        return loanCalcService.createRequest(loanRequest);

    }
}
