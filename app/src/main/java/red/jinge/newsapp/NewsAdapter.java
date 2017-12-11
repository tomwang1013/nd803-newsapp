package red.jinge.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Seeyon on 2017-12-11.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(@NonNull Context context, @NonNull List<News> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // 刚开始渲染的时候没有可重用的view，需要自己创建
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.news, parent, false);
        }

        News book = getItem(position);
        TextView textTitle = convertView.findViewById(R.id.text_view_title);
        TextView textAuthor = convertView.findViewById(R.id.text_view_date);


        textTitle.setText(book.getTitle());
        textAuthor.setText(book.getDate());


        return convertView;
    }
}
