package sim.lab.algorithms;

import sim.core.Manager;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

/* Klasa reprezentująca algorytm Eulera */
public class Euler extends Algorithm {
    public Euler(Manager mgr, DoubleBinaryOperator f, DoubleUnaryOperator solution, double x0) {
        super(mgr, f, solution, x0);
    }

    @Override
    /* Implementacja algorytmu Eulera */
    public void stateChange() {
        /* Ustawienie zmiennych */
        double xi = this.x;
        double ti = simTime();
        double delta_t = getTimeStep();
        /* Wyznaczenie xi+1 */
        this.x = xi+delta_t*f.applyAsDouble(xi, ti); /* ustawienie xi+1 */
        this.monitoredX.setValue(this.x, ti+delta_t); /* ustawienie wartości monitorowanej zmiennej monitoredX */
        /* Obliczenie oczekiwanej wartości dla ti+delta_t */
        double m = solution.applyAsDouble(ti+delta_t);
        this.monitoredM.setValue(m, ti+delta_t); /* ustawienie wartości monitorowanej zmiennej monitoredM */
        /* Wyznaczenie błędu */
        double error = Math.abs((this.x-m)/m*100);
        System.out.printf("%.2f\t Algorytm Eulera: xi = "+xi+" \n\t\t Wyznaczono xi+1 = " + this.x + ".\n\t\t Oczekiwana wartość w chwili ti+delta_t = %.2f wynosi: " + m +".\n\t\t Błąd: %.2f%%\n", simTime(), ti+delta_t, error);
    }
}
