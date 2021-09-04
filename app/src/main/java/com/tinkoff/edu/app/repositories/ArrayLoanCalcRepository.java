package com.tinkoff.edu.app.repositories;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.enums.LoanResponceType;

import java.util.UUID;

public class ArrayLoanCalcRepository implements LoanCalcRepository {

    LoanResponce[] loanResponses = new LoanResponce[100];
    LoanResponce loanResponce;
    int responseCount;

    @Override
    public LoanResponce save(LoanRequest loanRequest, LoanResponceType responceType) {
        loanResponce = new LoanResponce(responceType, UUID.randomUUID());
        responseCount = ResponseCount();
        System.out.println(responseCount);
        if (responseCount == 100) {
            throw new ArrayIndexOutOfBoundsException("responseCount =100");
        } else if (responseCount == 0) {
            loanResponses[0] = loanResponce;
            return loanResponce;
        } else {
            int counter = 0;
            while (counter != responseCount) {
                counter++;
            }


            loanResponses[counter++] = loanResponce;
        }
        return loanResponce;
    }


    private int ResponseCount() {
        int counter = 0;
        for (; counter < loanResponses.length; counter++) {
            if (loanResponses[counter] == null) {
                break;
            }
        }
        return counter;
    }

    @Override
    public LoanResponce getResponce(UUID uuid) {
        int i = 0;
        for (; i < loanResponses.length-1; i++) {

            if (loanResponses[i].getRequestId() == uuid) {
                break;
            }
        }
        return loanResponses[i];
    }

    @Override
    public void updateResponce(UUID requestId, LoanResponceType loanResponceType) {
        int i = 0;
        for (; i < loanResponses.length; i++) {
            if (loanResponses[i].getRequestId() == requestId) {
                loanResponses[i].setLoanResponceType(loanResponceType);
                break;
            }
        }
    }


}
