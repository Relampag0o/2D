package org.example;

public class MyProcess {

    private String name;
    private int arrival;
    private int exetime;

    public MyProcess(String name, int arrival, int exetime) {
        this.name = name;
        this.arrival = arrival;
        this.exetime = exetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArrival() {
        return arrival;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public int getExetime() {
        return exetime;
    }

    public void setExetime(int exetime) {
        this.exetime = exetime;
    }

    public static void main(String[] args) {

        if (args.length == 1) {
            int segundos = Integer.parseInt(args[0]);
            try {
                System.out.println("Im starting..");
                Thread.sleep(segundos * 1000); // Pausa de 2 segundos
                System.out.println("Im finishing..");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

