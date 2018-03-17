package com.example.xuanthanh.homeexercise_week3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.xuanthanh.homeexercise_week3.pojo.Movie;
import com.example.xuanthanh.homeexercise_week3.pojo.Result;
import com.google.gson.Gson;

public class MovieList extends AppCompatActivity implements MovieInformationAdapter.MovieItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        RecyclerView rvMovieList = findViewById(R.id.rvMovieList);
        rvMovieList.setLayoutManager(new LinearLayoutManager(this));

        Gson gson = new Gson();
        Movie movie = gson.fromJson(MyApp.getMsgMovie(), Movie.class);
        if (movie != null) {
            MovieInformationAdapter adapter = new MovieInformationAdapter(movie.getResults(), this, this);
            rvMovieList.setAdapter(adapter);
        }

    }

    @Override
    public void onMovieClick(Result result) {
        Intent intent = new Intent(this, MovieDetailed.class);
        intent.putExtra("detail", result);
        startActivity(intent);
    }
}
