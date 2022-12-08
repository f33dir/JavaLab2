package org.FuturePromise;

import java.util.concurrent.atomic.AtomicInteger;

public class Future<T> implements Runnable{
    private Object lock =new Object();
    private FutureExpression expression;
    private AtomicInteger status = new AtomicInteger(0);
    private T result;

    private void  finish(){
        synchronized (lock){
            lock.notifyAll();
            status.set(1);
        }
    }
    public Future(FutureExpression futureExpression){
        expression = futureExpression;
        run();
    }
    @Override
    public void run() {
        Runnable runnable = () ->{
            try {
                result = (T)expression.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finish();
        };
        var thread = new Thread(runnable);
        thread.run();
    }

    public  T await() {
        synchronized (lock){
        if(status.get() == 0) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
        }
    }
}
