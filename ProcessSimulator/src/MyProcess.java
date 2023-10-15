public class MyProcess implements Comparable<MyProcess> {
    private String name;
    private int execution;
    private int arrival;

    public MyProcess(String name,int arrival, int execution) {
        this.name = name;
        this.execution = execution;
        this.arrival = arrival;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExecution() {
        return execution;
    }

    public void setExecution(int execution) {
        this.execution = execution;
    }

    public int getArrival() {
        return arrival;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            int seconds = Integer.parseInt(args[0]);
            try {
                System.out.println("Im starting..");
                Thread.sleep(seconds * 1000);
                System.out.println("Im finishing..");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int compareTo(MyProcess o) {
        if (this.arrival == o.getArrival())
            return this.execution - o.getExecution();

        return this.getArrival() - o.getArrival();
    }
}
