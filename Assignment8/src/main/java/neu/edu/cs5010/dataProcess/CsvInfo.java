package neu.edu.cs5010.dataProcess;

public class CsvInfo {

    private static int skierPos;
    private static int liftPos;
    private static int hourPos;

    public CsvInfo(String header) {
        initialIndexes(header);
    }

    /**
     *  first find the different columns index in the giving String
     * @param input String
     */
    private void initialIndexes(String input) {
        String[] strs = input.split(",");
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].contains("skier")) skierPos = i;
            else if (strs[i].contains("lift")) liftPos = i;
            else if (strs[i].contains("time")) hourPos = i;
        }
    }

    /**
     * get the column index of lift in the giving file
     * @return int liftPos
     */
    public static int getLiftPos() {
        return liftPos;
    }

    /**
     * get the column index of hour in the giving file
     * @return int hourPos
     */
    public static int getHourPos() {
        return hourPos;
    }

    /**
     * get the column index of skier in the giving file
     * @return int skierPos
     */
    public static int getSkierPos() {
        return skierPos;
    }
}
