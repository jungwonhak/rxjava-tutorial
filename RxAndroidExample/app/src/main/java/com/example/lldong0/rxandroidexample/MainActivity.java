package com.example.lldong0.rxandroidexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lldong0.rxandroidexample.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new MainFragment(), MainFragment.TAG)
                    .commit();
        }
    }
}
