package abdallahcom.newsapp;

import android.app.Application;

import java.util.ArrayList;

import abdallahcom.newsapp.newsservices.NewsServices;
import abdallahcom.newsapp.utilities.NewsArticle;

/**
 * Created by Abdallah on 7/10/2016.
 */
public class ApplicationClass extends Application {
    public static ArrayList<NewsArticle> newsList;

    @Override
    public void onCreate() {
        super.onCreate();
        NewsServices.parse(Constants.URL);
    }
}
