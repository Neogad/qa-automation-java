package com.tinkoff.edu.app.services;

import com.tinkoff.edu.app.*;
import com.tinkoff.edu.app.enums.LoanResponceType;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.repositories.LoanCalcRepository;

import java.util.UUID;

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

        switch (loanRequest.getLoanType()) {
            case PERSON: {
                if (loanRequest.getAmount() <= AMOUNT & loanRequest.getMounths() <= MOUTH) {
                    return repo.save(loanRequest, LoanResponceType.APPROVE);
                } else if (loanRequest.getAmount() > AMOUNT & loanRequest.getMounths() > MOUTH) {
                    return repo.save(loanRequest, LoanResponceType.DECLINE);
                }
                break;
            }
            case OOO: {
                if (loanRequest.getAmount() > AMOUNT & loanRequest.getMounths() < MOUTH) {
                    return repo.save(loanRequest, LoanResponceType.APPROVE);
                }
                break;
            }

        }
        return repo.save(loanRequest, LoanResponceType.DECLINE);
    }

 @Override
    public LoanResponce getResponce(UUID uuid) {
        return repo.getResponce(uuid);
    }


    @Override
    public void updateResponce(UUID requestId, LoanResponceType loanResponceType) {
        repo.updateResponce(requestId, loanResponceType);
    }



    @Override
    public LoanResponce getResponce(LoanType loanType)  {
        return repo.getResponce(loanType);
    }


}