package abdallahcom.newsapp;

import android.app.Application;

import java.util.ArrayList;

import abdallahcom.newsapp.utilities.NewsArticle;

/**
 * Created by Abdallah on 7/10/2016.
 */
public class ApplicationClass extends Application {
    public final static String ARTICLE_KEY = "article";
    public static ArrayList<NewsArticle> newsList;
}
