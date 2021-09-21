package com.tinkoff.edu.app;

import com.tinkoff.edu.app.enums.LoanResponceType;

import java.util.Objects;
import java.util.UUID;

public class LoanResponce {

    private LoanResponceType loanResponceType;
    private UUID requestId;

    public UUID getRequestId() {
        return requestId;
    }

    public void setLoanResponceType(LoanResponceType loanResponceType) {
        this.loanResponceType = loanResponceType;
    }

    public LoanResponceType getLoanResponceType() {
        return loanResponceType;
    }

    public LoanResponce(LoanResponceType loanResponceType, UUID requestId) {
        this.loanResponceType = loanResponceType;
        this.requestId = requestId;
    }

    public String toString(){
        return  this.getRequestId()
                +";"
                +this.getLoanResponceType()
                +";";

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
