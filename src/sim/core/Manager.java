package sim.core;
import java.util.LinkedList;

public class Manager {	
	private double startSimTime = 0.0;
	private double stopSimTime = Double.MAX_VALUE;
	private double currentSimTime = startSimTime;
	private final double timeStep;
	private static Manager simMgr; // Singleton
	private boolean simulationStarted = false;
	// Lista workerów, którzy są skłądowymi kroku symulacji 
	private final LinkedList<SimStep> simStepWorkers = new LinkedList<>();
	
	public static Manager getInstance(double startSimTime, double timeStep) {
		if (simMgr == null) {
			simMgr = new Manager(startSimTime, timeStep);
		}
		return simMgr;
	}
	
	private Manager(double startSimTime, double timeStep) {
		if (startSimTime>0.0) 
			this.startSimTime = startSimTime;		
			this.timeStep = timeStep;
	}

	public void registerSimStep(SimStep step) {
		if (step!=null)
			simStepWorkers.add(step);
	}
	
	public final double simTime() {
		return currentSimTime;
	}

	public final double getTimeStep() {
		return timeStep;
	}
	
	public final void stopSimulation() {
		simulationStarted = false;
	}

	public final void startSimulation() {
		simulationStarted = true;
		// DO WYKONANIA NA LABORATORIUM
		System.out.printf("%.2f\t Symulacja rozpoczęta.\n", simTime());
		System.out.println("-------------------------------------------------------------");
		while(this.simulationStarted && this.currentSimTime < this.stopSimTime){
			runStebByStep();
			this.currentSimTime += this.timeStep;
			System.out.println("-------------------------------------------------------------");
		}
		stopSimulation();
		System.out.printf("%.2f\t Symulacja zakończona.\n", simTime());
	}
	
	public void setEndSimTime(double endSimTime) {
		this.stopSimTime = endSimTime;
	}
	
	private void runStebByStep() {
		// DO WYKONANIA NA LABORATORIUM
		for(SimStep simStepWorker : this.simStepWorkers){
			simStepWorker.stateChange();
		}
	}
}
