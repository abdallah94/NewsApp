package abdallahcom.newsapp;

import android.app.Application;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import abdallahcom.newsapp.newsservices.NewsServices;
import abdallahcom.newsapp.utilities.NewsArticle;

/**
 * Created by Abdallah on 7/10/2016.
 */
public class ApplicationClass extends Application {
    public static List<NewsArticle> newsList;

    @Override
    public void onCreate() {
        NewsServices newsServices=new NewsServices();
        super.onCreate();
        try {
            newsList = newsServices.parse(Constants.URL);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
