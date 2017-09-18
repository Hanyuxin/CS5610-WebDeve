package Assignment1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ScenicRoad {
    private Highway highway;
    private int bandwidth;
    private Map<Integer,Double> map;

    public ScenicRoad(int bandwidth){
        if(bandwidth<=0)
            throw new IllegalArgumentException();
        this.bandwidth = bandwidth;
        highway = new Highway();
        map = new HashMap<Integer, Double>();
    }

    public boolean add(Vehicle v){
        if(v == null) throw  new IllegalArgumentException();
        boolean res = highway.add(v);
        if(res == true) {
            map.put(v.hashCode(),v.getVelocity());
            if (numberVehiclesEastbound() >= bandwidth || numberVehiclesWestbound() >= bandwidth) {
                Iterator iterator = iterator();
                while(iterator.hasNext()){
                    Vehicle vehicle = (Vehicle) iterator.next();
                    vehicle.setVelocity(4.0);
                }
            }
        }
        return res;
    }

    public Iterator<Vehicle> iterator(){
        return highway.iterator();
    }

    public double getVelocityEastbound(){
        return highway.getVelocityEastbound();
    }

    public double getVelocityWestbound(){
        return highway.getVelocityWestbound();
    }

    public int numberVehiclesEastbound(){
        return highway.numberVehiclesEastbound();
    }

    public int numberVehiclesWestbound(){
        return highway.numberVehiclesWestbound();
    }

    public boolean contains(Vehicle v){
        return highway.contains(v);
    }

    public boolean remove(Vehicle v) {
        boolean res = highway.remove(v);
        if (res == true) {
            if (numberVehiclesEastbound() < bandwidth && numberVehiclesWestbound() < bandwidth) {
                Iterator iterator = iterator();
                while (iterator.hasNext()) {
                    Vehicle vehicle = (Vehicle) iterator.next();
                    vehicle.setVelocity(map.get(vehicle.hashCode()));
                }
            }
        }
        return res;
    }

}
