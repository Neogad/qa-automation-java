package com.tinkoff.edu.app;

import com.tinkoff.edu.app.enums.LoanResponceType;

public class LoanResponce {
    private LoanResponceType loanResponceType;
    private LoanRequest loanRequest;
    private int requestId;

    public LoanRequest getLoanRequest() {
        return loanRequest;
    }

    public int getRequestId() {
        return requestId;
    }

    public LoanResponce(LoanResponceType loanResponceType, LoanRequest loanRequest, int requestId) {
        this.loanResponceType = loanResponceType;
        this.loanRequest = loanRequest;
        this.requestId = requestId;
    }

    public LoanResponceType getLoanResponceType() {
        return loanResponceType;
    }

    public String toString(){
        return "Responce: "
                +"LoanRequestId "
                +this.getRequestId()
                +"Solution "
                +this.getLoanResponceType()
                +" "
                +this.getLoanRequest();

    }
}
