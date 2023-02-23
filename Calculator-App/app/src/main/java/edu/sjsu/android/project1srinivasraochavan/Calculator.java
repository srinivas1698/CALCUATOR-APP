package edu.sjsu.android.project1srinivasraochavan;

public class Calculator {
    public static double calculate(double P, double J, int N, double T) {
        J = J / 12.0 / 100.0;
        N *= 12;
        T = T / 100.0;
        if (J == 0.0)
            return P / N + T;
        else
            return ((P * J) / (1 - Math.pow(1 + J, -N))) + T;
    }
}
