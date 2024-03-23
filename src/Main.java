public class Main {
    public static void main(String[] args) {
        int number_of_cells = 100000000;
        int threadNum = 4;
        long time = System.nanoTime();
        Worker arrClass = new Worker(number_of_cells, threadNum);
        long minIndex = arrClass.OneThreadWorking(0, number_of_cells);     //single threading
        time = System.nanoTime() - time;
        System.out.println(minIndex + " time:" + time);
        time = System.nanoTime();
        minIndex = arrClass.threadMin();                                        //multithreading
        time = System.nanoTime() - time;
        System.out.println(minIndex + " time:" + time);
    }
}