package org.JavaLab2;

import org.FuturePromise.Future;
import org.FuturePromise.FutureExpression;
import org.FuturePromise.PromiseExpression;
import org.VersionedList.VersionedList;
import org.FuturePromise.Promise;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        var arr = new VersionedList<Integer>();
        arr.add(5);
        arr.add(6);
        var iter1 = arr.iterator();
        arr.add(3);
        arr.add(7);
        while (iter1.hasNext()){
            System.out.println(iter1.next());
        }
//        PromiseExpression expression = (reject,resolve) ->{
//            System.out.println(1);
//            Thread.sleep(4000);
//            resolve.apply();
//        };
//        var promise =  new Promise(expression);
//        promise.run();
//        promise.await();
//        System.out.println(2);

        FutureExpression<String> expression1 = () -> {
            Thread.sleep(4000);
            return "Done";
        };

        var future = new Future<String>(expression1);
        System.out.println(future.await());
    }
}