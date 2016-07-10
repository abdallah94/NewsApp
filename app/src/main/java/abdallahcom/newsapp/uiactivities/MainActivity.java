package abdallahcom.newsapp.uiactivities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import abdallahcom.newsapp.R;
import abdallahcom.newsapp.utilities.MyPageAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager(), this));
    }
}
