package neu.edu.cs5010;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class IOLibrary {

    /**
     * Use BufferedWriter to write the str to a file that named fileName
     * @param fileName the file that to write
     * @param str the content that write to file
     */
    public static void write(String fileName, String str ) {
        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            writer = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(str);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
