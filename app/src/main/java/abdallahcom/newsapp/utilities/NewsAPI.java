package abdallahcom.newsapp.utilities;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Bashar on 7/10/2016.
 */
public interface NewsAPI {
    /*Retrofit get annotation with our ROOT_URL
       And our method that will return us the list ob Book
    */
    @GET("/arabic/rss.xml")
    void getNewsArticles(Callback<NewsArticle> newsArticle);
}
