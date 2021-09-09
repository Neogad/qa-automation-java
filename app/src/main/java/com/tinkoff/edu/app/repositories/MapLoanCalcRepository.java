package com.tinkoff.edu.app.repositories;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.enums.LoanResponceType;
import com.tinkoff.edu.app.enums.LoanType;

import java.util.*;
import java.util.stream.Collectors;

public class MapLoanCalcRepository implements LoanCalcRepository {

    Map<UUID, LoanResponce> loanResponses = new HashMap();
    Map<UUID, LoanRequest> loanRequestes = new HashMap();

    LoanResponce loanResponce;
    UUID requesrId;


    @Override
    public LoanResponce save(LoanRequest loanRequest, LoanResponceType responceType) {
        requesrId = UUID.randomUUID();
        loanResponce = new LoanResponce(responceType, requesrId);
        loanResponses.put(requesrId, loanResponce);
        loanRequestes.put(requesrId, loanRequest);

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
        loanResponses.put(requestId, loanResponce);
    }

    @Override
    public LoanResponce getResponce(LoanType loanType) {
        List<UUID> loanReq = loanRequestes.entrySet()
                .stream()
                .filter(loanRequestEntry -> Objects.equals(loanRequestEntry.getValue().getLoanType(), loanType))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());


        return loanResponses.entrySet()
                .stream()
                .filter(loanResponceEntry -> Objects.equals(loanResponceEntry.getValue().getRequestId(), loanReq.get(0)))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList()).get(0);
    }
}
