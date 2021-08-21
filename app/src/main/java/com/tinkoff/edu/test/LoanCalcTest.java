package com.tinkoff.edu.test;

import com.tinkoff.edu.app.*;


/**
 * Hello world!
 */
public class LoanCalcTest {

    public static void main(String... args) {
        LoanRequest loanRequest = new LoanRequest(10, 100000, LoanType.PERSON);

        LoanCalcController loanCalcController = new LoanCalcController(new PersonCalcService(new StaticVariableLoancalcRepository()));//вариант с интерфейсом

        LoanCalcController loanCalcController1 = new LoanCalcController(new StaticVariableLoancalcRepository());//вариант с наследованием

        LoanResponce loanResponce = loanCalcController.createRequest(loanRequest);
        System.out.println(loanResponce);

    }
}
