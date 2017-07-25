package com.example.apple.demomusic.fragments;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.demomusic.MainActivity;
import com.example.apple.demomusic.R;
import com.example.apple.demomusic.adapters.TopSongsAdapter;
import com.example.apple.demomusic.databases.MusicTypeModel;
import com.example.apple.demomusic.databases.TopSongModel;
import com.example.apple.demomusic.events.OnClickMusicSong;
import com.example.apple.demomusic.events.OnClickMusicType;
import com.example.apple.demomusic.managers.MusicManager;
import com.example.apple.demomusic.managers.ScreenManager;
import com.example.apple.demomusic.networks.services.GetTopSongs;
import com.example.apple.demomusic.networks.RetrofitFactory;
import com.example.apple.demomusic.networks.json_models.topsongs.SongDetailJSONModel;
import com.example.apple.demomusic.networks.json_models.topsongs.TopSongsJSONModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = TopSongFragment.class.toString();
    @BindView(R.id.rv_top_song)
    RecyclerView rvTopSongs;
    TopSongsAdapter topSongsAdapter;
    @BindView(R.id.tv_type_song)
    TextView tvType;
    @BindView(R.id.tv_number_song)
    TextView tvNumbers;
    @BindView(R.id.iv_top_song)
    ImageView ivTopSong;
    @BindView(R.id.toolbar)
    Toolbar tbTopSongs;

    private List<TopSongModel> topSongModelList = new ArrayList<>();
    private MusicTypeModel musicTypeModel;
    public boolean allowBackPressed = true;

    public TopSongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_song, container, false);
        setupUI(view);
        loadData();

        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);

        EventBus.getDefault().register(this);
        topSongsAdapter = new TopSongsAdapter(topSongModelList, getContext());
        rvTopSongs.setAdapter(topSongsAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rvTopSongs.addItemDecoration(dividerItemDecoration);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTopSongs.setLayoutManager(manager);

        ivTopSong.setImageResource(musicTypeModel.getImageID());
        topSongsAdapter.setOnItemClick(this);

//        if (tbTopSongs != null) {
//            ((AppCompatActivity) getActivity()).setSupportActionBar(tbTopSongs);
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
//        }

        ImageView ivBack = tbTopSongs.findViewById(R.id.iv_back);
        ImageView ivBookmark = tbTopSongs.findViewById(R.id.iv_bookmark_favourite);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.getTabLayout().setVisibility(View.VISIBLE);

                getActivity().onBackPressed();
            }
        });
    }

    @Subscribe(sticky = true)
    public void onClickMusicType(OnClickMusicType onclickMusicType) {
        musicTypeModel = onclickMusicType.getMusicTypeModel();
    }

    private void loadData() {
        final GetTopSongs getTopSongs = RetrofitFactory.getInstance().create(GetTopSongs.class);
        getTopSongs.getTopSongs(musicTypeModel.getId()).enqueue(new Callback<TopSongsJSONModel>() {
            @Override
            public void onResponse(Call<TopSongsJSONModel> call, Response<TopSongsJSONModel> response) {
                List<SongDetailJSONModel> entry = response.body().getFeed().getEntry();
                for (SongDetailJSONModel songDeailJSONModel : entry) {
                    TopSongModel topSongModel = new TopSongModel();
                    topSongModel.setName(songDeailJSONModel.getName().getLabel());
                    topSongModel.setArtist(songDeailJSONModel.getArtist().getLabel());
                    topSongModel.setImage(songDeailJSONModel.getImages().get(2).getLabel());

                    topSongModelList.add(topSongModel);
                }

                tvNumbers.setText("" + topSongModelList.size() + " songs");
                tvType.setText(musicTypeModel.getKey());
                topSongsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TopSongsJSONModel> call, Throwable t) {
                Toast.makeText(getContext(), "There is no connection!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        TopSongModel topSongModel = (TopSongModel) view.getTag();
        EventBus.getDefault().postSticky(new OnClickMusicSong(topSongModel, true));
        MusicManager.loadSearchSong(topSongModel, getContext());
    }
}
