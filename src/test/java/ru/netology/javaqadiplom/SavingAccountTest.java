package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test // создание счета с балансом ниже минимального (исключения)
    public void throwExceptionForBalanceLessMinimum() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    500,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    @Test //создание счета с балансом выше максимального (исключения)
    public void throwExceptionForBalanceMoreMaximum() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    11_000,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    @Test // создание счета где максимум меньше минимум (исключения)
    public void throwExceptionForBalanceMaximumLessThanMinimum() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    10_000,
                    1_000,
                    5
            );
        });
    }

    @Test // создание счета где процентная ставка ниже нуля (исключения)
    public void throwExceptionWhenInterestRateIsBelowZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    1_000,
                    10_000,
                    -5
            );
        });
    }


    @Test // Пополнение в пределах максимального баланса
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }


    @Test // Пополнение на ноль
    public void shouldBeAddedZero () {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test // Пополнение при максимальном балансе
    public void shouldBeReplenishedAtTheMaximumBalance() {
        SavingAccount account = new SavingAccount(
                10_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(10_000, account.getBalance());
    }

    @Test // Пополнение выше максимального баланса
    public void shouldBeToppedUpAboveTheMaximumBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(9_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test // Покупка в пределах текущего баланса
    public void shouldPayLessThanTheCurrentBalance() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        account.pay(3_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test // Покупка равная нулю
    public void shouldPayZero() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        account.pay(0);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test // Траты всей суммы
    public void shouldPayUntilBalanceIsZero() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        account.pay(5_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test // Траты до баланса меньше нуля
    public void shouldPayUpToBalanceLessThanZero() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        account.pay(6_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test // Траты при нулевом балансе
    public void payWithZeroBalance() {
        SavingAccount account = new SavingAccount(
                0,
                0,
                10_000,
                5
        );

        account.pay(6_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test // Расчет процентов
    public void shouldBeCalculatedInterestNoPay() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );


        Assertions.assertEquals(250, account.yearChange());
    }
}
