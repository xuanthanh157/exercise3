package com.example.xuanthanh.homeexercise_week3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.xuanthanh.homeexercise_week3.pojo.Result;
import com.squareup.picasso.Picasso;

public class MovieDetailed extends AppCompatActivity {
    private static final String HEADER_URL_IMAGE = "https://image.tmdb.org/t/p/w500/";

    TextView tvTitle, tvOverview, tvDate;
    ImageView imgVideo;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie__detailed);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgVideo = findViewById(R.id.imgVideo);
        tvDate = findViewById(R.id.tvDate);
        tvOverview = findViewById(R.id.tvOverview);
        tvTitle = findViewById(R.id.tvTitle);
        ratingBar = findViewById(R.id.ratingbar);

        Intent intent = getIntent();
        if (intent != null) {
            Result result = (Result) intent.getSerializableExtra("detail");
            if (result != null) {
                tvTitle.setText(result.getTitle());
                Picasso.with(getApplicationContext()).load(HEADER_URL_IMAGE + result.getBackdropPath())
                        .fit()
                        .centerInside()
                        .into(imgVideo);
                tvDate.setText("Release Date: " + result.getReleaseDate());
                tvOverview.setText(result.getOverview());
                ratingBar.setRating(result.getVoteAverage() / 2);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
