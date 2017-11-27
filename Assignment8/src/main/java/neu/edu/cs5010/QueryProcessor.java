package neu.edu.cs5010;

import neu.edu.cs5010.IO.Reader;
import neu.edu.cs5010.ReadWriteLock.ReadWriteLock;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QueryProcessor {
    private  String inputFileName;
    private  List<String> input;

    private  int NumberOfQueries;
    private  int QueriesPerThread;
    private  CyclicBarrier cyclicBarrier;

    private  List<WorkingThread> threads;
    public QueryProcessor(){
         inputFileName="";
         input= new LinkedList<>();

         threads = new LinkedList<>();
         this.cyclicBarrier = new CyclicBarrier(10*2+1);
    }

    public  void checkArgument(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Please check your argument");
        }
        if (!args[0].contains(".csv")) {
            throw new IllegalArgumentException("The input test data file should be a csv file");
        }
        if(Integer.valueOf(Integer.valueOf(args[1]))%20>0) {
            throw new IllegalArgumentException("Invalid Number of queries!");
        }
        inputFileName = args[0];
        NumberOfQueries = Integer.valueOf(args[1]);
        QueriesPerThread = NumberOfQueries/20;
    }

    public  List<WorkingThread> generateThread(){
        List<WorkingThread> threads = new LinkedList<>();
        for(int i=0; i<20; i++){
            threads.add(new WorkingThread(i,getQueryPerThread(i)));
        }
        return threads;

    }

    public List<String> test(){
        Reader reader = new Reader();
        input = reader.read(inputFileName);
        return input;
    }


    public  Map<Integer, List<Integer>> getQueryPerThread(int index){
        Reader reader = new Reader();
        input = reader.read(inputFileName);

      int currIndex = index;
      Map<Integer,List<Integer>> QueryInput = new HashMap<>();
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

    public  void startThreads() throws InterruptedException{
        threads= generateThread();
        ExecutorService executor = Executors.newCachedThreadPool();
        try{
            for(int i=0; i<threads.size(); i++){
                executor.execute(threads.get(i));
            }
            cyclicBarrier.await();
            cyclicBarrier.await();
        } catch(Exception e){
            e.printStackTrace();
        }
        executor.shutdown();

    }

    public static void main(String[] args) throws InterruptedException{
        QueryProcessor queryProcessor = new QueryProcessor();
        queryProcessor.checkArgument(args);
        System.out.println(queryProcessor.test().size());
        queryProcessor.generateThread();
        queryProcessor.startThreads();
    }


}
