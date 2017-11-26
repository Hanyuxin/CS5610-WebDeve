package neu.edu.cs5010.database;

import neu.edu.cs5010.Hour;
import neu.edu.cs5010.Lift;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class HourDataBase implements SkiDataBase {
    private RandomAccessFile file = null;
    public static final int HOUR_OFFSET = 21 * Integer.BYTES;
    private int LIFT_NUMBER = 10;

    public HourDataBase(String fileName) {
        try {
            file = new RandomAccessFile(fileName,"rws");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Hour getHour(int hourID) {
        if(hourID < 1) throw new IllegalArgumentException("Error: Invalid ID");

        Hour hour = null;
        try {
            file.seek((hourID - 1) * HOUR_OFFSET);
            hour = new Hour(file.readInt());
            Map<Integer, Lift> liftMap = new HashMap<>();
            for (int i = 0; i < LIFT_NUMBER; i++) {
                Lift lift = new Lift(file.readInt());
                lift.setRidesCount(file.readInt());
                liftMap.put(lift.getLiftID(), lift);
            }
            hour.setLiftMap(liftMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hour;
    }

    /**
     * Close this file
     */
    @Override
    public void close() {
        if(file != null) {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
