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
    public Lock getReadLock(){
          return this.read;
    }

    public Lock getWriteLock(){
        return this.write;
    }

//    public  synchronized void lockRead() throws InterruptedException{
//       Thread callingThread= Thread.currentThread();
//
//        while(! canGrantReadAccess(callingThread)){
//            wait();
//        }
//        readingThreads.put(callingThread,
//                (getReadAccessCount(callingThread) + 1));
//        readingThread = callingThread;
//
//    }
//    public synchronized void unlockRead(){
//        Thread callingThread = Thread.currentThread();
//        int accessCount = getReadAccessCount(callingThread);
//
//        if(accessCount == 1){ readingThreads.remove(callingThread); }
//        else { readingThreads.put(callingThread, (accessCount -1)); }
//        notifyAll();
//    }
//
//
//    private boolean canGrantReadAccess(Thread callingThread){
//        if(writeAccesses > 0)            return false;
//        if(isReader(callingThread)) return true;
//        if(writeRequests > 0)      return false;
//        return true;
//    }
//
//    private int getReadAccessCount(Thread callingThread){
//        Integer accessCount = readingThreads.get(callingThread);
//        if(accessCount == null) return 0;
//        return accessCount.intValue();
//    }
//
//    private boolean isReader(Thread callingThread){
//        return readingThreads.get(callingThread) != null;
//    }
//
//
//
//    public synchronized void lockWrite() throws InterruptedException{
//        writeRequests++;
//        Thread callingThread = Thread.currentThread();
//        while(! canGrantWriteAccess(callingThread)){
//            wait();
//        }
//        writeRequests--;
//        writeAccesses++;
//        writingThread = callingThread;
//    }
//
//    public synchronized void unlockWrite() throws InterruptedException{
//        writeAccesses--;
//        if(writeAccesses == 0){
//            writingThread = null;
//        }
//        notifyAll();
//    }
//
//    private boolean canGrantWriteAccess(Thread callingThread){
//        if(isOnlyReader(callingThread))    return true;
//        if(hasReaders())                   return false;
//        if(writingThread == null)          return true;
//        if(!isWriter(callingThread))       return false;
//        return true;
//    }
//
//    private boolean hasReaders(){
//        return readingThreads.size() > 0;
//    }
//
//    private boolean isWriter(Thread callingThread){
//        return writingThread == callingThread;
//    }
//
//    private boolean isOnlyReader(Thread callingThread){
//        return readingThread==callingThread && readingThreads.get(callingThread) == 1;
//    }
}
