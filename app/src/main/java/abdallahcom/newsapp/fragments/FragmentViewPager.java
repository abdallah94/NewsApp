package abdallahcom.newsapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import abdallahcom.newsapp.Constants;
import abdallahcom.newsapp.R;
import abdallahcom.newsapp.utilities.NewsArticle;

/**
 * Created by Abdallah on 7/4/2016.
 */
public class FragmentViewPager extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NewsArticle article = (NewsArticle) getArguments().getSerializable(Constants.ARTICLE_KEY);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        TextView titleTextView = (TextView) view.findViewById(R.id.title);
        TextView descriptionTextView = (TextView) view.findViewById(R.id.description);
        TextView dateTextView = (TextView) view.findViewById(R.id.pubDate);
        imageView.setImageBitmap(article.getImage());
        titleTextView.setText(article.getTitle());
        descriptionTextView.setText(article.getDescription());
        dateTextView.setText(article.getPubDate());
        return view;
    }


    public static FragmentViewPager newInstance(NewsArticle article) {
        FragmentViewPager f = new FragmentViewPager();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARTICLE_KEY, article);
        f.setArguments(bundle);
        return f;
    }

}
