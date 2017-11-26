package neu.edu.cs5010.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class RawDataBase implements SkiDataBase {
    private RandomAccessFile file;
    public static final int RAW_OFFSET = 3 * Integer.BYTES;

   public RawDataBase(String fileName){
       try {
           file = new RandomAccessFile(fileName,"rw");
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
   }

//   public List<int[]> getLiftTimeList(int skierID, int liftID) {
//       if( skierID < 1 || liftID < 1) throw new IllegalArgumentException("Error: Invalid ID");
//
//       List<int[]> list = new ArrayList<>();
//       try {
//           file.seek((skierID - 1) * RAW_OFFSET);
//           for (int i = 0; i < RAW_OFFSET; i += 3 * Integer.BYTES) {
//
//           }
//       } catch (IOException e) {
//           e.printStackTrace();
//       }
//   }
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
