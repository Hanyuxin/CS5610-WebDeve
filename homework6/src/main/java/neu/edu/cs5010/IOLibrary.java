package neu.edu.cs5010;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOLibrary {

    /**
     * use bufferedReader to read from the file that named fileName
     * @param fileName the fileName to read
     * @return the content in this fileName
     */
    public static List<String> read(String fileName) {
        File file = new File(fileName);
        BufferedReader bufferedReader = null;
        List<String> list = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                list.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

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
