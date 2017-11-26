package neu.edu.cs5010;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Thread implements Runnable {
    private int index;
    private QueryProcessor queryProcessor;
    private Map<Integer, List<Integer>> QueryInput;
    private String fileName;

    public Thread(int index, Map<Integer, List<Integer>> QueryInput){
        this.index=index;
        this.QueryInput = QueryInput;
        this.fileName = "Thread"+this.index;
        queryProcessor = new QueryProcessor();

    }

    @Override
    public void run(){

    }



    /**
     * Return the index of current threads;
     * @return THe index of current threads;
     */
    public int getIndex(){
        return this.index;
    }
}
