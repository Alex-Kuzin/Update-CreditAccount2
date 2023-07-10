package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {
    @Test//Процентная ставка отрицательное число
    public void shouldNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    CreditAccount account = new CreditAccount(
                            1_000,
                            5_000,
                            -15
                    );
                }
        );


    }

    @Test// Начальный баланс счёта отрицательное число
    public void shouldNegativeBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    CreditAccount account = new CreditAccount(
                            -1_000,
                            5_000, 15
                    );
                }
        );

    }

    @Test// Кредитный лимит отрицательное число
    public void shouldNegativeLimit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    CreditAccount account = new CreditAccount(
                            1_000,
                            -5_000,
                            15
                    );
                }
        );

    }

    @Test// Пополнение счета при нулевом балансе
    public void shouldAddToZeroBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test// Пополнение счета на ноль
    public void shouldAddZero() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(0);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test// Покупка на ноль
    public void shouldPayZero() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(0);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test//Попупка при нулевом балансе
    public void shouldPayToZeroBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.pay(3_000);

        Assertions.assertEquals(-3000, account.getBalance());
    }

    @Test// Пополнение счета при положительном балансе
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(4_000, account.getBalance());
    }

    @Test//Покупка при положительном балансе
    public void shouldPayToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                4_000,
                5_000,
                15
        );

        account.pay(3_000);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test//Покупка при положительном балансе
    public void shouldPayFullPurchase() {
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );

        account.pay(3_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test//Покупка при положительном балансе
    public void shouldPayFullPurchaseCreditLimit() {
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );

        account.pay(8_000);

        Assertions.assertEquals(-5_000, account.getBalance());
    }

    @Test//Попупка при негативном балансе
    public void shouldPayToNegativeBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.pay(3_000);
        account.pay(1_000);

        Assertions.assertEquals(-4_000, account.getBalance());
    }

    @Test//Попупка при негативном балансе выше лимита
    public void shouldPayToNegativeBalanceUpLimit() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.pay(8_000);
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test//Расчет процентов
    public void shouldYearChange() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.pay(1_000);

        Assertions.assertEquals(-150, account.yearChange());
    }
}
