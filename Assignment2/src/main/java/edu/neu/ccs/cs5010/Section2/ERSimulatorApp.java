package edu.neu.ccs.cs5010.Section2;


public class ERSimulatorApp
{
    public static void main( String[] args )
    {
        ERSimulator erSimulator = new ERSimulator(480,0,50);
        erSimulator.runSimulation();
        erSimulator.printResult();
    }
}
