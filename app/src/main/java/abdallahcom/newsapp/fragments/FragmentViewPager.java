package abdallahcom.newsapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import abdallahcom.newsapp.R;

/**
 * Created by Abdallah on 7/4/2016.
 */
public class FragmentViewPager extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ImageView imageView = (ImageView) v.findViewById(R.id.image);
        //tv.setText(getArguments().getString("text"));
        TextView titleTextView = (TextView) v.findViewById(R.id.title);
        TextView descriptionTextView = (TextView) v.findViewById(R.id.description);
        TextView dateTextView = (TextView) v.findViewById(R.id.pubDate);
        //imageView.setBackgroundResource(getArguments().getInt("img"));
        return v;
    }


    public static FragmentViewPager newInstance(String text, int image) {


        FragmentViewPager f = new FragmentViewPager();

        Bundle b = new Bundle();

        b.putString("text", text);

        b.putInt("img", image);
        f.setArguments(b);
        return f;
    }

}
