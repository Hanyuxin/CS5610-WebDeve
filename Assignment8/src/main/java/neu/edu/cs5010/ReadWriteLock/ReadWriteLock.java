package neu.edu.cs5010.ReadWriteLock;


import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    private Map<Thread, Integer> readingThreads;
    private int writeAccesses;
    private int writeRequests;
    private Thread writingThread;
    private Thread readingThread;

    private ReentrantReadWriteLock readWriteLock;
    private Lock read;
    private Lock write;

    public ReadWriteLock(){
        readWriteLock = new ReentrantReadWriteLock();
        read = readWriteLock.readLock();
        write = readWriteLock.writeLock();

    }

    /**
     * get the readLock
     * @return The readLock;
     */
    public Lock getReadLock(){
          return this.read;
    }

    /**
     * Get the writeLock
     * @return The writeLock;
     */
    public Lock getWriteLock(){
        return this.write;
    }


}
