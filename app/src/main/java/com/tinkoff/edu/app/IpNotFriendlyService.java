package com.tinkoff.edu.app;

public class IpNotFriendlyService extends PersonCalcService {
    public IpNotFriendlyService(LoanCalcRepository repo) {
        super(repo);
    }

    @Override
    public LoanResponce createRequest(LoanRequest loanRequest) {
        return loanRequest.getLoanType().equals(LoanType.IP) ?
                new LoanResponce(LoanResponceType.DECLINE, loanRequest, -1) : super.createRequest(loanRequest);
    }
}
