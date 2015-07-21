package com.haseeb.freshtomatoesapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Haseeb on 7/19/2015.
 */

public class MovieDetailActivity extends Activity {

    private TextView tvMovieName ;
    private TextView tvSynopsis;
    private TextView tvRating;
    private ImageView ivImageThumnail;
    private static final String MOVIE_DETAIL_KEY = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fresh_tomatoes_moviedetails_activity);
        // Fetch views
        ivImageThumnail = (ImageView) findViewById(R.id.ivImageThumnail);
        tvMovieName = (TextView) findViewById(R.id.tvMovieName);
        tvSynopsis = (TextView) findViewById(R.id.tvSynopsis);
        tvRating = (TextView) findViewById(R.id.tvRating);
        // Load movie data
        MovieModel movie = (MovieModel) getIntent().getSerializableExtra(MOVIE_DETAIL_KEY);
        loadMovie(movie);
    }

    // Populate the data for the movie
    @SuppressLint("NewApi")
    public void loadMovie(MovieModel movie) {
       /*
        if (android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setTitle(movie.getName());
        }
        */
        // Populate data
        tvMovieName.setText(movie.getName());
        tvRating.setText(Html.fromHtml("<b>Rating:</b> " + movie.getRating() ));
        tvSynopsis.setText(Html.fromHtml("<b>Synopsis:</b> " + movie.getDescription()));
        // R.drawable.large_movie_poster from
        // http://content8.flixster.com/movie/11/15/86/11158674_pro.jpg -->
        Picasso.with(this).load(movie.getURL()).
                placeholder(R.drawable.large_movie_poster).
                into(ivImageThumnail);
    }

}