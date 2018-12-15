package com.example.lldong0.rxandroidexample.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lldong0.rxandroidexample.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class DebounceFragment extends Fragment {

    @BindView(R.id.btn_debounce)
    Button mButton;
    @BindView(R.id.tv_display)
    TextView mDisplay;

    Unbinder mUnbinder;
    Disposable mDisposable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout =  inflater.inflate(R.layout.fragment_debounce, container, false);
        mUnbinder = ButterKnife.bind(this, layout);
        return layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mDisposable.dispose();
    }

    /**
     * 액티비티 중복 실행 해결
     * debounce() 이용
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mDisposable = getObservable()
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
                    String time = sdf.format(Calendar.getInstance().getTime());
                    mDisplay.setText("Clicked : " + time.toString());
                });

    }

    private Observable<View> getObservable() {
        return Observable.create(e ->  mButton.setOnClickListener(e::onNext));
    }
}
