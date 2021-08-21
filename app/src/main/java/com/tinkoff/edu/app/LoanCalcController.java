package com.tinkoff.edu.app;

/**
 *
 */
public class LoanCalcController {
    private LoanCalcService loanCalcService;

    public LoanCalcController(LoanCalcService loanCalcService) {
        this.loanCalcService = loanCalcService;

    }

    public LoanCalcController(LoanCalcRepository repo) {
         loanCalcService = new IpNotFriendlyService(repo);
    }

    /**
     * TODO Validate and logs Request
     */
    public LoanResponce createRequest(LoanRequest loanRequest) {
        LoancalcLogger.log(loanRequest);

        return loanCalcService.createRequest(loanRequest);

    }
}
