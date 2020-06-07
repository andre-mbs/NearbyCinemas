package com.example.ihc3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AccountFragment extends Fragment {
    final ArrayList<Theater> list = new ArrayList<>();
    public View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        //navigationView.setCheckedItem(R.id.nav_list);

        view = inflater.inflate(R.layout.fragment_account, container, false);

        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.btnAdd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTheaterActivity.class);
                //startActivityForResult(Intent.createChooser(intent, "Add Theater"), 1);
                startActivity(intent);


            }
        });

        return view;
    }

    @Override
    public void onActivityResult(
            int aRequestCode, int aResultCode, Intent aData
    ) {
        switch (aRequestCode) {
            case 1:
                try {
                    JSONObject j = new JSONObject(aData.getStringExtra("data"));
                    System.out.println(j.getString("phone").getClass());

                    Theater t = new Theater(j.getString("name"), j.getString("city"), j.getString("addr"), 7);
                    list.add(t);

                    BindDictionary<Theater> dictionary = new BindDictionary<>();

                    dictionary.addStringField(R.id.txt_name, new StringExtractor<Theater>() {
                        @Override
                        public String getStringValue(Theater theater, int position) {
                            return theater.getName();
                        }
                    });
                    dictionary.addStringField(R.id.txt_city, new StringExtractor<Theater>() {
                        @Override
                        public String getStringValue(Theater theater, int position) {
                            return theater.getCity();
                        }
                    });
                /*dictionary.addStringField(R.id.txt_phone, new StringExtractor<Theater>() {
                    @Override
                    public String getStringValue(Theater theater, int position) {
                        return Integer.toString(theater.getPhone());
                    }
                });
*/
                    FunDapter dapter = new FunDapter(AccountFragment.this.getActivity(), list, R.layout.theater_layout, dictionary);

                    ListView listView = (ListView) view.findViewById(R.id.listTheaters);
                    listView.setAdapter(dapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Theater selectedItem = list.get(position);
                            Toast.makeText(AccountFragment.this.getActivity(), selectedItem.getName(), Toast.LENGTH_SHORT).show();
                            //Intent intent = new Intent(getActivity(), MovieActivity.class);
                            //intent.putExtra("getMovie", selectedItem.getName());
                            //startActivity(intent);
                        }
                    });

                    LinearLayout theater_layout = (LinearLayout )view.findViewById(R.id.linear_theater);
                    theater_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(AccountFragment.this.getActivity(), "Theater1", Toast.LENGTH_SHORT).show();
                            //Intent picture_intent = new Intent(CurrentActivity.this,PictureActivity.class);
                            //startActivity(picture_intent );
                        }
                    });

                    //list.add(new Theater(j.getString("name"), j.getString("city"), j.getString("addr"), Integer.parseInt(j.getString("phone"))));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                break;
            case 0:

                break;
        }
        super.onActivityResult(aRequestCode, aResultCode, aData);
    }
}
