package neu.edu.cs5010.database;

import neu.edu.cs5010.Skier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SkierDataBase implements SkiDataBase {

    private RandomAccessFile file = null;
    private static final int SKIER_OFFSET = 3 * Integer.BYTES;

    public SkierDataBase(String fileName) {
        try {
            file = new RandomAccessFile(fileName,"rws");
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
