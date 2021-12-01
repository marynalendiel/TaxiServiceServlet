package entity;

import com.taxiservice.model.entity.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarTest {
    Car car;
    String brand;
    String model;
    String carNumber;
    int numberOfSeats;
    String category;
    String status;
    String description;

    @Before
    public void setUp(){
        car = Car.createCar();

        brand = "Chevrolet";
        model = "Impala";
        carNumber = "";
        numberOfSeats = 4;
        category = "comfort";
        status = "to order";
        description = "Chevrolet Impala 67'";

        car.setBrand(brand);
        car.setModel(model);
        car.setCarNumber(carNumber);
        car.setNumberOfSeats(numberOfSeats);
        car.setCategory(category);
        car.setStatus(status);
        car.setDescription(description);
    }

    @Test
    public void shouldCreateCar() {
        Assert.assertTrue(Car.createCar() instanceof Car);
    }

    @Test
    public void shouldGetBrand() {
        Assert.assertEquals(brand, car.getBrand());
    }

    @Test
    public void shouldGetModel() {
        Assert.assertEquals(model, car.getModel());
    }

    @Test
    public void shouldGetNumberOfSeats() {
        Assert.assertEquals(numberOfSeats, car.getNumberOfSeats());
    }

    @Test
    public void shouldGetCarCategory() {
        Assert.assertEquals(category, car.getCategory());
    }

    @Test
    public void shouldGetCarStatus() {
        Assert.assertEquals(status, car.getStatus());
    }

    @Test
    public void shouldGetCarDescription() {
        Assert.assertEquals(description, car.getDescription());
    }

    @Test
    public void shouldSetCategoryToCar() {
        category = "minivan";
        car.setCategory(category);
        Assert.assertEquals(category, car.getCategory());
    }

    @Test
    public void shouldSetNumberOfSeats() {
        numberOfSeats = 5;
        car.setNumberOfSeats(numberOfSeats);
        Assert.assertEquals(numberOfSeats, car.getNumberOfSeats());
    }

    @Test
    public void shouldSetCarStatus() {
        status = "inactive";
        car.setStatus(status);
        Assert.assertEquals(status, car.getStatus());
    }

    @Test
    public void shouldSetCarDescription() {
        description = "asdfasdfasdf";
        car.setDescription(description);
        Assert.assertEquals(description, car.getDescription());
    }
}
