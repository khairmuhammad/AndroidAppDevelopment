package com.example.activity3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MySimpleArrayAdapter(Context context, String[] values) {
        super(context, R.layout.activity_main, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_main, parent,
                false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values[position]);

        String s = values[position];
        if(s.equals("WhatsApp"))
            imageView.setImageResource(R.drawable.i0);
        else if(s.equals("Camera"))
            imageView.setImageResource(R.drawable.i4);
        else if(s.equals("Line"))
            imageView.setImageResource(R.drawable.i5);
        else if(s.equals("Dropbox"))
            imageView.setImageResource(R.drawable.i2);
        else if(s.equals("Google+"))
            imageView.setImageResource(R.drawable.i3);
        else if(s.equals("SnapChat"))
            imageView.setImageResource(R.drawable.i9);
        else if(s.equals("Skype"))
            imageView.setImageResource(R.drawable.i8);
        else if(s.equals("Qoura"))
            imageView.setImageResource(R.drawable.i7);
        else if(s.equals("SoundCloud"))
            imageView.setImageResource(R.drawable.i10);

        return rowView;
    }
}