package neu.edu.cs5010;

import neu.edu.cs5010.IO.Reader;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class QueryProcessor  {
    private  String inputFileName;
    private  List<String> input;

    private  int NumberOfQueries;
    private  int QueriesPerThread;
    public  CyclicBarrier cyclicBarrier;
    private  long time;

    private  List<WorkingThread> threads;
    private ExecutorService executor;
    public QueryProcessor(){
         inputFileName="";
         input= new LinkedList<>();
         time = System.currentTimeMillis();
         threads = new LinkedList<>();
        executor = Executors.newCachedThreadPool();

         this.cyclicBarrier = new CyclicBarrier(10*2+1);
    }

    /**
     * check whether input arguments are valid, throw corresponding IllegalArgumentException
     * whenn the arguments are illegal.
     * and not a csv file
     * @param args input arguments
     */
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

    /**
     * Generate 20 WorkingThread objects that are able to process the query input data
     * and put the WorkingThread objects into List;
     * @return List of WorkingThread objects.
     */
    public  List<WorkingThread> generateThread(){
        List<WorkingThread> threads = new LinkedList<>();
        for(int i=0; i<20; i++){
            threads.add(new WorkingThread(i,getQueryPerThread(i)));
        }
        return threads;

    }

    /**
     * Get QueryInputs that each WorkingThread is responsible for to process the
     * Query input data.
     * @param index Index of WorkingThread object
     * @return The Map of QueryInput(QueryType, ParameterID) of each WorkingThread object.
     */
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

    /**
     * Start 20 WorkingThreads at the same time.
     * @throws InterruptedException
     */
    public  void startThreads() throws InterruptedException{
        threads= generateThread();


            for(int i=0; i<threads.size(); i++) {
                executor.execute(threads.get(i));
            }
           executor.shutdown();

//        try{
//            for(int i=0; i<threads.size(); i++){
//                executor.execute(threads.get(i));
//            }
//            cyclicBarrier.await();
//            cyclicBarrier.await();
//             executor.shutdown();
//        } catch(InterruptedException  | BrokenBarrierException e){
//            e.printStackTrace();
//        }
        System.out.println("Elapsed Time:"+String.valueOf(System.currentTimeMillis()-time)+"ms");

    }

    /**
     * start the entire QueryProcessor program.
     * @param args Command line arguments(String array).
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        QueryProcessor queryProcessor = new QueryProcessor();
        queryProcessor.checkArgument(args);
        queryProcessor.startThreads();


    }


}
