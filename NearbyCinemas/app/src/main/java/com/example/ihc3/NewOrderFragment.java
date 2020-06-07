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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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

public class NewOrderFragment extends Fragment {
    int quantity = 1;
    int popcorn = 0;
    int drinks = 0;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        //navigationView.setCheckedItem(R.id.nav_list);

        view = inflater.inflate(R.layout.fragment_order, container, false);

        TextView lblPrice = view.findViewById(R.id.lbl_price);
        lblPrice.setText(Integer.toString(quantity*6) + "€");

        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner_quantity);
        spinner.setSelection(0);
        final Spinner spinner_popcorn = (Spinner) view.findViewById(R.id.spinner_popcorn);
        spinner.setSelection(0);
        final Spinner spinner_drinks = (Spinner) view.findViewById(R.id.spinner_drinks);
        spinner.setSelection(0);

        Button btn = (Button) view.findViewById(R.id.btn_checkout);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                x("spinner", position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        spinner_popcorn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                x("popcorn", position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        spinner_drinks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                x("drinks", position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewOrderFragment.this.getActivity(), "Your order is complete", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("orderComplete", "Viseu");
                startActivity(intent);
            }
        });



        return view;
    }

    public void x(String spinner_name, int p){
        switch (spinner_name){
            case "spinner":
                quantity = p + 1;
                break;
            case "popcorn":
                popcorn = p;
                break;
            case "drinks":
                drinks = p;
                break;
        }

        TextView lblPrice = view.findViewById(R.id.lbl_price);
        lblPrice.setText(Integer.toString(quantity*6 + popcorn*2 + drinks) + "€");
    }
}
