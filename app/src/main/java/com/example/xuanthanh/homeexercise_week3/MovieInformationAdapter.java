package com.example.xuanthanh.homeexercise_week3;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuanthanh.homeexercise_week3.pojo.Result;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MovieInformationAdapter extends RecyclerView.Adapter<MovieInformationAdapter.ViewHolder> {

    private List<Result> mMovieInformation;
    private Context mContext;
    private static final String HEADER_URL_IMAGE = "https://image.tmdb.org/t/p/w500/";

    private MovieItemClickListener listener;

    public MovieInformationAdapter(List<Result> movieInformation, Context context, MovieItemClickListener listener) {
        mMovieInformation = movieInformation;
        mContext = context;
        this.listener = listener;
    }

    public interface MovieItemClickListener {
        void onMovieClick(Result result);
    }

    @Override
    public MovieInformationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View movieInformationView = inflater.inflate(R.layout.item_movie_information, parent, false);

        ViewHolder viewHolder = new ViewHolder(movieInformationView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieInformationAdapter.ViewHolder viewHolder, int position) {

        final Result movieInformation = mMovieInformation.get(position);

        ImageView poster = viewHolder.poster;
        Picasso.with(mContext).load(HEADER_URL_IMAGE + movieInformation.getPosterPath())
                .fit()
                .centerInside()
                .into(poster);
        TextView title = viewHolder.title;
        title.setText(movieInformation.getTitle());
        TextView overview = viewHolder.overview;
        overview.setText(movieInformation.getOverview());
        viewHolder.play.setVisibility(movieInformation.getVideo() ? View.VISIBLE : View.GONE);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMovieClick(movieInformation);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovieInformation.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView poster;
        public TextView title;
        public TextView overview;
        public ImageView play;

        public ViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            overview = itemView.findViewById(R.id.overview);
            play = itemView.findViewById(R.id.play);
        }
    }
}