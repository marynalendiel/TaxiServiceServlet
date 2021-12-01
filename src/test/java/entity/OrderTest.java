package entity;

import com.taxiservice.model.entity.Order;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
    Order order;

    String startPoint;
    String finishPoint;
    double distance;
    double price;
    int numberOfPassengers;
    int userId;
    int carId;

    @Before
    public void setUp() {
        order = Order.createOrder();

        userId = 1;
        startPoint = "Kyiv";
        finishPoint = "London";
        distance = 12.5;
        price = 50.0;
        numberOfPassengers = 2;
        carId = 1;

        order.setUserId(userId);
        order.setStartPoint(startPoint);
        order.setFinishPoint(finishPoint);
        order.setDistance(distance);
        order.setPrice(price);
        order.setNumberOfPassengers(numberOfPassengers);
        order.setCarId(carId);
    }

    @Test
    public void shouldCreateOrder() {
        Assert.assertTrue(Order.createOrder() instanceof Order);
    }

    @Test
    public void shouldGetUserId() {
        Assert.assertEquals(userId, order.getUserId());
    }

    @Test
    public void shouldGetStartPoint() {
        Assert.assertEquals(startPoint, order.getStartPoint());
    }

    @Test
    public void shouldGetFinishPoint() {
        Assert.assertEquals(finishPoint, order.getFinishPoint());
    }

    @Test
    public void shouldGetPrice() {
        Assert.assertEquals(price, order.getPrice(), 0.0);
    }

    @Test
    public void shouldGetCarIdList() {
        Assert.assertTrue(order.getCarIdList().contains(carId));
    }

    @Test
    public void shouldSetUserId() {
        userId = 3;
        order.setUserId(userId);
        Assert.assertEquals(userId, order.getUserId());
    }

    @Test
    public void shouldSetStartPoint() {
        startPoint = "Svalyava";
        order.setStartPoint(startPoint);
        Assert.assertEquals(startPoint, order.getStartPoint());
    }

    @Test
    public void shouldSetFinishPoint() {
        finishPoint = "Goverla";
        order.setFinishPoint(finishPoint);
        Assert.assertEquals(finishPoint, order.getFinishPoint());
    }

    @Test
    public void shouldSetPrice() {
        price = 25.25;
        order.setPrice(price);
        Assert.assertEquals(price, order.getPrice(), 0.0);
    }

    @Test
    public void shouldSetCarId() {
        carId = 2;
        order.setCarId(carId);
        Assert.assertTrue(order.getCarIdList().contains(carId));
    }
}
