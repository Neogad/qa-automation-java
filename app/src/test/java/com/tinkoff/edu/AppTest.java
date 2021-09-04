package com.tinkoff.edu;


import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.controllers.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanResponceType;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.repositories.ArrayLoanCalcRepository;
import com.tinkoff.edu.app.services.PersonCalcService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private LoanRequest loanRequest;
    private static LoanCalcController sut;
    private String fullName = "Имя Фамилия Отчество";


    @BeforeAll
    public static void init() {
        sut = new LoanCalcController(new PersonCalcService(new ArrayLoanCalcRepository()));

    }


    @Test
    @DisplayName("Проверка что если request null приложение это обработает")
    public void shouldGetErrorWhenApplyNullRequest() {
        this.loanRequest = new LoanRequest(10, 0, LoanType.PERSON, fullName);

        try {
            LoanResponce loanResponce = sut.createRequest(null);
        } catch (IllegalArgumentException exception) {
            assertEquals(exception.getClass(), IllegalArgumentException.class);
        }
    }

    @Test
    @DisplayName("Проверка что при 0 сумме мы обработаем ошибку")
    public void shouldGetErrorWhenApplyZeroAmountRequest() {

        this.loanRequest = new LoanRequest(10, 0, LoanType.PERSON, fullName);

        try {
            LoanResponce loanResponce = sut.createRequest(loanRequest);
        } catch (IllegalArgumentException exception) {
            assertEquals(exception.getClass(), IllegalArgumentException.class);
        }
    }

    @Test
    @DisplayName("Проверка что при отрицательной сумме мы обработаем ошибку")
    public void shouldGetErrorWhenApplyNegativeAmountRequest() {

        this.loanRequest = new LoanRequest(10, -1, LoanType.PERSON, fullName);

        try {
            LoanResponce loanResponce = sut.createRequest(loanRequest);
        } catch (IllegalArgumentException exception) {
            assertEquals(exception.getClass(), IllegalArgumentException.class);
        }
    }

    @Test
    @DisplayName("Проверка что при правильных значениях loanResponce = Approved")
    public void shouldGetApproveForPersonal1() {

        this.loanRequest = new LoanRequest(1, 9_000, LoanType.PERSON, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.APPROVE, loanResponce.getLoanResponceType(), "Responce is not Approve");
    }

    @Test
    @DisplayName("Проверка что при граничных значениях loanResponce = Approved")
    public void shouldGetApproveForPersonal3() {

        this.loanRequest = new LoanRequest(12, 10_000, LoanType.PERSON, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.APPROVE, loanResponce.getLoanResponceType(), "Responce is not Approve");
    }

    @Test
    @DisplayName("Проверка что при правильных значениях loanResponce = Decline")
    public void shouldGetDeclineForPersonal1() {

        this.loanRequest = new LoanRequest(23, 10_001, LoanType.PERSON, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not  Decline");
    }

    @Test
    @DisplayName("Проверка что при граничных значениях loanResponce = Decline")
    public void shouldGetDeclineForPersonal2() {

        this.loanRequest = new LoanRequest(13, 10_001, LoanType.PERSON, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
    }

    @Test
    @DisplayName("Проверка что при правильных значениях loanResponce = Approved")
    public void shouldGetApproveForOOO1() {

        this.loanRequest = new LoanRequest(10, 12_000, LoanType.OOO, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.APPROVE, loanResponce.getLoanResponceType(), "Responce is not Approve");
    }

    @Test
    @DisplayName("Проверка что при граничных значениях loanResponce = Approved")
    public void shouldGetApproveForOOO2() {

        this.loanRequest = new LoanRequest(11, 10_001, LoanType.OOO, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.APPROVE, loanResponce.getLoanResponceType(), "Responce is not Approve");
    }

    @Test
    @DisplayName("Проверка что при правильных значениях loanResponce = Decline")
    public void shouldGetDeclineForOOO1() {

        this.loanRequest = new LoanRequest(14, 3000_001, LoanType.OOO, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
    }

    @Test
    @DisplayName("Проверка что при правильных значениях loanResponce = Decline")
    public void shouldGetDeclineForOOO2() {

        this.loanRequest = new LoanRequest(14, 1_001, LoanType.OOO, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
    }

    @Test
    @DisplayName("Проверка что при граничных значениях loanResponce = Decline")
    public void shouldGetDeclineForOOO3() {

        this.loanRequest = new LoanRequest(23, 10_000, LoanType.PERSON, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
    }

    @Test
    @DisplayName("Проверка что при граничных значениях loanResponce = Decline")
    public void shouldGetDeclineForOOO4() {

        this.loanRequest = new LoanRequest(12, 10_001, LoanType.PERSON, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
    }

    @Test
    @DisplayName("Проверка что при любых значениях loanResponce = Decline")
    public void shouldGetDeclineForIP1() {

        this.loanRequest = new LoanRequest(12, 10_001, LoanType.IP, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
    }

    @Test
    @DisplayName("Проверка сохранения данных в массив")
    public void shouldGeNotZero() {

        this.loanRequest = new LoanRequest(12, 10_001, LoanType.IP, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);


        assertEquals(loanResponce.getRequestId(), sut.getResponce(loanResponce.getRequestId()).getRequestId(), "Responce is not Saved");
    }

    @Test
    @DisplayName("Проверка изменения данных")
    public void shouldBeUpdatet() {

        this.loanRequest = new LoanRequest(11, 1000, LoanType.PERSON, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        sut.updateResponce(loanResponce.getRequestId() ,LoanResponceType.DECLINE);
        assertEquals(LoanResponceType.DECLINE,loanResponce.getLoanResponceType(),"Response does not updated");
    }



}
