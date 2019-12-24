package com.example.dynamicfragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.os.Bundle;

import static com.example.dynamicfragments.R.id.detailscontainer;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get fragment manager
        FragmentManager fm = getSupportFragmentManager();
        // add
        BlankFragment b1 = new BlankFragment();
        BlankFragment2 b2 = new BlankFragment2();
        fm.beginTransaction().add(R.id.listFragment,b1)
                .add(detailscontainer,b2).commit();

    }
}
