package net.g3infotech.culinaria.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;

import net.g3infotech.culinaria.R;
import net.g3infotech.culinaria.entitie.Step;
import net.g3infotech.culinaria.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepFragment extends ListFragment {

    List<Step> mSteps;
    @BindView(R.id.tv_description)
    TextView mDescription;
    @BindView(R.id.iv_error)
    ImageView mIvError;
    SimpleExoPlayerView mSimpleExoPlayerView;
    SimpleExoPlayer mExoPlayer;

    public StepFragment() {
        // Required empty public constructor
    }

    public static StepFragment newInstance(Step step) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.SEND_STEP, step);
        StepFragment fragment = new StepFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_step, container, false);
        if(getArguments() != null){
            Step step = getArguments().getParcelable(Constants.SEND_STEP);

            mSimpleExoPlayerView = view.findViewById(R.id.simple_player_steps);
            ButterKnife.bind(this, view);
            mDescription.setText(step.getDescription());
            if(!"".equals(step.getVideoURL())){
                initializeExoPlayer(step.getVideoURL());
                mIvError.setVisibility(View.INVISIBLE);
            }else{
                mSimpleExoPlayerView.setVisibility(View.INVISIBLE);
                mIvError.setVisibility(View.VISIBLE);
            }
        }
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        pausePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        startPlayer();
    }

    private void pausePlayer(){
        mExoPlayer.setPlayWhenReady(false);
        mExoPlayer.getPlaybackState();
    }
    private void startPlayer(){
        mExoPlayer.setPlayWhenReady(true);
        mExoPlayer.getPlaybackState();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<Step>(getActivity(), android.R.layout.simple_list_item_1));
    }

    private void initializeExoPlayer(String url) {
        if(mExoPlayer == null) {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelection = new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelection);

            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);
            mSimpleExoPlayerView.setPlayer(mExoPlayer);
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                    Util.getUserAgent(getContext(), getContext().getPackageName()),
                    (TransferListener<? super DataSource>) bandwidthMeter);

            MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(url));
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

}
