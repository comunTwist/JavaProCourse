//* Создать базу данных «Банк» с таблицами «Пользователи»,
//«Транзакции», «Счета» и «Курсы валют». Счет бывает 3-х видов:
//USD, EUR, UAH. Написать запросы для пополнения счета в нужной
//валюте, перевода средств с одного счета на другой, конвертации
//валюты по курсу в рамках счетов одного пользователя. Написать
//запрос для получения суммарных средств на счету одного
//пользователя в UAH (расчет по курсу).

package com.gmail.agentup;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DrWhite");
        EntityManager em = emf.createEntityManager();
        Service service = new Service(em);
        Scanner scanner = new Scanner(System.in);
        Console console = new Console(service, scanner);
        try {
            service.createNewExchange(28.5,35.25,1);
            while (true) {
                service.getExchangeList();
                service.getUserList();
                console.user();
            }
        } finally {
            em.close();
            emf.close();
            scanner.close();
        }
    }
}
