package com.example.lldong0.rxandroidexample.fragments.recyclerview;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lldong0.rxandroidexample.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecyclerViewFragment extends Fragment {
    public static final String TAG = RecyclerViewFragment.class.getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerViewAdapter mRecyclerViewAdapter;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_recycler_veiw, container, false);
        ButterKnife.bind(this, layout);
        return layout;
    }

    /**
     * view 의 변경사항 조작
     * 1. recyclerView 의 layoutManager 객체 생성
     * 2. recyclerView 에 adapter 객체 생성및 연결
     * 3. PublishSubject 를 이용해서 뜨거운 Observable 구현(버튼 이벤트를 구현하기 위해서 사용)
     */

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerViewAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.getItemPublishSubject().subscribe(s -> snackBar(s.getTitle()));
    }

    /**
     * viewHolder 에 들어갈 데이터 셋 전달
     * 변경사항 적용
     */
    @Override
    public void onStart() {
        super.onStart();
        if (mRecyclerViewAdapter == null) {
            return;
        }
        getItemObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    mRecyclerViewAdapter.updateItems(item);
                    mRecyclerViewAdapter.notifyDataSetChanged();
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mUnbinder = null;
    }


    private Observable<RecyclerItem> getItemObservable() {

        final PackageManager pm = Objects.requireNonNull(getActivity()).getPackageManager();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        return Observable.fromIterable(pm.queryIntentActivities(i, 0))
                .sorted(new ResolveInfo.DisplayNameComparator(pm))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(item -> {
                    Drawable image = item.activityInfo.loadIcon(pm);
                    String title = item.activityInfo.loadLabel(pm).toString();
                    return new RecyclerItem(image, title);
                });
    }


    private void toast(String title) {
        Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), title, Toast.LENGTH_SHORT).show();
    }

    private void snackBar(String title) {
        final Snackbar snackbar = Snackbar.make(Objects.requireNonNull(getActivity()).findViewById(android.R.id.content), title, Snackbar.LENGTH_LONG);
        snackbar.setAction("취소", view -> snackbar.dismiss()).show();
    }
}
