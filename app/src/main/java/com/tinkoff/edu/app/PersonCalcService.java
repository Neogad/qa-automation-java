package com.tinkoff.edu.app;

public class PersonCalcService implements LoanCalcService{
    private LoanCalcRepository repo;

    public PersonCalcService(LoanCalcRepository repo) {
        this.repo = repo;
    }

    /**
     * TODO Loan calculation
     */
    public LoanResponce createRequest(LoanRequest loanRequest) {
        return loanRequest.getLoanType().equals(LoanType.PERSON) ? new LoanResponce(LoanResponceType.APPROVE, loanRequest, repo.save(loanRequest)) :
                new LoanResponce(LoanResponceType.DECLINE, loanRequest, repo.save(loanRequest));

    }
}
