package com.taxiservice.model.entity;

import java.util.Objects;

/**
 * Car entity.
 *
 * @author Maryna Lendiel
 */
public class Car extends Entity {
    private static final long serialVersionUID = 1623798010637059716L;

    private String brand;
    private String model;
    private String carNumber;
    private int numberOfSeats;
    private String category;
    private String status;
    private String description;

    private Car() { }

    public static Car createCar() {
        return new Car();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return numberOfSeats == car.numberOfSeats && Objects.equals(carNumber, car.carNumber) && Objects.equals(category, car.category) && Objects.equals(status, car.status) && description.equals(car.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carNumber, numberOfSeats, category, status, description);
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
