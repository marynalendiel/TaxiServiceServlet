package utils;

import com.taxiservice.util.OrderDistanceCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderDistanceCalculatorTest {
    String startPoint;
    String finishPoint;

    @Before
    public void setUp() {
        startPoint = "Kyiv, Lomonosova, 65";
        finishPoint = "Kyiv, Lomonosova, 85";
//        startPoint = "Київ, Ломоносова 65";
//        finishPoint = "Київ, Ломоносова, 85";
    }

    @Test
    public void calculateDistanceTest() {
        double distance = OrderDistanceCalculator.calculate(startPoint,finishPoint);
        double exp = 0.25;
        assertEquals(exp, distance, 0.5);
    }

}
