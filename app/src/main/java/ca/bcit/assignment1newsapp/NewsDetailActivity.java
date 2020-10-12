package ca.bcit.assignment1newsapp;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NewsDetailActivity extends AppCompatActivity {
    private Article article;
    private ImageView articleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_results);
        String articleInStr = getIntent().getStringExtra(ResultsActivity.ARTICLE);
        if (articleInStr == null) {
            return;
        }

        Gson gson = new Gson();
        article = gson.fromJson(articleInStr, Article.class);

        articleImage = findViewById(R.id.article_image);
        TextView articleTitle = findViewById(R.id.article_title);
        TextView articleAuthor = findViewById(R.id.article_author);
        TextView articleBody = findViewById(R.id.article_body);
        TextView articleDescription = findViewById(R.id.article_description);
        TextView articlePublishedDate = findViewById(R.id.article_publish_date);
        TextView articleSource = findViewById(R.id.article_source);
        TextView articleSourceURL = findViewById(R.id.source_url);

        articleTitle.setText(article.getTitle());
        articleAuthor.setText(article.getAuthor());
        articleBody.setText(article.getContent());
        articleDescription.setText(article.getDescription());
        articlePublishedDate.setText(article.getPublishedAt());
        articleSource.setText(article.getSource().getName());
        articleSourceURL.setText(article.getUrl());

        ImageDisplay downloader = new ImageDisplay();
        downloader.execute();
    }

    private class ImageDisplay extends AsyncTask<Void, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Void... arg0) {
            return downloadBitmap(article.getUrlToImage());
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null && articleImage != null) {
                articleImage.setImageBitmap(result);
            }
        }

        private Bitmap downloadBitmap(String url) {
            HttpURLConnection urlConnection = null;
            try {
                URL uri = new URL(url);
                urlConnection = (HttpURLConnection) uri.openConnection();
                int statusCode = urlConnection.getResponseCode();
                if (statusCode !=  HttpURLConnection.HTTP_OK) {
                    return null;
                }

                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream != null) {
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }
            } catch (Exception e) {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                Log.w("ImageDisplay", "Error downloading image from " + url);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }
    }
}
