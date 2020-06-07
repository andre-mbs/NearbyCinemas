package com.example.ihc3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.amigold.fundapter.interfaces.DynamicImageLoader;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MoviesListFragment extends Fragment {
    private static int flag = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_list);

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        final ArrayList<Movie> list = new ArrayList<>();

        list.add(new Movie("Donnie Darko",
                "https://m.media-amazon.com/images/M/MV5BZjZlZDlkYTktMmU1My00ZDBiLWFlNjEtYTBhNjVhOTM4ZjJjXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg",
                "donnie",
                "Drama, SCI-FI, Thriller",
                113,
                5,
                8.1));
        list.add(new Movie("Fight Club",
                "https://m.media-amazon.com/images/M/MV5BMjJmYTNkNmItYjYyZC00MGUxLWJhNWMtZDY4Nzc1MDAwMzU5XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UX182_CR0,0,182,268_AL_.jpg",
                "fight",
                "Drama",
                139,
                11,
                8.8));
        list.add(new Movie("Forest Gump",
                "https://m.media-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UY268_CR1,0,182,268_AL_.jpg",
                "forest",
                "Drama, Romance",
                142,
                7,
                8.8));
        list.add(new Movie("Inception",
                "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_UX182_CR0,0,182,268_AL_.jpg",
                "inception",
                "SCI-FI",
                148,
                4,
                8.8));
        list.add(new Movie("Matrix",
                "https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg",
                "matrix",
                "Action, SCI-FI",
                136,
                3,
                8.9));
        list.add(new Movie("Pulp Fiction",
                "https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UY268_CR1,0,182,268_AL_.jpg",
                "pulp",
                "Crime, Drama",
                154,
                10,
                8.9));

        BindDictionary<Movie> dictionary = new BindDictionary<>();
        dictionary.addStringField(R.id.txt_title, new StringExtractor<Movie>() {
            @Override
            public String getStringValue(Movie movie, int position) {
                return movie.getName();
            }
        });
        dictionary.addStringField(R.id.txt_genre, new StringExtractor<Movie>() {
            @Override
            public String getStringValue(Movie movie, int position) {
                return movie.getGenre();
            }
        });
        dictionary.addStringField(R.id.txt_duration, new StringExtractor<Movie>() {
            @Override
            public String getStringValue(Movie movie, int position) {
                return Integer.toString(movie.getDuration()) + " min";
            }
        });
        dictionary.addStringField(R.id.txt_nSessions, new StringExtractor<Movie>() {
            @Override
            public String getStringValue(Movie movie, int position) {
                return Integer.toString(movie.getnSessions()) + " sessions";
            }
        });
        dictionary.addStringField(R.id.txt_rating, new StringExtractor<Movie>() {
            @Override
            public String getStringValue(Movie movie, int position) {
                return "IMDB: " + Double.toString(movie.getRating()) + "/10";
            }
        });

        dictionary.addDynamicImageField(R.id.img_poster, new StringExtractor<Movie>() {
            @Override
            public String getStringValue(Movie movie, int position) {
                return movie.getImgUrl();
            }
        }, new DynamicImageLoader() {
            @Override
            public void loadImage(String url, ImageView view) {
                // UNIVERSAL IMAGE LOADER SETUP
                DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                        .cacheOnDisc(true).cacheInMemory(true)
                        .imageScaleType(ImageScaleType.EXACTLY)
                        .displayer(new FadeInBitmapDisplayer(300)).build();

                ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                        MoviesListFragment.this.getActivity())
                        .defaultDisplayImageOptions(defaultOptions)
                        .memoryCache(new WeakMemoryCache())
                        .discCacheSize(100 * 1024 * 1024).build();

                ImageLoader.getInstance().init(config);
                // END - UNIVERSAL IMAGE LOADER SETUP

                int defaultImage = MoviesListFragment.this.getActivity().getResources().getIdentifier("@drawable/image_failed", null, MoviesListFragment.this.getActivity().getPackageName());
                ImageLoader imageLoader = ImageLoader.getInstance();
                DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                        .cacheOnDisc(true).resetViewBeforeLoading(true)
                        .showImageForEmptyUri(defaultImage)
                        .showImageOnFail(defaultImage)
                        .showImageOnLoading(defaultImage).build();

                ImageView imageView = (ImageView) view.findViewById(R.id.img_poster);
                imageLoader.displayImage(url, imageView, options);
            }
        });

        FunDapter dapter = new FunDapter(MoviesListFragment.this.getActivity(), list, R.layout.list_layout, dictionary);;
        if(flag % 2 == 0){
            dapter = new FunDapter(MoviesListFragment.this.getActivity(), list, R.layout.list_layout2, dictionary);
        }

        ListView listView = (ListView) view.findViewById(R.id.listMovies);
        listView.setAdapter(dapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie selectedItem = list.get(position);
                Toast.makeText(MoviesListFragment.this.getActivity(), selectedItem.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MovieActivity.class);
                intent.putExtra("getMovie", selectedItem.getName());
                startActivity(intent);
            }
        });


        flag++;
        return view;
    }
}
