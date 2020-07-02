package sim.lab;

import sim.core.Manager;
import sim.lab.algorithms.Euler;
import sim.lab.algorithms.Algorithm;
import sim.lab.algorithms.RungeKutta;
import sim.monitors.Diagram;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

/* Klasa reprezentująca koordynatora symulacji */
public class Simulation {
    private static Simulation simulation; // Singleton
    public Manager mgr;
    private final Algorithm euler;
    private final Algorithm rungeKutta;

    public static Simulation getInstance(double startSimTime, double timeStep, DoubleBinaryOperator f, DoubleUnaryOperator solution, double x0) {
        if (simulation == null) {
            simulation = new Simulation(startSimTime, timeStep, f, solution, x0);
        }
        return simulation;
    }

    public Simulation(double startSimTime, double timeStep, DoubleBinaryOperator f, DoubleUnaryOperator solution, double x0) {
        this.mgr = Manager.getInstance(startSimTime, timeStep);
        this.euler = new Euler(mgr, f, solution, x0);
        this.rungeKutta = new RungeKutta(mgr, f, solution, x0);
    }

    /* Rozpoczęcie symulacji */
    public void simulate(double endSimTime) {
        mgr.setEndSimTime(endSimTime);
        mgr.startSimulation();
        showDiagrams(); /* wyświetlenie diagramów */
    }

    /* Rysuje diagramy */
    private void showDiagrams(){
        Diagram d1 = new Diagram(Diagram.DiagramType.TIME_FUNCTION,"Algorytm Eulera");
        d1.add(euler.monitoredX, java.awt.Color.BLUE); /* wartości wyznaczone za pomocą algorytmu Eulera */
        d1.add(euler.monitoredM, java.awt.Color.MAGENTA); /* wartości oczekiwane */
        d1.show();

        Diagram d2 = new Diagram(Diagram.DiagramType.TIME_FUNCTION,"Algorytm Runge-Kutty");
        d2.add(rungeKutta.monitoredX, java.awt.Color.RED); /* wartości wyznaczone za pomocą algorytmu Runge-Kutty */
        d2.add(rungeKutta.monitoredM, java.awt.Color.MAGENTA); /* wartości oczekiwane */
        d2.show();

        Diagram d3 = new Diagram(Diagram.DiagramType.TIME_FUNCTION,"Porównanie algorytmów");
        d3.add(euler.monitoredX, java.awt.Color.BLUE); /* wartości wyznaczone za pomocą algorytmu Eulera */
        d3.add(rungeKutta.monitoredX, java.awt.Color.RED); /* wartości wyznaczone za pomocą algorytmu Runge-Kutty */
        d3.add(rungeKutta.monitoredM, java.awt.Color.MAGENTA); /* wartości oczekiwane */
        d3.show();
    }
}
