package com.taxiservice.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Order entity.
 *
 * @author Maryna Lendiel
 */
public class Order extends Entity {
    private static final long serialVersionUID = 2144243957566837126L;

    private String startPoint;
    private String finishPoint;
    private double distance;
    private double price;
    private int numberOfPassengers;
    private Date createTime;
    private int userId;
    private List<Integer> carIdList;

    private Order() {
        carIdList = new ArrayList<>();
    }

    public static Order createOrder() {
        return new Order();
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getFinishPoint() {
        return finishPoint;
    }

    public void setFinishPoint(String finishPoint) {
        this.finishPoint = finishPoint;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Integer> getCarIdList() {
        return carIdList;
    }

    public void setCarId(Integer carId) {
        carIdList.add(carId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.distance, distance) == 0 && Double.compare(order.price, price) == 0 && userId == order.userId && Objects.equals(startPoint, order.startPoint) && Objects.equals(finishPoint, order.finishPoint) && Objects.equals(createTime, order.createTime) && carIdList.equals(order.carIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPoint, finishPoint, distance, price, createTime, userId, carIdList);
    }

    @Override
    public String toString() {
        return "Order{" +
                "startPoint='" + startPoint + '\'' +
                ", finishPoint='" + finishPoint + '\'' +
                ", distance=" + distance +
                ", price=" + price +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", carIdList=" + carIdList +
                '}';
    }
}
