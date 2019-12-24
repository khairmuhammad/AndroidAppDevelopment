package com.example.android.lab_exam_mid_2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class CustomList extends ArrayAdapter<Book> {


    private final Activity context;
    //private final Book[] web;
    private final Integer[] imageId;
    private final ArrayList<Book> arrayList;

    public CustomList(Activity context, Integer[] imageId, ArrayList<Book> arrayList) {
        super(context, R.layout.list_layout, arrayList);

        this.context = context;
        this.imageId = imageId;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_layout, null, true);
        ImageView imageView = rowView.findViewById(R.id.img);
        TextView name = rowView.findViewById(R.id.name);
        TextView author = rowView.findViewById(R.id.author);
        TextView year = rowView.findViewById(R.id.year);

        imageView.setImageResource(imageId[position]);
        name.setText(arrayList.get(position).book_name);
        author.setText(arrayList.get(position).author);
        year.setText(arrayList.get(position).year);

        return rowView;
    }
}
