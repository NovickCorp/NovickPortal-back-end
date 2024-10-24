package com.novick.customers.util;

public class Arithmetic {

    public static String toFractionString(double value) {
        if (value == 0.125) {
            return "1/8";
        }

        if (value == 0.25) {
            return "1/4";
        }

        if (value == 0.375) {
            return "3/8";
        }

        if (value == 0.5) {
            return "1/2";
        }

        if (value == 0.625) {
            return "5/8";
        }

        if (value == 0.67) {
            return "2/3";
        }

        if (value == 0.75) {
            return "3/4";
        }

        if (value == 0.875) {
            return "7/8";
        }

        if (value == 1.33 || value == 1.34) {
            return "1 1/3";
        }

        if (value == 1.125) {
            return "1 1/8";
        }

        if (value == 1.25) {
            return "1 1/4";
        }

        if (value == 1.375) {
            return "1 3/8";
        }

        if (value == 1.5) {
            return "1 1/2";
        }

        if (value == 1.625) {
            return "1 5/8";
        }

        if (value == 1.75) {
            return "1 3/4";
        }

        if (value == 1.875) {
            return "1 7/8";
        }

        if (value == 2.0100000000000002) {
            return "2";
        }

        if (value == 1.0 || value == 2.0 || value == 3.0) {
            return Integer.toString((int) value);
        }

        return Double.toString(value);
    }
}
