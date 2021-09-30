package com.tinkoff.edu;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;
import com.tinkoff.edu.retrofit.Client;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JpaTest {
    private static EntityManagerFactory entityManagerFactory;
    private RequestSpecification request;

    @BeforeAll
    public static void setUpJpa() {
        entityManagerFactory = Persistence.createEntityManagerFactory("dbo");
    }

    @AfterAll
    public void setUpRestAssured() {
        request = given()
                .baseUri("http://localhost")
                .port(8080)
                .basePath("/dbo/api/")
                .header("X-API-VERSION", 1)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void shouldGetAccountByIdWhenExists() throws SQLException {
        final EntityManager em = entityManagerFactory.createEntityManager();

        final String newLogin = "login" + new Random().nextInt();
        final Client client = new Client(newLogin, "secret", "salt", LocalDateTime.now(), true);

        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();

        request.
                when().
                get("client/{id}", client.getId())
                .then()
                .statusCode(HttpStatus.SC_OK).
                body("id", equalTo(client.getId()),
                        "login", equalTo(client.getLogin()));

        em.getTransaction().begin();
        final Client clientSaved = em.find(Client.class, client.getId());
        em.remove(clientSaved);
        em.getTransaction().commit();

        em.close();
    }

    @Test
    public void shouldDeleteAccountByIdWhenExists() throws SQLException {
        final EntityManager em = entityManagerFactory.createEntityManager();

        final String newLogin = "login" + new Random().nextInt();
        final Client client = new Client(newLogin, "secret", "salt", LocalDateTime.now(), true);

        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();


        request.
                when()
               .delete("/client/{id}",client.getId())
                .then()
                .statusCode(200);

        em.getTransaction().begin();
        assertThrows(NullPointerException.class, () -> {
            Client clientdeleted = em.find(Client.class, client.getId());
        });


        em.close();
    }
}




