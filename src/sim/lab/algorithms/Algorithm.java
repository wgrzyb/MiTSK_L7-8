package sim.lab.algorithms;

import sim.core.Manager;
import sim.core.SimStep;
import sim.monitors.MonitoredVar;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

/* Klasa reprezentująca algorytm */
public abstract class Algorithm extends SimStep {
    DoubleBinaryOperator f; /* równanie różniczkowe zwyczajne do rozwiązania */
    DoubleUnaryOperator solution; /* dokładne rozwiązanie */
    double x; /* obecna wartość x */
    public MonitoredVar monitoredX = new MonitoredVar(); /* monitorowana zmienna reprezentująca wyznaczone wartości x */
    public MonitoredVar monitoredM = new MonitoredVar(); /* monitorowana zmienna reprezentująca oczekiwane wartości dla x */

    public Algorithm(Manager mgr, DoubleBinaryOperator f, DoubleUnaryOperator solution, double x0) {
        super(mgr);
        this.f = f;
        this.solution = solution;
        this.x = x0;
    }
}
