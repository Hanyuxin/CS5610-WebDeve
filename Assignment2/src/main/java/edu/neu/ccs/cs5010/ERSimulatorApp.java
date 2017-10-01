package edu.neu.ccs.cs5010;


public class ERSimulatorApp
{
    public static void main( String[] args )
    {
        ERSimulator erSimulator = new ERSimulator(180,4,50);
        erSimulator.runArriveSimulation();
        erSimulator.printResult();

    }
}
