package ru.yandex.practicum.delivery;

public class StandardParcel extends Parcel {


    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    protected int getBaseCost() {
        return  2;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String getDescription() {
        return description;
    }


}
