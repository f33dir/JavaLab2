package org.FuturePromise;

public interface PromiseExpression<T,PromiseReject, PromiseResolve> {
    void run(org.FuturePromise.PromiseReject reject, org.FuturePromise.PromiseResolve resolve) throws InterruptedException;
}
