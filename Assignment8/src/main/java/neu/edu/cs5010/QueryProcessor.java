package neu.edu.cs5010;

import neu.edu.cs5010.IO.Reader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class QueryProcessor {
    private String inputFileName;
    private List<String> input;
    private Map<Integer, List<Integer>> QueryInput;
    private int NumberOfQueries;
    private int QueriesPerThread;

    private Thread[] threads;
    public QueryProcessor(){
         inputFileName="";
         input= new LinkedList<>();
         QueryInput=new HashMap<>();
         threads = new Thread[20];
    }

    public void checkArgument(String[] args) {
        if (args.length != 2)
            throw new IllegalArgumentException("Please check your argument");
        if (!args[0].contains(".csv"))
            throw new IllegalArgumentException("The input test data file should be a csv file");
        if(Integer.valueOf(args[1])%20>0)
            throw new IllegalArgumentException("Invalid Number of queries!");
        inputFileName = args[0];
        NumberOfQueries = Integer.valueOf(args[1]);
        QueriesPerThread = NumberOfQueries/20;
    }

    public void generateThread(){
        for(int i=0; i<20; i++){
            threads[i]= new Thread(i,getQueryPerThread(i));
        }

    }



    public Map<Integer, List<Integer>> getQueryPerThread(int index){
        Reader reader = new Reader();
        input = reader.read(inputFileName);

      int currIndex = index;
       for(int i=currIndex*QueriesPerThread; i<(currIndex+1)*QueriesPerThread; i++){
             String data = input.get(i);
               String[] line = data.split(",");
               int key = Integer.valueOf(line[0]);
               if(!QueryInput.containsKey(key)){
                   QueryInput.put(key, new LinkedList<>());
               }
               QueryInput.get(key).add(Integer.valueOf(line[1]));

       }
       return QueryInput;
    }

    public void startThreads(){
        generateThread();

    }


}
