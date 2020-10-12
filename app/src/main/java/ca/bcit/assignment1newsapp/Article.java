package ca.bcit.assignment1newsapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article {

    public class Source {
        @SerializedName("name")
        @Expose
        private String name;
        public String getName() {
            return this.name;
        }
    }

    @SerializedName("author")
    @Expose
    private String author;
    public String getAuthor() {
        if (author == null) {
            return "N/A";
        }
        return author;
    }

    @SerializedName("title")
    @Expose
    private String title;
    public String getTitle() {
        if (this.title == null) {
            return "N/A";
        }
        return this.title;
    }

    @SerializedName("description")
    @Expose
    private String description;
    public String getDescription() {
        if (this.description == null) {
            return "N/A";
        }
        return description;
    }

    @SerializedName("source")
    @Expose
    private Source source;
    public Source getSource() {
        return source;
    }

    @SerializedName("url")
    @Expose
    private String url;
    public String getUrl() {
        return this.url;
    }

    @SerializedName("urlToImage")
    @Expose
    private String urlToImage;
    public String getUrlToImage() {
        return urlToImage;
    }

    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    public String getPublishedAt() {
        return publishedAt;
    }

    @SerializedName("content")
    @Expose
    private String content;
    public String getContent() {
        return content;
    }
}
