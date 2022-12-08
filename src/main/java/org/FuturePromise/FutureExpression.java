package org.FuturePromise;

public interface FutureExpression<T> {
    T run() throws InterruptedException;
}
