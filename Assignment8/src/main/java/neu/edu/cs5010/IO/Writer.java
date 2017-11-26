package neu.edu.cs5010.IO;

import java.io.*;

public class Writer {

    /**
     * Use BufferedWriter to write the string to a file that named fileName, if already existing,
     * file will be overridden
     * @param fileName the file that to write
     * @param str       the content that write to file
     */
    public static void OverriddenWrite(String fileName, String str) {
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

    /**
     * Use BufferedWriter to write the string to a file that named fileName, if already existing,
     * file will keep the existing content and append the new content to the end of a file
     * @param fileName the file that to write
     * @param str       the content that write to file
     */
    public static void AppendWrite(String fileName, String str) {
        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;
        try {

            writer = new FileWriter(fileName,true);
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

    /**
     * Use FileOutputStream to write byte array to the giving file
     * @param fileName the file that to write
     * @param content  the byte that to write
     */
    public static void OverriddenWrite(String fileName, byte[] content) {

        FileOutputStream fop = null;
        File file;
        try {

            file = new File(fileName);
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            fop.write(content);
            fop.flush();
            fop.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
