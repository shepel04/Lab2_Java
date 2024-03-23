import java.util.Random;
public class Worker {
    private final int number_of_cells;
    private final int threadNum;

    public final int[] arr;


    public Worker(int number_of_cells, int threadNum) {
        this.number_of_cells = number_of_cells;
        arr = new int[number_of_cells];
        this.threadNum = threadNum;
        for(int i = 0; i < number_of_cells; i++){
            arr[i] = i;
        }
        Random random = new Random();
        arr[random.nextInt(number_of_cells)]*=-1;
    }

    public long OneThreadWorking(int startIndex, int finishIndex){
        long min =Long.MAX_VALUE;
        for(int i = startIndex; i < finishIndex; i++){
            if(min>arr[i]){
                min=arr[i];
            }
        }
        return min;
    }

    private long min = 0;

    synchronized private long getMin() {//crit w
        while (getThreadCount()<threadNum){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return min;
    }

    synchronized public void collectMin(long min){
        if(this.min>min){
            this.min = min;
        }
    }

    private int threadCount = 0;
    synchronized public void incThreadCount(){
        threadCount++;
        notify();
    }

    private int getThreadCount() {
        return threadCount;
    }

    public long threadMin(){
        ThreadMin[] threadMins = new ThreadMin[threadNum];
        int len = number_of_cells / threadNum;
        for (int i = 0; i < threadNum - 1; i++) {
            threadMins[i] = new ThreadMin(len * i, len * (i + 1), this);
            threadMins[i].start();
        }
        threadMins[threadNum-1]= new ThreadMin(len*(threadNum-1), number_of_cells, this);
        threadMins[threadNum-1].start();                                                                    
        return getMin();
    }
}
