package com.tinkoff.edu;


import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.controllers.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanResponceType;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.exceptions.ValidateRequestException;
import com.tinkoff.edu.app.repositories.MapLoanCalcRepository;
import com.tinkoff.edu.app.services.PersonCalcService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private LoanRequest loanRequest;
    private static LoanCalcController sut;
    private String fullName = "Имя Фамилия Отчество";


    @BeforeAll
    public static void init() {
        sut = new LoanCalcController(new PersonCalcService(new MapLoanCalcRepository()));

    }


    @Test
    @DisplayName("Проверка что если request null приложение это обработает")
    public void shouldGetErrorWhenApplyNullRequest() {
        assertThrows(NullPointerException.class, () -> {
            sut.createRequest(null);
        });

    }

    @Test
    @DisplayName("Проверка что при 0 сумме мы обработаем ошибку")
    public void shouldGetErrorWhenApplyZeroAmountRequest() {

        this.loanRequest = new LoanRequest(10, 0, LoanType.PERSON, fullName);

        assertThrows(IllegalArgumentException.class, () -> {
            sut.createRequest(loanRequest);
        });
    }

    @Test
    @DisplayName("Проверка что при отрицательной сумме мы обработаем ошибку")
    public void shouldGetErrorWhenApplyNegativeAmountRequest() {

        this.loanRequest = new LoanRequest(10, -1, LoanType.PERSON, fullName);


        assertThrows(IllegalArgumentException.class, () -> {
            sut.createRequest(loanRequest);
        });

    }

    @Test
    @DisplayName("Проверка что при правильных значениях loanResponce = Approved")
    public void shouldGetApproveForPersonal1() throws ValidateRequestException {

        this.loanRequest = new LoanRequest(1, 9_000, LoanType.PERSON, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.APPROVE, loanResponce.getLoanResponceType(), "Responce is not Approve");
    }

    @Test
    @DisplayName("Проверка что при граничных значениях loanResponce = Approved")
    public void shouldGetApproveForPersonal3() throws ValidateRequestException {

        this.loanRequest = new LoanRequest(12, 10_000, LoanType.PERSON, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.APPROVE, loanResponce.getLoanResponceType(), "Responce is not Approve");
    }

    @Test
    @DisplayName("Проверка что при правильных значениях loanResponce = Decline")
    public void shouldGetDeclineForPersonal1() throws ValidateRequestException {

        this.loanRequest = new LoanRequest(23, 10_001, LoanType.PERSON, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not  Decline");
    }

    @Test
    @DisplayName("Проверка что при граничных значениях loanResponce = Decline")
    public void shouldGetDeclineForPersonal2() throws ValidateRequestException {

        this.loanRequest = new LoanRequest(13, 10_001, LoanType.PERSON, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
    }

    @Test
    @DisplayName("Проверка что при правильных значениях loanResponce = Approved")
    public void shouldGetApproveForOOO1() throws ValidateRequestException {

        this.loanRequest = new LoanRequest(10, 12_000, LoanType.OOO, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.APPROVE, loanResponce.getLoanResponceType(), "Responce is not Approve");
    }

    @Test
    @DisplayName("Проверка что при граничных значениях loanResponce = Approved")
    public void shouldGetApproveForOOO2() throws ValidateRequestException {

        this.loanRequest = new LoanRequest(11, 10_001, LoanType.OOO, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.APPROVE, loanResponce.getLoanResponceType(), "Responce is not Approve");
    }

    @Test
    @DisplayName("Проверка что при правильных значениях loanResponce = Decline")
    public void shouldGetDeclineForOOO1() throws ValidateRequestException {

        this.loanRequest = new LoanRequest(14, 3000_001, LoanType.OOO, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
    }

    @Test
    @DisplayName("Проверка что при правильных значениях loanResponce = Decline")
    public void shouldGetDeclineForOOO2() throws ValidateRequestException {

        this.loanRequest = new LoanRequest(14, 1_001, LoanType.OOO, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
    }

    @Test
    @DisplayName("Проверка что при граничных значениях loanResponce = Decline")
    public void shouldGetDeclineForOOO3() throws ValidateRequestException {

        this.loanRequest = new LoanRequest(23, 10_000, LoanType.PERSON, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
    }

    @Test
    @DisplayName("Проверка что при граничных значениях loanResponce = Decline")
    public void shouldGetDeclineForOOO4() throws ValidateRequestException {

        this.loanRequest = new LoanRequest(12, 10_001, LoanType.PERSON, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
    }

    @Test
    @DisplayName("Проверка что при любых значениях loanResponce = Decline")
    public void shouldGetDeclineForIP1() throws ValidateRequestException {

        this.loanRequest = new LoanRequest(12, 10_001, LoanType.IP, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Responce is not Decline");
    }

    @Test
    @DisplayName("Проверка сохранения данных в массив")
    public void shouldGeNotZero() throws ValidateRequestException {

        this.loanRequest = new LoanRequest(12, 10_001, LoanType.IP, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(loanResponce.getRequestId(), sut.getResponce(loanResponce.getRequestId()).getRequestId(), "Responce is not Saved");
    }

    @Test
    @DisplayName("Проверка изменения данных")
    public void shouldBeUpdate() throws ValidateRequestException {

        this.loanRequest = new LoanRequest(11, 1000, LoanType.PERSON, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        sut.updateResponce(loanResponce.getRequestId(), LoanResponceType.DECLINE);
        assertEquals(LoanResponceType.DECLINE, loanResponce.getLoanResponceType(), "Response does not updated");
    }

    @Test
    @DisplayName("Проверка что если fullName null приложение это обработает")
    public void shouldGetErrorWhenApplyNullRequestFullName() {
        this.loanRequest = new LoanRequest(10, 0, LoanType.PERSON, null);

        assertThrows(NullPointerException.class, () -> {
            sut.createRequest(loanRequest);
        });

    }

    @Test
    @DisplayName("Проверка что если loanType null приложение это обработает")
    public void shouldGetErrorWhenApplyNullRequestLoanType() {
        this.loanRequest = new LoanRequest(10, 0, null, fullName);

        assertThrows(NullPointerException.class, () -> {
            sut.createRequest(loanRequest);
        });

    }

    @Test
    @DisplayName("Проверка что если fullName <10 символов приложение это обработает")
    public void shouldGetErrorWhenFulNameLess10() {
        this.loanRequest = new LoanRequest(10, 1000, LoanType.PERSON, "123");

        assertThrows(ValidateRequestException.class, () -> {
            sut.createRequest(loanRequest);
        });

    }

    @Test
    @DisplayName("Проверка что если fullName >100 символов приложение это обработает")
    public void shouldGetErrorWhenFulNameMore100() {
        final String more100symbol = "999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";
        this.loanRequest = new LoanRequest(10, 1000, LoanType.PERSON, more100symbol);

        assertThrows(ValidateRequestException.class, () -> {
            sut.createRequest(loanRequest);
        });

    }


    @Test
    @DisplayName("Проверка поиска по LoanType")
    public void shouldGetAllWithLoanTypeOOO() throws ValidateRequestException {

        this.loanRequest = new LoanRequest(12, 10_001, LoanType.OOO, fullName);

        LoanResponce loanResponce = sut.createRequest(loanRequest);

        assertEquals(loanResponce.getRequestId(), sut.getResponce(LoanType.OOO).getRequestId(), "Responce is not OOO");
    }

}
