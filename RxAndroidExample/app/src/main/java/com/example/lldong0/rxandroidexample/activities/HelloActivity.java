package com.example.lldong0.rxandroidexample.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.lldong0.rxandroidexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class HelloActivity extends AppCompatActivity {
    public static final String TAG = HelloActivity.class.getSimpleName();

    @BindView(R.id.text_title)
    TextView textView;

    private Disposable mDisposable;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUnbinder = ButterKnife.bind(this);

        DisposableObserver<String> observer = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                textView.setText(s);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };

        mDisposable = Observable.create((ObservableOnSubscribe<String>) e -> {
            e.onNext("Hello RxAndroid! This Activity version is 1");
            e.onComplete();
        }).subscribeWith(observer);
        Log.d(TAG, "onCreate 실행");

        // 구독이 처리되지 않았을때에 대한 잠재적인 메모리 누수에 대한 해결법
        CompositeDisposable compositeDisposable = new CompositeDisposable();

        Disposable disposable = Observable.create(
                (ObservableEmitter<String> emitter) -> {
                    emitter.onNext("test message");
                    emitter.onComplete();
                }
        ).subscribe(
                val -> textView.setText(val)
        );

        compositeDisposable.add(disposable);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
