package com.example.apple.demomusic;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.apple.demomusic.adapters.PagerAdapter;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private static final String TAG = MainActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final GetMusicTypes getMusicTypes = RetrofitFactory.getInstance().create(GetMusicTypes.class);
//
//        getMusicTypes.getMusicTypes().enqueue(new Callback<AllMusicTypesJSONModel>() {
//            @Override
//            public void onResponse(Call<AllMusicTypesJSONModel> call, Response<AllMusicTypesJSONModel> response) {
//                List<MusicTypeJSONModel> list = response.body().getSubgenres();
//                for(int i = 0; i < list.size(); i ++){
//                    MusicTypeJSONModel m = list.get(i);
//                    Log.d(TAG, m.getId() + " " + m.getTranslation_key());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AllMusicTypesJSONModel> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "There is no connection", Toast.LENGTH_SHORT).show();
//            }
//        });
        setUI();
        setUX();
    }

    public void setUI() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
    }

    public void setUX() {
        FragmentManager fm = getSupportFragmentManager();
        PagerAdapter pagerAdapter = new PagerAdapter(fm);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        int[] imageResId = {
                R.drawable.ic_music,
                R.drawable.ic_favorite,
                R.drawable.ic_offline
        };

        for (int i = 0; i < imageResId.length; i++) {
            tabLayout.getTabAt(i).setIcon(imageResId[i]);

        }
        for (int i = 1; i < imageResId.length; i++) {
            tabLayout.getTabAt(i).getIcon().setAlpha(90);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(1000);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(90);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(90);
            }
        });
    }
}
