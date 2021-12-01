package com.taxiservice.util;

import org.apache.commons.math3.util.Precision;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Class calculates order price considering selected car category and
 * discount availability for specified user.
 *
 * @author Maryna Lendiel
 */
public class OrderPriceCalculator {

    private static final double PRICE = 20;
    private static Map<String, Double> priceList = new TreeMap<>();

    static {
        priceList.put("standard", 0.0);
        priceList.put("comfort", 15.0);
        priceList.put("minivan", 20.0);
    }

    public static double calculate(double distance, boolean isDiscount, String category) {
        double calc = distance * PRICE;
        double discount = 0;
        // order price depends on selected car category
        if (priceList.get(category) != null) {
            calc = calc + calc * priceList.get(category) / 100;
        }

        // if user has discount his order is 10% cheaper
        if (isDiscount) {
            discount = calc * 10 / 100;
        }
        Logger.getLogger(OrderPriceCalculator.class).info(Precision.round(calc - discount, 2));
        return Precision.round(calc - discount, 2);
    }
}