package com.tinkoff.edu;


import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.controllers.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanResponceType;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.exceptions.ValidateRequestException;
import com.tinkoff.edu.app.repositories.FileLoanCalcRepository;
import com.tinkoff.edu.app.services.PersonCalcService;
import com.tinkoff.edu.retrofit.Client;
import com.tinkoff.edu.retrofit.ClientService;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.sql.*;
import java.time.Instant;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private  RequestSpecification requestSpecification;
    private LoanRequest loanRequest;
    private static LoanCalcController sut;
    private String fullName = "Имя Фамилия Отчество";
    private  OkHttpClient.Builder httpClient;
    private Retrofit retrofit;
    ClientService clientService;

    private RequestSpecification request;
    private Connection connection;


    @BeforeEach
    public void setUpDbConnectcon() throws SQLException{
        connection = DriverManager.getConnection("jdbc:derby://localhost/dbo-db");
    }

    @BeforeEach
    public void closeDbConnecton()throws SQLException{
        connection.close();
    }

    @BeforeEach
    public  void initRequest(){
        requestSpecification = given()
                .baseUri("http://localhost")
                .port(8080)
                .basePath("/dbo/api/")
                .header("X-API-VERSION", 1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());


        httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("http://localhost:8080/dbo/api/")
                .client(httpClient.build())
                .build();

        clientService= retrofit.create(ClientService.class);
    }

    @BeforeAll
    public static void init() {
        sut = new LoanCalcController(new PersonCalcService(new FileLoanCalcRepository()));

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
        loanResponce = sut.getResponce(loanResponce.getRequestId());
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

    @Test
    public void shouldGetClientById() {
        requestSpecification.when()
                .get("/client/{id}",1)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id",equalTo(1),
                        "login",equalTo("admin@acme.com"));
    }

    @Test
    public void shouldErrorWhenAccountNotExist() {
        requestSpecification.when()
                .get("/client/{id}",0)
                .then()
                .statusCode(404);
    }


    @Test
    public void shouldErrorWhenAccountNotExistDelete() {
        requestSpecification.when()
                .body("{\n"+
                        " \"login\": \"test@mail.com\",\n"+
                        " \"salt\": \"some-salt\",\n"+
                        " \"secret\":\"749f09bade8aca7556749f09bade8aca7556\"\n"+
                        "}").
                post("client");

        requestSpecification.when()
                .delete("/client/{id}",3)
                .then()
                .statusCode(500);

    }

    @Test
    public void shouldDeleteById() {
       Response response = requestSpecification.when()
                .body("{\n"+
                        " \"login\": \"t12e1st1@mail.com\",\n"+
                        " \"salt\": \"some-salt\",\n"+
                        " \"secret\":\"749f09bade8aca7556749f09bade8aca7556\"\n"+
                        "}").
                post("client")
               ;

        JsonPath jsonPathEvaluator = response.jsonPath();
        int id = jsonPathEvaluator.get("id");

       String s= response.getBody().asString();
        requestSpecification.when()
                .delete("/client/{id}",id)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetAllClients() throws IOException {
        assertEquals(1,clientService.getClients().execute().body().get(0).getId());
    }

    @Test
    public void shouldCreateClient() throws IOException {
        Client client = new Client("1333456@test.com","somesalt","5aba80f0c9f7cfb0c7e8d5123aad85e8b384872e070c13a8fe6d11f58327934b");
        clientService.createClient(client)
                .execute();
        List<Client>  clients = clientService.getClients().execute().body();
        assertEquals(client.getLogin(),clients.get(clients.size()-1).getLogin());

    }

}
