package com.haseeb.freshtomatoesapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.loopj.android.http.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import android.widget.AdapterView.OnItemClickListener;
//??
import org.apache.http.Header;

public class FreshTomatoesMain extends ActionBarActivity {

    private FreshTomatoesAPIClient apiClient;
    private ListView listViewMovies;
    private MovieListAdaptor movieListAdaptor;
    private EditText etSearchbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fresh_tomatoes_main_activity);
        InitializeUIElements();
    }

    private void InitializeUIElements()
    {
        listViewMovies = (ListView) findViewById(R.id.lvMovies);
        ArrayList<MovieModel> moviesArray = new ArrayList<MovieModel>();
        movieListAdaptor = new MovieListAdaptor(this, moviesArray);
        listViewMovies.setTextFilterEnabled(true);
        listViewMovies.setAdapter(movieListAdaptor);

        etSearchbox=(EditText)findViewById(R.id.etSearchBox);

        fetchAllMoviesAsync();
        setupMovieSelectedListener();
        setupSearchBoxListener();

    }

    //Needs reformating
    private void fetchAllMoviesAsync() {

        apiClient = new FreshTomatoesAPIClient();
        apiClient.getAllMovies(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int code, Header[] headers, JSONArray items) {
                Log.d("Handler", "JsonHttpResponseHandler called");
                try {
                    // Parse json array into array of model objects
                    ArrayList<MovieModel> movies = MovieModel.fromJson(items);
                    // Load model objects into the adapter which displays them
                    movieListAdaptor.addAll(movies);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void setupSearchBoxListener()
    {
        etSearchbox.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                FreshTomatoesMain.this.movieListAdaptor.getFilter().filter(arg0);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }
        });


    }




    public void setupMovieSelectedListener() {
        listViewMovies.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View item, int position, long rowId) {
                Intent i = new Intent(FreshTomatoesMain.this, MovieDetailActivity.class);
                i.putExtra("movie", movieListAdaptor.getItem(position));
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fresh_tomatoes_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
