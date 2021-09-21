package com.tinkoff.edu.app.repositories;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.enums.LoanResponceType;
import com.tinkoff.edu.app.enums.LoanType;
import java.util.UUID;

public interface LoanCalcRepository {
    LoanResponce save(LoanRequest loanRequest, LoanResponceType responceType);

    LoanResponce getResponce(UUID uuid);

    void updateResponce(UUID requestId, LoanResponceType loanResponceType);

    LoanResponce getResponce(LoanType loanType);
}
