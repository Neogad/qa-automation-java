package com.tinkoff.edu;


import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.controllers.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanResponceType;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.repositories.VariableLoancalcRepository;
import com.tinkoff.edu.app.services.PersonCalcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private LoanRequest loanRequest;
    private LoanCalcController sut;


    @BeforeEach
    public void init() {
        //region Fixture | Arrage | |Given
        loanRequest = new LoanRequest(10, 100000, LoanType.PERSON);
        //endregion

    }

    @Test
    @DisplayName("Проверка что при первом сообщении id будет инкрементироваться")
    public void shouldGet1WhenFirstRequest() {
        //region Given
        sut = new LoanCalcController(new PersonCalcService(new VariableLoancalcRepository()));
        //endregion
        //region Act | When
        LoanResponce loanResponce = sut.createRequest(loanRequest);
        //endregion
        //region Assert | Then
        assertEquals(1, loanResponce.getRequestId(), "id не инкрементируются");
        //endregion
    }

    @Test
    @DisplayName("Проверка что при любом сообщении id будет инкрементироваться")
    public void shouldGetIncrementedIdWhenAnyCall() {
        final int NON_DEFAULT_ANY_ID = 2;
        //region Given
        sut = new LoanCalcController(new PersonCalcService(new VariableLoancalcRepository(NON_DEFAULT_ANY_ID)));
        //endregion
        //region  When
        LoanResponce loanResponce = sut.createRequest(loanRequest);
        //endregion
        //region  Then
        assertEquals(NON_DEFAULT_ANY_ID + 1, loanResponce.getRequestId(), "id не инкрементируются");
        //endregion
    }

    @Test
    @DisplayName("Проверка что если request null приложение это обработает")
    public void shouldGetErrorWhenApplyNullRequest() {
        final int NON_DEFAULT_ANY_ID = 2;
        //region Given
        sut = new LoanCalcController(new PersonCalcService(new VariableLoancalcRepository(NON_DEFAULT_ANY_ID)));
        //endregion
        //region  When
        try {
            LoanResponce loanResponce = sut.createRequest(null);
        }
        //endregion
        //region  Then

        catch (IllegalArgumentException exception) {
            assertEquals(exception.getClass(), IllegalArgumentException.class);
        }
        //endregion
    }

    @Test
    @DisplayName("Проверка что при 0 сумме мы обработаем ошибку")
    public void shouldGetErrorWhenApplyZeroAmountRequest() {
        //region Given
        this.loanRequest = new LoanRequest(10, 0, LoanType.PERSON);
        sut = new LoanCalcController(new PersonCalcService(new VariableLoancalcRepository()));
        //endregion
        //region  When
        try {
            LoanResponce loanResponce = sut.createRequest(loanRequest);
        }
        //endregion
        //region  Then

        catch (IllegalArgumentException exception) {
            assertEquals(exception.getClass(), IllegalArgumentException.class);
        }
        //endregion
    }

    @Test
    @DisplayName("Проверка что при отрицательной сумме мы обработаем ошибку")
    public void shouldGetErrorWhenApplyNegativeAmountRequest() {
        //region Given
        this.loanRequest = new LoanRequest(10, -1, LoanType.PERSON);
        sut = new LoanCalcController(new PersonCalcService(new VariableLoancalcRepository()));
        //endregion
        //region  When
        try {
            LoanResponce loanResponce = sut.createRequest(loanRequest);
        }
        //endregion
        //region  Then
        catch (IllegalArgumentException exception) {
            assertEquals(exception.getClass(), IllegalArgumentException.class);
        }
        //endregion
    }

    @Test
    @DisplayName("Проверка что при правильных значениях loanResponce = Approved")
    public void shouldGetApproveForPersonal1() {
        //region Given
        this.loanRequest = new LoanRequest(1, 9_000, LoanType.PERSON);
        sut = new LoanCalcController(new PersonCalcService(new VariableLoancalcRepository()));
        //endregion
        //region  When
        LoanResponce loanResponce = sut.createRequest(loanRequest);
        //endregion
        //region  Then
        assertEquals(LoanResponceType.APPROVE, loanResponce.getLoanResponceType(), "Responce is not Approve");
        //endregion
    }

    @Test
    @DisplayName("Проверка что при граничных значениях loanResponce = Approved")
    public void shouldGetApproveForPersonal3() {
        //region Given
        this.loanRequest = new LoanRequest(12, 10_000, LoanType.PERSON);
        sut = new LoanCalcController(new PersonCalcService(new VariableLoancalcRepository()));
        //endregion
        //region  When
        LoanResponce loanResponce = sut.createRequest(loanRequest);
        //endregion
        //region  Then
        assertEquals(LoanResponceType.APPROVE, loanResponce.getLoanResponceType(), "Responce is not Approve");
        //endregion
    }

    @Test
    @DisplayName("Проверка что при правильных значениях loanResponce = Decline")
    public void shouldGetDeclineForPersonal1() {
        //region Given
        this.loanRequest = new LoanRequest(23, 10_001, LoanType.PERSON);
        sut = new LoanCalcController(new PersonCalcService(new VariableLoancalcRepository()));
        //endregion
        //region  When
        LoanResponce loanResponce = sut.createRequest(loanRequest);
        //endregion
        //region  Then
        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not  Decline");
        //endregion
    }

    @Test
    @DisplayName("Проверка что при граничных значениях loanResponce = Decline")
    public void shouldGetDeclineForPersonal2() {
        //region Given
        this.loanRequest = new LoanRequest(13, 10_001, LoanType.PERSON);
        sut = new LoanCalcController(new PersonCalcService(new VariableLoancalcRepository()));
        //endregion
        //region  When
        LoanResponce loanResponce = sut.createRequest(loanRequest);
        //endregion
        //region  Then
        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
        //endregion
    }

    @Test
    @DisplayName("Проверка что при правильных значениях loanResponce = Approved")
    public void shouldGetApproveForOOO1() {
        //region Given
        this.loanRequest = new LoanRequest(10, 12_000, LoanType.OOO);
        sut = new LoanCalcController(new PersonCalcService(new VariableLoancalcRepository()));
        //endregion
        //region  When
        LoanResponce loanResponce = sut.createRequest(loanRequest);
        //endregion
        //region  Then
        assertEquals(LoanResponceType.APPROVE, loanResponce.getLoanResponceType(), "Responce is not Approve");
        //endregion
    }

    @Test
    @DisplayName("Проверка что при граничных значениях loanResponce = Approved")
    public void shouldGetApproveForOOO2() {
        //region Given
        this.loanRequest = new LoanRequest(11, 10_001, LoanType.OOO);
        sut = new LoanCalcController(new PersonCalcService(new VariableLoancalcRepository()));
        //endregion
        //region  When
        LoanResponce loanResponce = sut.createRequest(loanRequest);
        //endregion
        //region  Then
        assertEquals(LoanResponceType.APPROVE, loanResponce.getLoanResponceType(), "Responce is not Approve");
        //endregion
    }

    @Test
    @DisplayName("Проверка что при правильных значениях loanResponce = Decline")
    public void shouldGetDeclineForOOO1() {
        //region Given
        this.loanRequest = new LoanRequest(14, 3000_001, LoanType.OOO);
        sut = new LoanCalcController(new PersonCalcService(new VariableLoancalcRepository()));
        //endregion
        //region  When
        LoanResponce loanResponce = sut.createRequest(loanRequest);
        //endregion
        //region  Then
        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
        //endregion
    }

    @Test
    @DisplayName("Проверка что при правильных значениях loanResponce = Decline")
    public void shouldGetDeclineForOOO2() {
        //region Given
        this.loanRequest = new LoanRequest(14, 1_001, LoanType.OOO);
        sut = new LoanCalcController(new PersonCalcService(new VariableLoancalcRepository()));
        //endregion
        //region  When
        LoanResponce loanResponce = sut.createRequest(loanRequest);
        //endregion
        //region  Then
        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
        //endregion
    }

    @Test
    @DisplayName("Проверка что при граничных значениях loanResponce = Decline")
    public void shouldGetDeclineForOOO3() {
        //region Given
        this.loanRequest = new LoanRequest(23, 10_000, LoanType.PERSON);
        sut = new LoanCalcController(new PersonCalcService(new VariableLoancalcRepository()));
        //endregion
        //region  When
        LoanResponce loanResponce = sut.createRequest(loanRequest);
        //endregion
        //region  Then
        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
        //endregion
    }

    @Test
    @DisplayName("Проверка что при граничных значениях loanResponce = Decline")
    public void shouldGetDeclineForOOO4() {
        //region Given
        this.loanRequest = new LoanRequest(12, 10_001, LoanType.PERSON);
        sut = new LoanCalcController(new PersonCalcService(new VariableLoancalcRepository()));
        //endregion
        //region  When
        LoanResponce loanResponce = sut.createRequest(loanRequest);
        //endregion
        //region  Then
        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
        //endregion
    }

    @Test
    @DisplayName("Проверка что при любых значениях loanResponce = Decline")
    public void shouldGetDeclineForIP1() {
        //region Given
        this.loanRequest = new LoanRequest(12, 10_001, LoanType.IP);
        sut = new LoanCalcController(new PersonCalcService(new VariableLoancalcRepository()));
        //endregion
        //region  When
        LoanResponce loanResponce = sut.createRequest(loanRequest);
        //endregion
        //region  Then
        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
        //endregion
    }


}
