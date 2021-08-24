package com.tinkoff.edu.app.services;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.enums.LoanResponceType;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.repositories.LoanCalcRepository;

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
