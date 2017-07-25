package com.example.apple.demomusic;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.apple.demomusic.adapters.PagerAdapter;
import com.example.apple.demomusic.databases.TopSongModel;
import com.example.apple.demomusic.events.OnClickMusicSong;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.seek_bar_player)
    SeekBar seekBar;
    @BindView(R.id.layout_mini_container)
    RelativeLayout layout_mini_player;
    @BindView(R.id.iv_song)
    ImageView ivSong;
    @BindView(R.id.tv_song_name)
    TextView tvSongName;
    @BindView(R.id.tv_artist)
    TextView tvArtist;
    private TopSongModel topSongModel;

    private static final String TAG = MainActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
        setUX();
    }

    public void setUI() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        seekBar.setPadding(0, 0, 0, 0);
        seekBar.getThumb().mutate().setAlpha(0);
        layout_mini_player.setVisibility(View.GONE);
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
            tabLayout.getTabAt(i).getIcon().setAlpha(100);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(255);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(100);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Subscribe(sticky = true)
    public void onClickMusicSong(OnClickMusicSong onClickMusicSong) {
        topSongModel = onClickMusicSong.getTopSongModel();
        layout_mini_player.setVisibility(View.VISIBLE);

        Picasso.with(this).load(topSongModel.getImage()).transform(new CropCircleTransformation()).into(ivSong);
        tvSongName.setText(topSongModel.getName());
        tvArtist.setText(topSongModel.getArtist());
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }
}
