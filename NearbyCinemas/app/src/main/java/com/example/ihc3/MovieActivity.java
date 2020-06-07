package com.example.ihc3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MovieActivity extends YouTubeBaseActivity {

    YouTubePlayerView playerView;
    YouTubePlayer.OnInitializedListener listner;
    boolean initialized;

    ImageView imgBuy1, imgBuy2, imgBuy3, imgBuy4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        playerView = (YouTubePlayerView) findViewById(R.id.youtubePlayer);
        initialized = false;

        ImageView simpleImageView=(ImageView) findViewById(R.id.imgThumb);
        simpleImageView.setImageResource(R.drawable.inception_poster);

        listner = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("YoHD9XEInc0");
                final YouTubePlayer ytbp = youTubePlayer;

                ytbp.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {

                    private boolean interceptPlay = true;

                    @Override
                    public void onPlaying() {
                        if (interceptPlay) {
                            ytbp.pause();
                            interceptPlay = false;
                        }
                    }

                    @Override
                    public void onStopped() {}

                    @Override
                    public void onSeekTo(int arg0) {}

                    @Override
                    public void onPaused() {}

                    @Override
                    public void onBuffering(boolean arg0) {}
                });

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }
        };

        playerView.initialize(YoutubeConfig.getApiKey(), listner);

        final Button btn = (Button) findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MovieActivity.this, "BUY!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MovieActivity.this, MainActivity.class);
                intent.putExtra("getOrder", "getOrder");
                startActivity(intent);
            }
        });


        ImageView imgArrow = (ImageView)findViewById(R.id.imgArrow);
        imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MovieActivity.this, "BACK!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MovieActivity.this, MainActivity.class);
                intent.putExtra("", "");
                startActivity(intent);
            }
        });

    }



}
