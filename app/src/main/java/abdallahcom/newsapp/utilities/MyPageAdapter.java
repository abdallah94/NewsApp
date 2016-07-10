package abdallahcom.newsapp.utilities;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import abdallahcom.newsapp.ApplicationClass;
import abdallahcom.newsapp.R;
import abdallahcom.newsapp.fragments.FragmentViewPager;

/**
 * Created by Abdallah on 7/4/2016.
 */
public class MyPageAdapter extends FragmentPagerAdapter {
    private Context context;

    public MyPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int pos) {

                return FragmentViewPager.newInstance(ApplicationClass.newsList.get(pos));
    }

    @Override
    public int getCount() {
        return 3;
    }

}
