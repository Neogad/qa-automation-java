package com.tinkoff.edu.app;

public class LoanCalcService {
    /**
     * TODO Loan calculation
     */
    public LoanResponce createRequest(LoanRequest loanRequest) {
        LoancalcRepository loancalcRepository = new LoancalcRepository();
        return loanRequest.getMounths() == 10 ? new LoanResponce(LoanResponceType.APPROVE, loanRequest, loancalcRepository.save(loanRequest)) :
                new LoanResponce(LoanResponceType.DECLINE, loanRequest, loancalcRepository.save(loanRequest));

    }
}
