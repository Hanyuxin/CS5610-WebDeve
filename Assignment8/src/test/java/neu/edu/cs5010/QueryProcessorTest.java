package neu.edu.cs5010;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.util.List;


public class QueryProcessorTest {
    private QueryProcessor queryProcessor;
    private List<WorkingThread> threads;
    private String[] args1;

    @Before
    public void setUp(){
        queryProcessor = new QueryProcessor();
        args1 = new String[]{"PDPAssignment8.csv","4000"};

    }

    /**
     * Test the checkArgument method in QueryProcessor class.
     */
    @Test
    public void checkArgumentTest(){
        queryProcessor.checkArgument(args1);
    }

    /**
     * Test the generateThread method in QueryProcessor class.
     * See if the data were distributed evenly to each of WorkingThread
     * object.
     */
    @Test
    public void generateThreadTest(){
        queryProcessor.checkArgument(args1);
        threads= queryProcessor.generateThread();
        Assert.assertEquals(20, threads.size());
        for(int i=0; i<19; i++){
            Assert.assertEquals(threads.get(i).getQueryInput().size(), threads.get(i+1).getQueryInput().size());
        }
    }

    /**
     * Test the startThread method in QueryProcessor class.
     */
    @Test
    public void startThreadsTest(){
        queryProcessor.checkArgument(args1);
        threads= queryProcessor.generateThread();
        try{
            queryProcessor.startThreads();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }


}
