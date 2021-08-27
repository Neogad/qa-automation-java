package com.tinkoff.edu.app.repositories;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.enums.LoanResponceType;

public class VariableLoancalcRepository implements LoanCalcRepository {


    private int requestId;


    public VariableLoancalcRepository(int requestId) {
        this.requestId = requestId;
    }

    public VariableLoancalcRepository() {
        this(0);
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
