package com.tinkoff.edu.test;

import com.tinkoff.edu.app.LoanCalcController;
import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.LoanType;


/**
 * Hello world!
 *
 */
public class LoanCalcTest {

    public static void main(String... args) {
        LoanRequest loanRequest = new LoanRequest(10,100000, LoanType.IP);
        LoanCalcController loanCalcController = new LoanCalcController();
        LoanResponce loanResponce = loanCalcController.createRequest(loanRequest);
        System.out.println(loanResponce);

    }
}
