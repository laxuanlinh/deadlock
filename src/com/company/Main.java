package com.company;

public class Main {

    static StringBuilder resource1 = new StringBuilder("resource1");
    static StringBuilder resource2 = new StringBuilder("resource2");

    public static void main(String args[]) {
        ThreadDemo1 T1 = new ThreadDemo1();
        ThreadDemo2 T2 = new ThreadDemo2();
        T1.start();
        T2.start();
    }

    private static class ThreadDemo1 extends Thread {
        public void run() {
            synchronized (resource1) {

                resource1 = resource1.append(resource2.toString());
                System.out.println("Thread 1: locked resource 1 "+resource1.toString());
                try { Thread.sleep(100);} catch (Exception e) {}

                synchronized (resource2) {
                    resource2 = resource2.append(resource1.toString());
                    System.out.println("Thread 1: locked resource 2 "+resource2);
                }
            }
        }
    }
    private static class ThreadDemo2 extends Thread {
        public void run() {
            synchronized (resource1) {

                resource1 = resource1.append(resource2.toString());
                System.out.println("Thread 2: locked resource 1 "+resource1);

                try { Thread.sleep(100);} catch (Exception e) {}

                synchronized (resource2) {
                    resource2 = resource2.append(resource1.toString());
                    System.out.println("Thread 2: locked resource 2 "+resource2);
                }
            }
        }
    }
}
