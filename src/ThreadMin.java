public class ThreadMin extends Thread {
    private final int startIndex;
    private final int finishIndex;
    private final Worker arrClass;

    public ThreadMin(int startIndex, int finishIndex, Worker arrClass) {
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;
        this.arrClass = arrClass;
    }

    @Override
    public void run() {
        long min = arrClass.OneThreadWorking(startIndex, finishIndex);//min el start i .. finish
        arrClass.collectMin(min);
        arrClass.incThreadCount();//mark finish
    }
}
