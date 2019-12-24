package com.example.potatoegame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick0(View view)
    {
        ImageView iv = (ImageView) findViewById(R.id.glasses);

        if(iv.getVisibility() == ImageView.VISIBLE)
        {
            iv.setVisibility(ImageView.INVISIBLE);
        }
        else {

            iv.setVisibility(ImageView.VISIBLE);
        }
    }
    public void onClick1(View view)
    {
        ImageView iv = (ImageView) findViewById(R.id.mouth);

        if(iv.getVisibility() == ImageView.VISIBLE)
        {
            iv.setVisibility(ImageView.INVISIBLE);
        }
        else {

            iv.setVisibility(ImageView.VISIBLE);
        }
    }
    public void onClick2(View view)
    {
        ImageView iv = (ImageView) findViewById(R.id.moustach);

        if(iv.getVisibility() == ImageView.VISIBLE)
        {
            iv.setVisibility(ImageView.INVISIBLE);
        }
        else {

            iv.setVisibility(ImageView.VISIBLE);
        }
    }
    public void onClick3(View view)
    {
        ImageView iv = (ImageView) findViewById(R.id.eyebrows);

        if(iv.getVisibility() == ImageView.VISIBLE)
        {
            iv.setVisibility(ImageView.INVISIBLE);
        }
        else {

            iv.setVisibility(ImageView.VISIBLE);
        }
    }
    public void onClick4(View view)
    {
        ImageView iv = (ImageView) findViewById(R.id.eyes);

        if(iv.getVisibility() == ImageView.VISIBLE)
        {
            iv.setVisibility(ImageView.INVISIBLE);
        }
        else {

            iv.setVisibility(ImageView.VISIBLE);
        }
    }
    public void onClick5(View view)
    {
        ImageView iv = (ImageView) findViewById(R.id.arms);

        if(iv.getVisibility() == ImageView.VISIBLE)
        {
            iv.setVisibility(ImageView.INVISIBLE);
        }
        else {

            iv.setVisibility(ImageView.VISIBLE);
        }
    }public void onClick6(View view)
    {
        ImageView iv = (ImageView) findViewById(R.id.ears);

        if(iv.getVisibility() == ImageView.VISIBLE)
        {
            iv.setVisibility(ImageView.INVISIBLE);
        }
        else {

            iv.setVisibility(ImageView.VISIBLE);
        }
    }public void onClick7(View view)
    {
        ImageView iv = (ImageView) findViewById(R.id.nose);

        if(iv.getVisibility() == ImageView.VISIBLE)
        {
            iv.setVisibility(ImageView.INVISIBLE);
        }
        else {

            iv.setVisibility(ImageView.VISIBLE);
        }
    }
    public void onClick8(View view)
    {
        ImageView iv = (ImageView) findViewById(R.id.shoes);

        if(iv.getVisibility() == ImageView.VISIBLE)
        {
            iv.setVisibility(ImageView.INVISIBLE);
        }
        else {

            iv.setVisibility(ImageView.VISIBLE);
        }
    }
    public void onClick9(View view)
    {
        ImageView iv = (ImageView) findViewById(R.id.hat);

        if(iv.getVisibility() == ImageView.VISIBLE)
        {
            iv.setVisibility(ImageView.INVISIBLE);
        }
        else {

            iv.setVisibility(ImageView.VISIBLE);
        }
    }
    public void onClick10(View view)
    {
        ImageView iv = (ImageView) findViewById(R.id.body);

        if(iv.getVisibility() == ImageView.VISIBLE)
        {
            iv.setVisibility(ImageView.INVISIBLE);
        }
        else {

            iv.setVisibility(ImageView.VISIBLE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        CheckBox cb = (CheckBox) findViewById(R.id.checkBox);
        outState.putBoolean("cb",cb.isChecked());
        Toast.makeText(this,"Save",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        CheckBox cb = (CheckBox) findViewById(R.id.checkBox);
        cb.setChecked(savedInstanceState.getBoolean("cb"));
        Toast.makeText(this,"Restore",Toast.LENGTH_SHORT).show();
    }
}
