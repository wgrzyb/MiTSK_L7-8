import sim.lab.Simulation;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

public class Main {

    public static void main (String[] args) {
        /* Ustawienie parametrów */
        double startSimTime = 0.0; /* czas rozpoczęcia symulacji */
        double timeStep = 0.1; /* krok zmiany czasu */
//        double timeStep = 0.2; /* krok zmiany czasu */
//        double timeStep = 0.3; /* krok zmiany czasu */
        DoubleBinaryOperator f = (x, t) -> x-t*t; /* równanie różniczkowe zwyczajne do rozwiązania */
        DoubleUnaryOperator solution = (t) -> 2+2*t+t*t-Math.exp(t); /* dokładne rozwiązanie */
        double x0 = 1; /* wartość x dla t=0 */
        double endSimTime = 0.6; /* czas zakończenia symulacji */

        /* Utworzenie obiektu koordynatora symulacji */
        Simulation simulation = Simulation.getInstance(startSimTime, timeStep, f, solution, x0);
        /* Rozpoczęcie symulacji */
        simulation.simulate(endSimTime);
    }
}
