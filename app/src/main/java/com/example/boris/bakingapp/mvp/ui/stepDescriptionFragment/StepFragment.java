package com.example.boris.bakingapp.mvp.ui.stepDescriptionFragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.bumptech.glide.Glide;
import com.example.boris.bakingapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.boris.bakingapp.constants.Contract.TAG_VAR_VALUE;
import static com.example.boris.bakingapp.constants.Contract.TAG_WORK_CHECKING;

public class StepFragment extends MvpAppCompatFragment {

    private static final String EXTRA_DESCRIPTION_ID = "EXTRA_DESCRIPTION_ID";
    private static final String EXTRA_IMAGE_URL_ID = "EXTRA_IMAGE_URL_ID";
    private static final String EXTRA_VIDEO_URL_ID = "EXTRA_VIDEO_URL_ID";

    @BindView(R.id.recipe_step_desc)
    TextView descTextView;

    @BindView(R.id.recipe_step_video)
    SimpleExoPlayerView playerView;

    @BindView(R.id.recipe_step_image)
    ImageView image;

    private SimpleExoPlayer player;

    View view;

    Unbinder unbinder;

    private long playbackPosition;
    private boolean playWhenReady = true;

    private boolean isVisible;
    private boolean isStarted;

    private static final String PLAYER_POSITION = "player position";
    private static final String PLAYER_STATE = "player state";

    public static StepFragment newInstance(String description, String imageUrl, String videoUrl) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_DESCRIPTION_ID, description);
        arguments.putString(EXTRA_IMAGE_URL_ID, imageUrl);
        arguments.putString(EXTRA_VIDEO_URL_ID, videoUrl);
        StepFragment fragment = new StepFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    public StepFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.step_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        setRetainInstance(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        String description = getArguments().getString(EXTRA_DESCRIPTION_ID);
        descTextView.setText(description);

        Log.d(TAG_WORK_CHECKING, "Thumbnail is - " + getArguments().getString("thumbnailURL"));

        Glide.with(this)
                .load(getArguments().getString(EXTRA_IMAGE_URL_ID))
                .into(image);

        String video = getArguments().getString(EXTRA_VIDEO_URL_ID);

        if (video != null && !video.isEmpty()) {
            // Init and show video view
            setViewVisibility(playerView, true);

            if (savedInstanceState != null) {
                Log.d(TAG_VAR_VALUE, "saved instance isn`t null");
                playbackPosition = savedInstanceState.getLong(PLAYER_POSITION);
                playWhenReady = savedInstanceState.getBoolean(PLAYER_STATE);
            }

            initializePlayer(video);
        } else {
            // Hide video view
            setViewVisibility(playerView, false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setViewVisibility(View view, boolean show) {
        if (show) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (player != null) {
            Log.d(TAG_VAR_VALUE, "player is not null");
            outState.putLong(PLAYER_POSITION, player.getCurrentPosition());
            outState.putBoolean(PLAYER_STATE, player.getPlayWhenReady());
        } else {
            Log.d(TAG_VAR_VALUE, "player is null");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void initializePlayer(String videoUrl) {
        if(player == null) {
            player = ExoPlayerFactory.newSimpleInstance(
                    new DefaultRenderersFactory(getContext()),
                    new DefaultTrackSelector(),
                    new DefaultLoadControl());
            playerView.setPlayer(player);
            player.setPlayWhenReady(playWhenReady);
            player.seekTo(playbackPosition);
        }

        MediaSource mediaSource = buildMediaSource(Uri.parse(videoUrl));
        player.prepare(mediaSource, true, false);
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            playWhenReady = player.getPlayWhenReady();
            player.release();
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        String userAgent = "exoplayer";

        if (uri.getLastPathSegment().contains("mp3") || uri.getLastPathSegment().contains("mp4")) {
            return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory(userAgent))
                    .createMediaSource(uri);
        } else if (uri.getLastPathSegment().contains("m3u8")) {
            return new HlsMediaSource.Factory(new DefaultHttpDataSourceFactory(userAgent))
                    .createMediaSource(uri);
        } else {
            DashChunkSource.Factory dashChunkSourceFactory = new DefaultDashChunkSource.Factory(
                    new DefaultHttpDataSourceFactory("ua", new DefaultBandwidthMeter()));
            DataSource.Factory manifestDataSourceFactory = new DefaultHttpDataSourceFactory(userAgent);
            return new DashMediaSource.Factory(dashChunkSourceFactory, manifestDataSourceFactory).
                    createMediaSource(uri);
        }
    }

}
