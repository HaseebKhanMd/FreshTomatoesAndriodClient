package com.haseeb.freshtomatoesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haseeb on 7/19/2015.
 */
public class MovieListAdaptor extends ArrayAdapter<MovieModel> implements Filterable {

    private Filter filter;
    private ArrayList<MovieModel> movieList;

    public MovieListAdaptor(Context context, ArrayList<MovieModel> moviesArray) {
        super(context, 0, moviesArray);
        this.movieList = moviesArray;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public MovieModel getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movieList.get(position).getId();
    }

    @Override
    public Filter getFilter() {
        if (filter == null)
            filter = new AppFilter<MovieModel>(movieList);
        return filter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        MovieModel movie = getItem(position);

        if (convertView == null) {  // Check if an existing view is being reused, otherwise inflate the view
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.fresh_tomatoes_item, null);
        }
        // Lookup view for data population
        TextView tvMovieName = (TextView) convertView.findViewById(R.id.tvMovieName);
        TextView tvRating = (TextView) convertView.findViewById(R.id.tvRating);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
        ImageView ivImageThumnail = (ImageView) convertView.findViewById(R.id.ivImageThumnail);
        // Populate the data into the template view using the data object
        tvMovieName.setText(movie.getName());
        tvRating.setText("Rating: " + movie.getRating());
        tvDescription.setText(movie.getDescription());
                Picasso.with(getContext()).load(movie.getURL()).into(ivImageThumnail);
        // Return the completed view to render on screen
        return convertView;
    }

    private class AppFilter<T> extends Filter {

        private ArrayList<T> sourceObjects;

        public AppFilter(ArrayList<T> objects) {
            sourceObjects = new ArrayList<T>();
            synchronized (this) {
                sourceObjects.addAll(objects);
            }
        }

        @Override
        protected FilterResults performFiltering(CharSequence chars) {
            String filterSeq = chars.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if (filterSeq != null && filterSeq.length() > 0) {
                ArrayList<T> filter = new ArrayList<T>();

                for (T movie : sourceObjects) {
                    // the filtering itself:
                    String searchColumn = ((MovieModel)movie).getName();
                    if (searchColumn.toLowerCase().contains(filterSeq))
                        filter.add(movie);
                }
                result.count = filter.size();
                result.values = filter;
            } else {
                // add all objects
                synchronized (this) {
                    result.values = sourceObjects;
                    result.count = sourceObjects.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            // NOTE: this function is *always* called from the UI thread.
            ArrayList<MovieModel> filtered = (ArrayList<MovieModel>) results.values;
            notifyDataSetChanged();
            clear();
            for (int i = 0, l = filtered.size(); i < l; i++)
                add((MovieModel) filtered.get(i));
            notifyDataSetInvalidated();
        }
    }

}



