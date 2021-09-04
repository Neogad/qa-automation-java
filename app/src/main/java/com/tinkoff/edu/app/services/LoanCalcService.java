package com.tinkoff.edu.app.services;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.enums.LoanResponceType;

import java.util.UUID;

public interface LoanCalcService {
    LoanResponce createRequest(LoanRequest loanRequest);
    LoanResponce getResponce(UUID uuid);

    void updateResponce(UUID responseId ,LoanResponceType loanResponceType);

}
