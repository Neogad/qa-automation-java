package com.tinkoff.edu.app.repositories;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.enums.LoanResponceType;

public interface LoanCalcRepository {
    LoanResponce save(LoanRequest loanRequest, LoanResponceType responceType);

    void setRequestId(int requestId);

}
