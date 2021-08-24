package com.tinkoff.edu.app.repositories;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.enums.LoanResponceType;

public class StaticVariableLoancalcRepository implements LoanCalcRepository {


    private int requestId;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    /**
     * TODO persists request
     *
     * @return request id
     */
    public LoanResponce save(LoanRequest loanRequest, LoanResponceType responceType) {
        return new LoanResponce(responceType, loanRequest, ++requestId);
    }


}
