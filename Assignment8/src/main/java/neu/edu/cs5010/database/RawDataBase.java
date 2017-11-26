package neu.edu.cs5010.database;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RawDataBase implements SkiDataBase {
private RandomAccessFile file;
   public RawDataBase(String fileName){
       file = null;

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
