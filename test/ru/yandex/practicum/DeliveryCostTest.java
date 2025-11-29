package ru.yandex.practicum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.yandex.practicum.delivery.StandardParcel;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.ParcelBox;

public class DeliveryCostTest {

    // Вычисление стоимости посылки для каждого типа посылок

    @Test
    public void costOfStandardParcel() {
        StandardParcel parcelTest = new StandardParcel("Одежда", 10, "Москва", 1);
        assertEquals(20, parcelTest.calculateDeliveryCost());
    }

    @Test
    public void costOfFragileParcel() {
        FragileParcel parcelTest = new FragileParcel("Керамика",10,"Воронеж",1);
        assertEquals(40, parcelTest.calculateDeliveryCost());
    }

    @Test
    public void costOfPerishableParcel() {
        PerishableParcel parcelTest = new PerishableParcel("Продукты", 10, "Москва", 1, 1);
        assertEquals(30, parcelTest.calculateDeliveryCost());
    }

    // Работа метода isExpired

    @Test
    public void isExpiredMustBeFalse() {
        PerishableParcel parcelTest = new PerishableParcel("Мясо", 5, "Москва", 1, 5);
        parcelTest.currentDay = 5;
        assertFalse(parcelTest.isExpired());
    }

    @Test
    public void isExpiredMustBeTrue() {
        PerishableParcel parcelTest = new PerishableParcel("Рыба", 5, "Орел", 1, 5);
        parcelTest.currentDay = 7;
        assertTrue(parcelTest.isExpired());
    }

    // Проверка добавления новой посылки в коробку

    @Test
    public void addParcelMustBeOk() {
        ParcelBox<StandardParcel> testBox = new ParcelBox<>(100);
        testBox.addParcel(new StandardParcel("Одежда 1", 30, "Москва", 1));
        testBox.addParcel(new StandardParcel("Одежда 2", 40, "Москва", 2));
    }

    @Test
    public void addParcelBorderButMustBeOk() {
        ParcelBox<StandardParcel> testBox = new ParcelBox<>(100);
        testBox.addParcel(new StandardParcel("Одежда 1", 50, "Москва", 1));
        testBox.addParcel(new StandardParcel("Одежда 2", 50, "Москва", 2));
    }
    @Test
    public void addParcelExcess() {
        ParcelBox<StandardParcel> testBox = new ParcelBox<>(100);
        testBox.addParcel(new StandardParcel("Одежда 1", 50, "Москва", 1));
        testBox.addParcel(new StandardParcel("Одежда 2", 51, "Москва", 2));
    }
}