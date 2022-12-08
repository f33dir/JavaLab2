package org.FuturePromise;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class Promise  implements Runnable{
    private AtomicInteger status = new AtomicInteger(0);
    private PromiseReject reject = ()->{
        synchronized (this.lock){
            status.set(-1);
            this.lock.notifyAll();
        }
    };
    private PromiseResolve resolve = ()->{
        synchronized (this.lock){
            status.set(1);
            this.lock.notifyAll();
        }
    };
    private PromiseExpression expression;

    public Promise(PromiseExpression promiseExpression){
        expression = promiseExpression;
    }

    private Object lock =new Object();
    @Override
    public void run() {
        Runnable runnable = () ->{
            try {
                expression.run(reject,resolve);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch(status.get()){
                case 0:
                    try {
                        throw(new Exception("Unresolved Promise"));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                case -1:
                    try {
                        throw(new Exception("Promise Rejected"));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                case 1:
                    break;
            }
        };
        var thread = new Thread(runnable) ;
        thread.run();
    }


    public  void await()  {
        synchronized (lock){
        if(status.get() == 0){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        }
    }
}
