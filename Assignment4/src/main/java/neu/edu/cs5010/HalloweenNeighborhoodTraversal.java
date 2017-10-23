package neu.edu.cs5010;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * main class
 *
 */
public class HalloweenNeighborhoodTraversal {


    public static void main( String[] args ) {
        HalloweenNeighborhoodTraversal traversal = new HalloweenNeighborhoodTraversal();

        traversal.checkArgument(args);

        for (int i = 1; i < args.length; i++) {

            String fileContent = IOLibrary.read(args[i]);
            List<Candy> candyList = traversal.getCandyList(fileContent);
            StringBuilder output = new StringBuilder();
            boolean valid = true;
            output.append("Candy type, Candy size, Household type").append(System.lineSeparator());

            for ( Candy candy : candyList) {

                //In order to continue next CSV file
                if (candy == null) {
                    valid = false;
                    break;
                }

                MansionVistor mansionVistor = new MansionVistor();
                candy.accept(mansionVistor);
                if (mansionVistor.getMansion() != null) {
                    output.append(mansionVistor.getMansion()).append(System.lineSeparator());
                    continue;
                }

                TownhomeVisitor townhomeVisitor = new TownhomeVisitor();
                candy.accept(townhomeVisitor);
                if (townhomeVisitor.getTownhome() != null){
                    output.append(townhomeVisitor.getTownhome()).append(System.lineSeparator());
                    continue;
                }

                DetachedHouseVistor detachedHouseVistor = new DetachedHouseVistor();
                candy.accept(detachedHouseVistor);
                if (detachedHouseVistor.getDetachedHouse() != null) {
                    output.append(detachedHouseVistor.getDetachedHouse()).append(System.lineSeparator());
                    continue;
                }

                DuplexVisitor duplexVisitor = new DuplexVisitor();
                candy.accept(duplexVisitor);
                if (duplexVisitor.getDuplex() != null) {
                    output.append(duplexVisitor.getDuplex()).append(System.lineSeparator());
                    continue;
                }

                System.out.println("Error: No traversal because the list contains an unsupported " +
                        "combination of " + candy.getSize() + " size and type of " +
                        candy.getClass().getSimpleName());
                valid = false;
                break;
            }

            if (valid)
                IOLibrary.write(traversal.getOutPutFileName(args[i]),output.toString());
        }
    }

    /**
     *  Check argument, if nothing input, throw  IllegalArgumentException
     * @param args command input argument
     */
    private void checkArgument(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Please input correct argument");
        } else {
            checkCVSArgument(args);
        }
    }


    /**
     * Check argument, if nothing input, throw  IllegalArgumentException
     * @param args command input argument
     */
    private void checkCVSArgument(String[] args) {

        int n = Integer.parseInt(args[0]);
        if (args.length != n + 1) {
            throw new IllegalArgumentException("Not enough CSV file");
        }

    }

    /**
     * use the input file Name to get the output file name
     * @param input Input file Name
     * @return output file name
     */
    private String getOutPutFileName(String input) {

        String index = input.substring("DreamCandy".length(),input.length() - ".csv".length());
        return "DreamTravesal "+ index + ".txt";

    }


    /** use "," to split the input file content to get individual candy. And for each candy, use "size" to
     * split to get the candy size and type. and then pass the type and size to getCandy method
     * @param fileContent the input file content
     * @return a  candy list that this child want
     */
    private List<Candy> getCandyList(String fileContent) {

        String[] candies = fileContent.split(",");

        List<Candy> list = new ArrayList<>();

        for(String candy: candies) {
            candy = candy.toLowerCase();
            String[] str = candy.split(" size ");
            if (str.length == 1) {
                list.add(getCandy(str[0].trim(), "regular"));
            } else list.add(getCandy(str[1].trim(),str[0].trim()));
        }

        return list;
    }

    /**
     * In order to avoid large switch-case block, use reflex to get Candy
     * @param candyName this candy type
     * @param candySize this candy size
     * @return Candy
     */
    private Candy getCandy(String candyName, String candySize) {

        int index = candyName.lastIndexOf(" ");
        if (index == -1) {candyName = candyName.substring(0,1).toUpperCase() + candyName.substring(1);}
        else candyName = candyName.substring(0,1).toUpperCase() + candyName.substring(1,index) +
                candyName.substring(index + 1,index + 2).toUpperCase() + candyName.substring(index + 2);

        String candyClassName = "neu.edu.cs5010." + candyName + "Candy";

        Candy candy = null;
        try {
            Class newCandy = Class.forName(candyClassName);
            Constructor constructor = newCandy.getConstructor(String.class);
            candy = (Candy)constructor.newInstance(candySize);
        } catch (ClassNotFoundException e) {
            System.out.println("Error : No traversal because the list contains an unsupported candy " + candyName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return candy;
    }
}
