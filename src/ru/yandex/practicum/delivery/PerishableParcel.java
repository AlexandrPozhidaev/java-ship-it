package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel { // СКОРОПОРТЯЩАЯСЯ

    private final int timeToLive;
    public int currentDay;
    private static final int BASE_COST = 3;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired() {
        return sendDay + timeToLive < currentDay;
    }

    @Override
    protected int getBaseCost() {
        return BASE_COST;
    }

}