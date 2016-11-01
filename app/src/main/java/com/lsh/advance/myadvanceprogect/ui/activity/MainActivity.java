package com.lsh.advance.myadvanceprogect.ui.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.jakewharton.rxbinding.view.RxView;
import com.lsh.advance.myadvanceprogect.R;
import com.lsh.advance.myadvanceprogect.push.Utils;
import com.lsh.advance.myadvanceprogect.utils.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.btn_click)
    Button btnClick;
    private String TAG;
    Observable<String> myObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> sub) {
                    sub.onNext("Hello, world!");
                    sub.onCompleted();
                }
            }
    );

    Observable<String> myObservableJust = Observable.just("Just Hello, world!");
    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            LogUtil.i("onCompleted()");
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.i("onError()");
        }

        @Override
        public void onNext(String s) {
            LogUtil.i(s);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TAG=this.getLocalClassName();
        Utils.logStringCache = Utils.getLogText(getApplicationContext());
        // Push: 以apikey的方式登录，一般放在主Activity的onCreate中。
        // 这里把apikey存放于manifest文件中，只是一种存放方式，
        // 您可以用自定义常量等其它方式实现，来替换参数中的Utils.getMetaValue(PushDemoActivity.this,
        // "api_key")
        PushManager.startWork(getApplicationContext(),
                PushConstants.LOGIN_TYPE_API_KEY,
                Utils.getMetaValue(MainActivity.this, "api_key"));
        init();
//        RxView.clicks(btnClick)
//                .throttleFirst(5, TimeUnit.SECOND)
//                .subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//
//            }
//        });
        RxView.clicks(btnClick)
                .throttleFirst(3, java.util.concurrent.TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        LogUtil.i("连击");
                    }
                });

    }

    private void init() {
        myObservable.subscribe(subscriber);
        Observable.just("just  Hello, world!")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        LogUtil.i("action2:" + s);
                    }
                });
        Observable.just("xxxx").subscribe(s -> LogUtil.i("action2:" + s));

    }




}
