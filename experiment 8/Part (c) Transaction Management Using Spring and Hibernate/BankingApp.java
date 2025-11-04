package com.example.transaction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BankingApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfigTx.class);

        TransactionService service = context.getBean(TransactionService.class);

        // Example transaction
        service.transferMoney(1001, 1002, 500.0);

        context.close();
    }
}
