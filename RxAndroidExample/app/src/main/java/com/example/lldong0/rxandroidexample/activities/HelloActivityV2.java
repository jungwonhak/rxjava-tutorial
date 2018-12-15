package com.example.lldong0.rxandroidexample.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lldong0.rxandroidexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class HelloActivityV2 extends AppCompatActivity {

    private static final String TAG = HelloActivityV2.class.getSimpleName();

    @BindView(R.id.text_article)
    TextView textArticle;

    private CompositeDisposable mCompositeDisposable;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnBinder = ButterKnife.bind(this);

        helloRxAndroidWithCreate();
//        helloRxAndroidWithJust();

    }

    private void helloRxAndroidWithCreate() {
        mCompositeDisposable = new CompositeDisposable();
//        Disposable disposable = Observable.create(
//                (ObservableEmitter<String> emitter) -> {
//                    emitter.onNext("This Activity Version is 4");
//                    emitter.onComplete();
//                }).subscribe(
//                text -> textArticle.setText(text)
//        );

        Disposable disposable = Observable.<String>create(
                s -> {
                    s.onNext("This method is create()");
                    s.onComplete();
                }).subscribe(
                o -> textArticle.setText(o)
        );
        mCompositeDisposable.add(disposable);

    }

    private void helloRxAndroidWithJust() {
        mCompositeDisposable = new CompositeDisposable();
        Disposable disposable = Observable.just(
                "This method is just()"
        ).subscribe(textArticle::setText);
        mCompositeDisposable.add(disposable);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }

    }
}
