package com.tinkoff.edu.test;

import com.tinkoff.edu.app.LoanCalcController;

/**
 * Hello world!
 *
 */
public class LoanCalcTest {

    public static void main(String... args) {
        int reqestId = LoanCalcController.createRequest();
        System.out.println("request id  1 = "+reqestId);
    }
}
