package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<Trackable> trackableParcels = new ArrayList<>();
    private static final ParcelBox<StandardParcel> standardBox = new ParcelBox<>(50);
    private static final ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(75);
    private static final ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(100);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    System.out.println("Введите новое местоположение:");
                    String newLocation = scanner.nextLine();
                    for (Trackable parcel : trackableParcels) {
                        parcel.reportStatus(newLocation);
                    }
                    break;
                case 5:
                    System.out.println("Выберите тип коробки: 1 - Стандартные, 2 - Хрупкие, 3 - Скоропортящиеся");
                    String boxType = scanner.nextLine();
                    switch (boxType) {
                        case "1" -> {
                            for (StandardParcel parcel : standardBox.getAllParcels()) {
                                System.out.println(parcel.getDescription());
                            }
                        }
                        case "2" -> {
                            for (FragileParcel parcel : fragileBox.getAllParcels()) {
                                System.out.println(parcel.getDescription());
                            }
                        }
                        case "3" -> {
                            for (PerishableParcel parcel : perishableBox.getAllParcels()) {
                                System.out.println(parcel.getDescription());
                            }
                        }
                        default -> System.out.println("Неизвестный тип коробки.");
                    }
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
                    break;
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Трекинг отправления");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Введите тип посылки: 1 - Стандартная, 2 - Хрупкая, 3 - Скоропортящаяся");
        String type = scanner.nextLine();

        String description;
        int weight;
        String deliveryAddress;
        int sendDay;
        int timeToLive;

        System.out.println("Введите описание посылки:");
        description = scanner.nextLine();

        System.out.println("Введите вес посылки:");
        weight = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите адрес доставки:");
        deliveryAddress = scanner.nextLine();

        System.out.println("Введите день отправки:");
        sendDay = Integer.parseInt(scanner.nextLine());

        Parcel parcel;
        switch (type) {
            case "1" -> {
                parcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                standardBox.addParcel((StandardParcel) parcel);
            }
            case "2" -> {
                parcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                fragileBox.addParcel((FragileParcel) parcel);
                trackableParcels.add((Trackable) parcel);
            }
            case "3" -> {
                System.out.println("Введите срок годности:");
                timeToLive = Integer.parseInt(scanner.nextLine());
                parcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                perishableBox.addParcel((PerishableParcel) parcel);
            }
            default -> {
                System.out.println("Неизвестный тип посылки.");
                return;
            }
        }

        allParcels.add(parcel);
    }


    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int totalCost = 0;
        for (Parcel parcel : allParcels) {
            totalCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех доставок: " + totalCost);
    }
}