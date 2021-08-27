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
        final int AMOUNT = 10_000;
        final int MOUTH = 12;

        if (loanRequest == null) throw new IllegalArgumentException("loanRequest is null");

        if(loanRequest.getAmount()<=0) throw new IllegalArgumentException("loanAmount zero or Negative");

        if (loanRequest.getLoanType().equals(LoanType.PERSON)) {
            if (loanRequest.getAmount() <= AMOUNT & loanRequest.getMounths() <= MOUTH) {
                return repo.save(loanRequest, LoanResponceType.APPROVE);
            } else if (loanRequest.getAmount() > AMOUNT & loanRequest.getMounths() > MOUTH) {
                return repo.save(loanRequest, LoanResponceType.DECLINE);
            }

        } else if (loanRequest.getLoanType().equals(LoanType.OOO)) {
              if (loanRequest.getAmount() > AMOUNT & loanRequest.getMounths() < MOUTH) {
                return repo.save(loanRequest, LoanResponceType.APPROVE);
            }
        }
            return repo.save(loanRequest, LoanResponceType.DECLINE);



    }

}
