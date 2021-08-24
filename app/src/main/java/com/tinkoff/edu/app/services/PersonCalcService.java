package com.tinkoff.edu.app.services;

import com.tinkoff.edu.app.*;
import com.tinkoff.edu.app.enums.LoanResponceType;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.repositories.LoanCalcRepository;

public class PersonCalcService implements LoanCalcService {
    private LoanCalcRepository repo;

    public PersonCalcService(LoanCalcRepository repo) {
        this.repo = repo;
    }

    /**
     * TODO Loan calculation
     */
    public LoanResponce createRequest(LoanRequest loanRequest) {
        return loanRequest.getLoanType().equals(LoanType.PERSON) ? repo.save(loanRequest,LoanResponceType.APPROVE) :
                repo.save(loanRequest,LoanResponceType.DECLINE);

    }
}
