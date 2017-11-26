package neu.edu.cs5010.database;

import neu.edu.cs5010.Lift;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LiftDataBase implements SkiDataBase{
    private RandomAccessFile file = null;
    public static final int LIFT_OFFSET = 2 * Integer.BYTES;

    public LiftDataBase(String fileName) {
        try {
            file = new RandomAccessFile(fileName,"rws");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Lift getLift(int liftID) {
        if(liftID < 1) throw new IllegalArgumentException("Error: Invalid ID");

        Lift lift = null;
        try {
            file.seek((liftID - 1) * LIFT_OFFSET);
            lift = new Lift(file.readInt());
            lift.setRidesCount(file.readInt());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lift;
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
