package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private final List<T> parcelBox;
    private final int maxWeight;
    private int totalWeight;

    public ParcelBox(int maxWeight) {
        this.parcelBox = new ArrayList<>();
        this.maxWeight = maxWeight;
        this.totalWeight = 0;
    }

    public void addParcel(T parcel) {
        if (totalWeight + parcel.getWeight() <= maxWeight) {
            parcelBox.add(parcel);
            totalWeight += parcel.getWeight();
        } else {
            System.out.println("Превышен максимальный допустимый вес - посылка не добавлена");
        }
    }

    public List<T> getAllParcels() {
        return parcelBox;
    }
}

