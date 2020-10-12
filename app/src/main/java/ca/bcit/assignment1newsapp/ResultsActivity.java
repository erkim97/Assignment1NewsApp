package ca.bcit.assignment1newsapp;


import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;

public class ResultsActivity extends AppCompatActivity {
    public final static String ARTICLE = "";
    private ListView resultsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        resultsListView = findViewById(R.id.search_results);

        MyAsyncTask task = new MyAsyncTask();
        task.setKeyword(getIntent().getStringExtra(MainActivity.KEYWORD));
        task.execute();
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        private String keyword = "";
        private NewsResult newsSearchResults;

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String url = String.format("https://newsapi.org/v2/everything?q=%s&sortBy=publishedAt&apiKey=a8f63973151d478d818d365eac372eb0", this.keyword);
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
            Gson gson = new Gson();
            newsSearchResults = gson.fromJson(jsonStr, NewsResult.class);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
            ArticleAdapter articleAdapter= new ArticleAdapter(ResultsActivity.this, newsSearchResults.getArticles());
            resultsListView.setAdapter(articleAdapter);
        }
    }
}