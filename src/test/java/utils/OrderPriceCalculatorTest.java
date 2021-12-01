package utils;

import com.taxiservice.util.OrderDistanceCalculator;
import com.taxiservice.util.OrderPriceCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderPriceCalculatorTest {
    double distance;
    double trueDiscount;
    double falseDiscount;
    String category;

    @Before
    public void setUp() {
        distance = 10.0;
        falseDiscount = 230.0;
        trueDiscount = 207.0;
        category = "comfort";
    }

    @Test
    public void shouldCountPriceWithoutDiscount() {
        Assert.assertEquals(falseDiscount, OrderPriceCalculator.calculate(distance, false, category), 0.0);
    }

    @Test
    public void shouldCountPriceWithDiscount() {
        Assert.assertEquals(trueDiscount, OrderPriceCalculator.calculate(distance, true, category), 0.0);
    }
}
