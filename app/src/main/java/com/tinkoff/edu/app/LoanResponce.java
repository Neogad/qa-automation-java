package com.tinkoff.edu.app;

import com.tinkoff.edu.app.enums.LoanResponceType;

import java.util.Objects;

public class LoanResponce {
    private LoanResponceType loanResponceType;
    private int requestId;

    public int getRequestId() {
        return requestId;
    }

    public LoanResponce(LoanResponceType loanResponceType, LoanRequest loanRequest, int requestId) {
        this.loanResponceType = loanResponceType;
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
                +this.getLoanResponceType();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanResponce that = (LoanResponce) o;
        return requestId == that.requestId && loanResponceType == that.loanResponceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanResponceType, requestId);
    }
}
