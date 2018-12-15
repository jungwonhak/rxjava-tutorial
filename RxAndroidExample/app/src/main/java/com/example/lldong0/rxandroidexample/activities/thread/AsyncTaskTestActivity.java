package com.example.lldong0.rxandroidexample.activities.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.lldong0.rxandroidexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsyncTaskTestActivity extends AppCompatActivity {
    private static final String TAG = AsyncTaskTestActivity.class.getSimpleName();

    @BindView(R.id.text_fib)
    TextView textFib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_test);
        ButterKnife.bind(this);
        testAsyncTask();
    }

    private void testAsyncTask() {
        new AsyncTaskExample().execute(10);
    }

    private class AsyncTaskExample extends AsyncTask<Integer, Integer, String> {

        private final int MEMO_LENGTH = 20;
        private final int[] memo = new int[MEMO_LENGTH];

        // 피보나치
        private int fib(int n, int[] memo) {
            if (n <= 0) return 0;
            if (n == 1) return memo[0] = 1;
            else if (memo[n] > 0) return memo[n];
            return memo[n] = fib(n - 2, memo) + fib(n - 1, memo);
        }

        // 백그라운드 실해전 준비 단계
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        // 메인 로직이 진행되는 단계
        // 실행중에 publishProgress()을 호출하면 onProgressUpdate()가 실행
        @Override
        protected String doInBackground(Integer... integers) {
            publishProgress(5);
            return Integer.toString(fib(integers[0], memo));
        }

        // 사용자에게 백그라운드의 진행을 알릴때 사용
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d(TAG, values[0] + "%");
        }

        // 백그라운드 작업 완료 결과값
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            textFib.setText(result);
//            progressBar.setVisibility(ProgressBar.INVISIBLE);
            Log.d(TAG, "Result :" + result);
        }

    }

}
