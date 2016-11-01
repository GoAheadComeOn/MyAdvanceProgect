package com.example;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by DELL on 2016/10/11.
 */

public class RXJavaTest {
    public static void main(String[] str) {
//        query("xxx  Hello, world!")
//                .subscribe(urls -> {
//                    Observable.from(urls).subscribe(url -> System.out.printf(url));
//                });
        Subscription subscription=query("xxx  Hello, world!")
                .flatMap(list->
                Observable.from(list))
                .flatMap(url->getTitle(url))
                .take(2)
                .doOnNext(s1->saveUrl(s1))
                .subscribe(s->System.out.println(s));
        System.out.println("subscription"+subscription);

        Observable.defer(new Func0<Observable<Object>>() {
            @Override
            public Observable<Object> call() {
                return null;
            }
        });

    }

    private static void saveUrl(String s1) {
        System.out.println("保存url:"+s1);
    }

    private static Observable<String> getTitle(String url) {
        return  Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(url.substring(0,1));
            }
        });
    }

    static Observable<List<String>> query(String s) {

        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                List<String> list = new ArrayList<>();
                list.add("1111");
                list.add("2222");
                list.add("3333");
                subscriber.onNext(list);
            }
        });
    }


}
