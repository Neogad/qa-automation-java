package com.tinkoff.edu.app.controllers;

import com.tinkoff.edu.app.*;
import com.tinkoff.edu.app.enums.LoanResponceType;
import com.tinkoff.edu.app.exceptions.ValidateRequestException;
import com.tinkoff.edu.app.loggers.LoancalcLogger;
import com.tinkoff.edu.app.services.LoanCalcService;
import java.util.Objects;
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
    public LoanResponce createRequest(LoanRequest loanRequest) throws ValidateRequestException {
        if (Objects.equals(loanRequest,null)|| loanRequest.getFullName() == null | loanRequest.getLoanType() == null)
            throw new NullPointerException("loanRequest is null");


        if (loanRequest.getAmount() <= 0) throw new IllegalArgumentException("loanAmount zero or Negative");
        LoancalcLogger.log(loanRequest);


            ValidateRequest(loanRequest);


        return loanCalcService.createRequest(loanRequest);

    }

    public static void ValidateRequest(LoanRequest loanRequest) throws ValidateRequestException {
        if (loanRequest.getFullName().length() < 10 | loanRequest.getFullName().length() > 100)
            throw new ValidateRequestException("length fullname validate exception ");
    }


    public LoanResponce getResponce(UUID uuid) {
        return loanCalcService.getResponce(uuid);
    }

    public void updateResponce(UUID requestId, LoanResponceType loanResponceType) {
        loanCalcService.updateResponce(requestId, loanResponceType);
    }

    public void createManyRequests(LoanRequest loanRequest,int count) {
         loanCalcService.createManyRequests(loanRequest,count);

    }
}
