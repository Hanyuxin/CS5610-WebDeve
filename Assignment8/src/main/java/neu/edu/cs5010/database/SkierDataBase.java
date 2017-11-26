package neu.edu.cs5010.database;

import neu.edu.cs5010.Skier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class SkierDataBase implements SkiDataBase {

    private RandomAccessFile file = null;
    private static final int SKIER_OFFSET = 3 * Integer.BYTES;
    private RandomAccessFile fileLift = null;

    public SkierDataBase(String fileName) {
        try {
            file = new RandomAccessFile(fileName,"rws");
            fileLift = new RandomAccessFile("lift.dat","rws");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Skier getSkier(int skierID) {
        if (skierID < 1) throw new IllegalArgumentException("Error: Invalid ID");

        Skier skier = null;
        try {
            file.seek((skierID - 1) * SKIER_OFFSET);
            skier = new Skier(file.readInt());
            skier.setVerticalMetres(file.readInt());
            skier.setLiftRidesCount(file.readInt());
            Map<Integer, Integer> countToLiftID = new HashMap<>();
            int LiftID = fileLift.readInt();
            int count = fileLift.readInt();
            countToLiftID.put(count,LiftID);
            skier.setCountToLiftID(countToLiftID);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return skier;
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
