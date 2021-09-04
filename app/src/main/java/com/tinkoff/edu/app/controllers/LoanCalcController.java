package com.tinkoff.edu.app.controllers;

import com.tinkoff.edu.app.*;
import com.tinkoff.edu.app.enums.LoanResponceType;
import com.tinkoff.edu.app.loggers.LoancalcLogger;
import com.tinkoff.edu.app.services.LoanCalcService;

import java.util.UUID;

/**
 *
 */
public class LoanCalcController {
    private LoanCalcService loanCalcService;

    public LoanCalcController(LoanCalcService loanCalcService) {
        this.loanCalcService = loanCalcService;

    }

    /**
     * TODO Validate and logs Request
     */
    public LoanResponce createRequest(LoanRequest loanRequest) {
        LoancalcLogger.log(loanRequest);

        return loanCalcService.createRequest(loanRequest);

    }


    public LoanResponce getResponce(UUID uuid) {
        return loanCalcService.getResponce(uuid);
    }

    public void updateResponce(UUID requestId, LoanResponceType loanResponceType) {
        loanCalcService.updateResponce(requestId, loanResponceType);
    }
}
