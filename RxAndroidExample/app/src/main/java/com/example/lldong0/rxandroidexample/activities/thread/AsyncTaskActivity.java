package com.example.lldong0.rxandroidexample.activities.thread;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.lldong0.rxandroidexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class AsyncTaskActivity extends AppCompatActivity {

    private static final String TAG = AsyncTaskActivity.class.getSimpleName();

    Unbinder mUnbinder;
    @BindView(R.id.text_title)
    TextView mAndroidTextView;
    @BindView(R.id.text_article)
    TextView mRxTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUnbinder = ButterKnife.bind(this);
//        initAndroidAsync();
        initRxAsync();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    private void initAndroidAsync() {
        new MyAsyncTask().execute("Hello", "async", "world");
    }

    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder word = new StringBuilder();
            for (String s : params) {
                word.append(s).append(" ");
            }
            return word.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mAndroidTextView.setText(result);
        }
    }


    @SuppressLint("CheckResult")
    private void initRxAsync() {
        Observable.just("Hello", "rx", "world")
                .reduce((x, y) -> x + " " + y)
                .observeOn(AndroidSchedulers.mainThread())
                //.subscribe(getObserver());
                .subscribe(
                        mRxTextView::setText,
                        e -> Log.e(TAG, e.getMessage()),
                        () -> Log.i(TAG, "done")
                );

    }


    private MaybeObserver<String> getObserver() {
        return new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(String result) {
                mRxTextView.setText(result);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "done");
            }
        };
    }
}


