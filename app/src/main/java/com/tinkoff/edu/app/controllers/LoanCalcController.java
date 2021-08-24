package com.tinkoff.edu.app.controllers;

import com.tinkoff.edu.app.*;
import com.tinkoff.edu.app.loggers.LoancalcLogger;
import com.tinkoff.edu.app.repositories.LoanCalcRepository;
import com.tinkoff.edu.app.services.IpNotFriendlyService;
import com.tinkoff.edu.app.services.LoanCalcService;

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
