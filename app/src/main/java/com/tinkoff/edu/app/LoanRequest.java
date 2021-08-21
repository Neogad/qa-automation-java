package com.tinkoff.edu.app;

/**
 * Class, Type ->objects,instances
 */
public class LoanRequest {
    private final int mounths;
    private final int amount;
    private final LoanType loanType;


    public LoanRequest(int mounths, int amount, LoanType loanType) {
        this.mounths = mounths;
        this.amount = amount;
        this.loanType = loanType;
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


    public String toString() {
        return "Request: "
                + "Loan type "
                + this.getLoanType()
                + "; Amount "
                + this.getAmount()
                + "; Months "
                + this.getMounths();


    }

}
