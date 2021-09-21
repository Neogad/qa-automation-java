package com.tinkoff.edu.app;

import com.tinkoff.edu.app.enums.LoanType;

import java.util.UUID;

/**
 * Class, Type ->objects,instances
 */
public class LoanRequest {
    private final int mounths;
    private final int amount;
    private final LoanType loanType;
    private final String fullName;
    private UUID requestId;



    public LoanRequest(UUID requestId ,String fullName,LoanType loanType,int amount, int mounths) {
        this.requestId=requestId;
        this.mounths = mounths;
        this.amount = amount;
        this.loanType = loanType;
        this.fullName = fullName;
    }

    public LoanRequest(int mounths, int amount, LoanType loanType, String fullName) {
        this.requestId=requestId;
        this.mounths = mounths;
        this.amount = amount;
        this.loanType = loanType;
        this.fullName = fullName;
    }

    public UUID getRequestId() {
        return requestId;
    }


    public String getFullName() {
        return fullName;
    }

    public int getAmount() {
        return amount;
    }

    public int getMounths() {
        return mounths;
    }

    public LoanType getLoanType() {
        return loanType;
    }


    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }


    public String toString() {
        return
                this.getRequestId()
                +";"
                +this.getFullName()
                + ";"
                + this.getLoanType()
                + ";"
                + this.getAmount()
                + ";"
                + this.getMounths()
                +";";


    }

}
