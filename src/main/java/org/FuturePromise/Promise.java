package org.FuturePromise;

import java.util.concurrent.Callable;

public interface PromiseExpression{
    public void func(Callable<Boolean> ,Callable);
}
public class Promise<T> {
    public void resolve(){

    }
    public void reject(){

    }
    public T subscribe() {
        return null;
    }
    public Promise<T>()
}
