package com.sltpaya.rxtest;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test2() {
        Observable.fromArray(1, 2, 3, 4, 5).flatMap((Function<Integer, ObservableSource<String>>) integer -> Observable.create(emitter -> emitter.onNext("我喜欢你" + integer))).subscribe(System.out::println);
    }


    @Test
    public void test() {
        Observable
                .create((ObservableOnSubscribe<String>) emitter -> {
                    emitter.onNext("哈哈");
                })
                .map(s -> s.charAt(0))
                .subscribe(character -> {

                });


        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("哈哈哈哈哈");
            emitter.onNext("好吧");
            emitter.onNext("不开心");
            emitter.onComplete();
        }).map(String::length)
                  .map(integer -> Character.toString((char) (integer + 55)))
                  .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("订阅了");
            }

            @Override
            public void onNext(String integer) {
                System.out.println("长度为：" + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("发生错误了！" + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("结束了");
            }
        });
    }

}


