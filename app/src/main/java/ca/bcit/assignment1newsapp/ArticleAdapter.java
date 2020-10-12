package ca.bcit.assignment1newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ArticleAdapter extends ArrayAdapter<Article> {
    Context context;

    public ArticleAdapter(Context context, ArrayList<Article> articles) {
        super(context, 0, articles);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get dataitem at position
        final Article article = getItem(position);
        // convertView, the old view to reuse, if possible. Note: You should check that this view is non-null and of an appropriate type before using.
        // If it is not possible to convert this view to display the correct data, this method can create a new view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_article_list, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.article_title);
        if (article.getTitle() != null) {
            textView.setText(article.getTitle());
        }
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, NewsDetailActivity.class);
                Gson gson = new Gson();
                String articleInStr = gson.toJson(article, Article.class);
                i.putExtra(ResultsActivity.ARTICLE, articleInStr);
                context.startActivity(i);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}
