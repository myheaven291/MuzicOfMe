package com.example.apple.demomusic.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apple.demomusic.R;
import com.example.apple.demomusic.adapters.MusicTypeAdapter;
import com.example.apple.demomusic.databases.MusicTypeModel;
import com.example.apple.demomusic.events.OnClickMusicType;
import com.example.apple.demomusic.managers.ScreenManager;
import com.example.apple.demomusic.networks.services.GetMusicTypes;
import com.example.apple.demomusic.networks.RetrofitFactory;
import com.example.apple.demomusic.networks.json_models.musictypes.AllMusicTypesJSONModel;
import com.example.apple.demomusic.networks.json_models.musictypes.MusicTypeJSONModel;

import org.greenrobot.eventbus.EventBus;

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
public class MusicFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = MusicFragment.class.toString();
    @BindView(R.id.rv_music)
    RecyclerView rvMusicTypes;
    MusicTypeAdapter musicTypeAdapter;

    private List<MusicTypeModel> musicTypeModelList = new ArrayList<>();


    public MusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        setupUI(view);
        loadData();

        return view;
    }

    private void loadData() {
        final GetMusicTypes getMusicTypes = RetrofitFactory.getInstance().create(GetMusicTypes.class);

        getMusicTypes.getMusicTypes().enqueue(new Callback<AllMusicTypesJSONModel>() {
            @Override
            public void onResponse(Call<AllMusicTypesJSONModel> call, Response<AllMusicTypesJSONModel> response) {
                for(MusicTypeJSONModel musicTypeJSONModel : response.body().getSubgenres()){
                    MusicTypeModel musicTypeModel = new MusicTypeModel();
                    musicTypeModel.setId(musicTypeJSONModel.getId());
                    musicTypeModel.setKey(musicTypeJSONModel.getTranslation_key());

                    musicTypeModel.setImageID(getContext().getResources().getIdentifier("genre_x2_" + musicTypeJSONModel.getId(), "raw", getContext().getPackageName()));

                    musicTypeModelList.add(musicTypeModel);
                }

                musicTypeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<AllMusicTypesJSONModel> call, Throwable t) {
                Toast.makeText(getContext(), "There is no connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);

        musicTypeAdapter = new MusicTypeAdapter(musicTypeModelList, getContext());
        rvMusicTypes.setAdapter(musicTypeAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 0 ? 2 : 1;
            }
        });

        rvMusicTypes.setLayoutManager(gridLayoutManager);
        musicTypeAdapter.setOnItemClick(this);
    }

    @Override
    public void onClick(View view) {
        MusicTypeModel musicTypeModel = (MusicTypeModel) view.getTag();

        EventBus.getDefault().postSticky(new OnClickMusicType(musicTypeModel));

        ScreenManager.openFragment(getActivity().getSupportFragmentManager(),new TopSongFragment(),R.id.layout_container,true);
    }
}
