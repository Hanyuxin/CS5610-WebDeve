package neu.edu.cs5010.database;



import neu.edu.cs5010.dataProcess.Lift;
import neu.edu.cs5010.dataProcess.Skier;
import neu.edu.cs5010.dataProcess.SkierLiftMap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class SkierDataBase implements SkiDataBase {

    private RandomAccessFile file = null;
    private static final int SKIER_OFFSET = 4 * Integer.BYTES +
            SkierLiftMap.SIGNLE_LENGTH * SkierLiftMap.NUMBER *Character.BYTES;

    public SkierDataBase(String fileName) {
        try {
            file = new RandomAccessFile(fileName,"rw");
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
            skier.setNumberOfViews(file.readInt());

            SkierLiftMap liftMap = getLiftMap();
            skier.setLiftMap(liftMap);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return skier;
    }

    public void updateSkierNumOfView(Skier skier) {
        if (skier.getID() < 1) throw new IllegalArgumentException("Error: Invalid ID");
        try {
            file.seek((skier.getID()  - 1) * SKIER_OFFSET + 3 * Integer.BYTES);
            file.writeInt(skier.getNumberOfViews() + 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SkierLiftMap getLiftMap() {
        Map<Integer, Lift> map = new HashMap<>();
        for(int j = 0; j < SkierLiftMap.NUMBER; j++) {
            char[] s = new char[SkierLiftMap.SIGNLE_LENGTH];
            for (int i = 0; i < s.length; i++) {
                try {
                    s[i] = file.readChar();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String temp = new String(s);
            if (temp.contains("\0")) continue;
            int time = Integer.parseInt(String.valueOf(s,0,3));
            int liftID = Integer.parseInt(String.valueOf(s,3,2));
            map.put(time,new Lift(liftID));
        }
        return new SkierLiftMap(map);
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
