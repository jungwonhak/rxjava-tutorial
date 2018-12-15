package com.example.lldong0.rxandroidexample.activities;


import android.os.Bundle;
import android.widget.TextView;

import com.example.lldong0.rxandroidexample.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

public class HelloActivityV3 extends RxAppCompatActivity {
    public static final String TAG = HelloActivityV3.class.getSimpleName();

    @BindView(R.id.text_title)
    TextView textView;

    private Unbinder mUnbinder;

    /**
     * RxLifecycle 사용
     * 메모리 관리를 하기 위해서 사용된다.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUnbinder = ButterKnife.bind(this);

        Observable.just("Hello, rx world!")
                .compose(bindToLifecycle())
                .subscribe(textView::setText);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
