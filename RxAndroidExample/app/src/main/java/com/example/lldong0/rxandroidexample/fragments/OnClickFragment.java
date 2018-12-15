package com.example.lldong0.rxandroidexample.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.lldong0.rxandroidexample.R;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.DisposableObserver;


public class OnClickFragment extends Fragment {
    public static final String TAG = OnClickFragment.class.getSimpleName();
    private static final int SEVEN = 7;

    @BindView(R.id.lv_log)
    ListView mLogView;
    @BindView(R.id.btn_click_java)
    Button mButtonJava;
    @BindView(R.id.btn_click_observer)
    Button mButton;
    @BindView(R.id.btn_click_observer_lambda)
    Button mButtonLambda;
    @BindView(R.id.btn_click_observer_binding)
    Button mButtonBinding;
    @BindView(R.id.btn_click_observer_extra)
    Button mButtonExtra;

    private Unbinder mUnbinder;
    private LogAdapter mLogAdapter;
    private List<String> mLogs;

    /**
     * Fragment 에 사용될 뷰를 생성하는 메소드
     *
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_on_click, container, false);
        mUnbinder = ButterKnife.bind(this, layout);
        setupLogger();
        return layout;
    }

    /**
     * Activity 에서 Fragment 를 모두 생성하고 난 다음에 호출되는 메소드
     * 모든 View 가 생성된 시점이기에 View 변경등을 작업할 수 있다.
     *
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getClickEventJava();

        getClickEventObservable()
                .map(s -> "clicked")
                .subscribe(getObserver());

        getClickEventObservableWithLambda()
                .map(s -> "clicked lambda")
                .subscribe(this::log);

        getClickEventObservableWithRxBinding()
                .subscribe(this::log);

        getClickEventObservableExtra()
                .map(local -> SEVEN)
                .flatMap(this::compareNumbers)
                .subscribe(this::log);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @OnClick(R.id.btn_click_java)
    public void getClickEventJava(){
        log("java");
    }

    private Observable<View> getClickEventObservable() {
        return Observable.create(new ObservableOnSubscribe<View>() {
            @Override
            public void subscribe(ObservableEmitter<View> e) throws Exception {
                mButton.setOnClickListener(e::onNext);
            }
        });
    }

    private Observable<View> getClickEventObservableWithLambda() {
        return Observable.create(s -> mButtonLambda.setOnClickListener(s::onNext));
    }

    private Observable<String> getClickEventObservableWithRxBinding() {
        return RxView.clicks(mButtonBinding)
                .map(s -> "Clicked Rxbinding");
    }

    private Observable<View> getClickEventObservableExtra() {
        return Observable.create(s -> mButtonExtra.setOnClickListener(s::onNext));
    }


    private Observable<String> compareNumbers(int input) {
        return Observable.just(input)
                .flatMap(in -> {
                    Random random = new Random();
                    int data = random.nextInt(10);
                    return Observable.just("local : " + in, "remote : " + data, "result = " + (in == data));
                });
    }


    private DisposableObserver<? super String> getObserver() {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                log(s);
            }

            @Override
            public void onError(Throwable e) {
                log(e.getMessage());
            }

            @Override
            public void onComplete() {
                log("complete");
            }
        };
    }

    private void log(String log) {
        mLogs.add(log);
        mLogAdapter.clear();
        mLogAdapter.addAll(mLogs);
    }

    private void setupLogger() {
        mLogs = new ArrayList<>();
        mLogAdapter = new LogAdapter(getActivity(), new ArrayList<>());
        mLogView.setAdapter(mLogAdapter);
    }

    private class LogAdapter extends ArrayAdapter<String> {
        public LogAdapter(Context context, List<String> logs) {
            super(context, R.layout.textview_log, R.id.tv_log, logs);
        }
    }
}
