package com.tinkoff.edu.app;

public class LoanCalcService {
    /**
     * TODO Loan calculation
     */
    public  LoanResponce createRequest(LoanRequest loanRequest) {
        LoancalcRepository loancalcRepository = new LoancalcRepository();
        if(loanRequest.getMounths()==10) {
            return new LoanResponce(LoanResponceType.APPROVE, loanRequest, loancalcRepository.save(loanRequest));
        }
        else {
            return new LoanResponce(LoanResponceType.DECLINE, loanRequest, loancalcRepository.save(loanRequest));

        }


        }
}
