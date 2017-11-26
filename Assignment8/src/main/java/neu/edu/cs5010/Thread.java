package neu.edu.cs5010;

import neu.edu.cs5010.IO.Reader;
import neu.edu.cs5010.IO.Writer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Thread extends java.lang.Thread {
    private int index;
    private QueryProcessor queryProcessor;
    private Map<Integer, List<Integer>> QueryInput;
    private Map<Integer, String> fileNameParser;
    private String fileName;

    public Thread(int index, Map<Integer, List<Integer>> QueryInput){
        this.index=index;
        this.QueryInput = QueryInput;
        this.fileName = "Thread"+this.index;
        queryProcessor = new QueryProcessor();
        fileNameParser = new HashMap<>();
        fileNameParser.put(1,"skier.dat");
        fileNameParser.put(2,"skier.dat");
        fileNameParser.put(3,"hour.dat");
        fileNameParser.put(4,"lift.dat");

    }

    @Override
    public void run(){
        for(int key : QueryInput.keySet()){
            for(int parameterID: QueryInput.get(key)){
                String data = Reader.readData(key, parameterID,fileNameParser.get(key));
                write(data);
            }
        }

    }

   public void write(String data){
       Writer.AppendWrite(fileName, data);
   }

    /**
     * Return the index of current threads;
     * @return THe index of current threads;
     */
    public int getIndex(){
        return this.index;
    }
}
