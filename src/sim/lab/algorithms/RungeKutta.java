package sim.lab.algorithms;

import sim.core.Manager;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

/* Klasa reprezentująca algorytm Runge-Kutty */
public class RungeKutta extends Algorithm {
    public RungeKutta(Manager mgr, DoubleBinaryOperator f, DoubleUnaryOperator solution, double x0) {
        super(mgr, f, solution, x0);
    }

    @Override
    /* Implementacja algorytmu Runge-Kutty */
    public void stateChange() {
        /* Ustawienie zmiennych */
        double xi = this.x;
        double ti = simTime();
        double delta_t = getTimeStep();
        /* Wyznaczenie xi+1 */
        double k1 = f.applyAsDouble(xi, ti);
        double k2 = f.applyAsDouble(xi+k1*delta_t/2,ti+delta_t/2);
        double k3 = f.applyAsDouble(xi+k2*delta_t/2,ti+delta_t/2);
        double k4 = f.applyAsDouble(xi+k3*delta_t/2,ti+delta_t/2);
        this.x = xi+delta_t*(k1+2*k2+2*k3+k4)/6; /* ustawienie xi+1 */
        this.monitoredX.setValue(this.x, ti+delta_t); /* ustawienie wartości monitorowanej zmiennej monitoredX */
        /* Obliczenie oczekiwanej wartości dla ti+delta_t */
        double m = solution.applyAsDouble(ti+delta_t);
        this.monitoredM.setValue(m, ti+delta_t); /* ustawienie wartości monitorowanej zmiennej monitoredM */
        /* Wyznaczenie błędu */
        double error = Math.abs((this.x-m)/m*100);
        System.out.printf("%.2f\t Algorytm Runge-Kutty: xi = "+xi+" \n\t\t Wyznaczono xi+1 = " + this.x + ".\n\t\t Oczekiwana wartość w chwili ti+delta_t = %.2f wynosi: " + m +".\n\t\t Błąd: %.2f%%\n", simTime(), ti+delta_t, error);
    }
}

