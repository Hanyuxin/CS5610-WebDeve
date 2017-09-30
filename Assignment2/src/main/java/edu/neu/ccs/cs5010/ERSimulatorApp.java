package edu.neu.ccs.cs5010;


public class ERSimulatorApp
{
    public static void main( String[] args )
    {
        ERSimulator erSimulator = new ERSimulator(480,0,700);
        erSimulator.runArriveSimulation();
        erSimulator.runDepartSimulation();
        erSimulator.printResult();

    }
}
