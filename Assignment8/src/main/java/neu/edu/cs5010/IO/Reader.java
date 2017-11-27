package neu.edu.cs5010.IO;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reader {

    public Reader(){


    }
    /**
     * use bufferedReader to read from the file that named fileName
     * @param fileName the fileName to read
     * @return the content in this fileName
     */
    public  List<String> read(String fileName) {
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


}
