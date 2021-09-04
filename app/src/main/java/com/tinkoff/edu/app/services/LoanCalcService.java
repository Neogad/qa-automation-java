package com.tinkoff.edu.app.services;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.enums.LoanResponceType;
import com.tinkoff.edu.app.exceptions.ValidateRequestException;

import java.util.UUID;

public interface LoanCalcService {
    LoanResponce createRequest(LoanRequest loanRequest) throws ValidateRequestException;
    LoanResponce getResponce(UUID uuid);

    void updateResponce(UUID responseId ,LoanResponceType loanResponceType);

    void createManyRequests(LoanRequest loanRequest,int count);
}
