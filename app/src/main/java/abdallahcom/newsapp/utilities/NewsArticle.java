package abdallahcom.newsapp.utilities;

import android.graphics.Bitmap;

/**
 * Created by Bashar on 7/10/2016.
 */
public class NewsArticle {
    private String title;
    private String description;
    private String url;
    private String pubDate;
    private Bitmap image;

    public NewsArticle(String title, String description, String url, String pubDate, Bitmap image) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.pubDate = pubDate;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", image=" + image +
                '}';
    }
}
