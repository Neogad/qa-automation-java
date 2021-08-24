package com.tinkoff.edu;


import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.controllers.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.repositories.StaticVariableLoancalcRepository;
import com.tinkoff.edu.app.services.PersonCalcService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AppTest {
    private LoanRequest loanRequest;
    private LoanCalcController sut;


    @BeforeEach
    public void init() {
        //region Fixture | Arrage | |Given
        loanRequest = new LoanRequest(10, 100000, LoanType.PERSON);
        sut = new LoanCalcController(new PersonCalcService(new StaticVariableLoancalcRepository()));
        //endregion

    }

    @Test
    public void shouldGet1WhenFirstRequest() {

        //region Act | When
        LoanResponce loanResponce = sut.createRequest(loanRequest);
        //endregion
        //region Assert | Then
        assertEquals(1, loanResponce.getRequestId());
        //endregion
    }

    @Test
    public void shouldGetIncrementedIdWhenAnyCall() {
        //region Fixture | Arrage | |Given
        sut.setRequestId(2);
        //endregion

        //region Act | When
        LoanResponce loanResponce = sut.createRequest(loanRequest);
        //endregion

        //region Assert | Then
        assertEquals(3, loanResponce.getRequestId());
        //endregion

    }
}
