package ca.bcit.assignment1newsapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsResult {
    @SerializedName("articles")
    @Expose
    private ArrayList<Article> articles;
    public ArrayList<Article> getArticles() {
        return articles;
    }
}