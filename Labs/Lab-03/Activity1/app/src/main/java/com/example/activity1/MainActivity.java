package com.example.activity1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView iv1 = (TextView) findViewById(R.id.Answer1);
        iv1.setVisibility(TextView.INVISIBLE);

        TextView iv2 = (TextView) findViewById(R.id.Answer2);
        iv2.setVisibility(TextView.INVISIBLE);

        TextView iv3 = (TextView) findViewById(R.id.Answer3);
        iv3.setVisibility(TextView.INVISIBLE);

        TextView iv4 = (TextView) findViewById(R.id.Answer4);
        iv4.setVisibility(TextView.INVISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"On Start",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"On Resume",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"On Pause",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"On Stop",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onClickAns1(View view)
    {
        TextView iv = (TextView) findViewById(R.id.Answer1);
        iv.setVisibility(TextView.VISIBLE);
    }

    public void onClickAns2(View view)
    {
        TextView iv = (TextView) findViewById(R.id.Answer2);
        iv.setVisibility(TextView.VISIBLE);
    }

    public void onClickAns3(View view)
    {
        TextView iv = (TextView) findViewById(R.id.Answer3);
        iv.setVisibility(TextView.VISIBLE);
    }

    public void onClickAns4(View view)
    {
        TextView iv = (TextView) findViewById(R.id.Answer4);
        iv.setVisibility(TextView.VISIBLE);
    }

}
