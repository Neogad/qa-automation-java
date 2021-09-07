package com.tinkoff.edu.app.repositories;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.enums.LoanResponceType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MapLoanCalcRepository implements LoanCalcRepository {

    Map<UUID,LoanResponce> loanResponses = new HashMap();
    LoanResponce loanResponce;
    UUID requesrId;


    @Override
    public LoanResponce save(LoanRequest loanRequest, LoanResponceType responceType) {
        requesrId = UUID.randomUUID();
        loanResponce = new LoanResponce(responceType,requesrId);
        loanResponses.put(requesrId,loanResponce);

        return loanResponce;
    }

    @Override
    public LoanResponce getResponce(UUID uuid) {
       return loanResponses.get(uuid);
    }

    @Override
    public void updateResponce(UUID requestId, LoanResponceType loanResponceType) {
        loanResponce = loanResponses.get(requestId);
        loanResponce.setLoanResponceType(loanResponceType);
        loanResponses.put(requestId,loanResponce);
    }
}
