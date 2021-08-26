package com.tinkoff.edu.app.services;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;

public interface LoanCalcService {
    LoanResponce createRequest(LoanRequest loanRequest);

    void setRequestId(int newRequestId);
}
