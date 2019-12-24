package com.example.android.custom_listview_adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Book> {

    private Context mContext;
    private List<Book> bookList = new ArrayList<>();

    public CustomAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Book> list) {
        super(context, 0 , list);
        mContext = context;
        bookList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Book currentBook = bookList.get(position);

        ImageView imageView = (ImageView) listItem.findViewById(R.id.book_coverPage);
        imageView.setImageResource(currentBook.getImgID());

        TextView name = (TextView) listItem.findViewById(R.id.txtV_book_name);
        name.setText(currentBook.getBook_name());

        TextView author = (TextView) listItem.findViewById(R.id.txtV_author);
        author.setText(currentBook.getAuthor());

        TextView year = (TextView) listItem.findViewById(R.id.txtV_year);
        year.setText(currentBook.getYear());


        return listItem;

    }
}
