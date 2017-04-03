package com.example.adamlieu.swipetabssimple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Adam Lieu on 4/3/2017.
 */

public class FragmentTwo extends Fragment {
    private static RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<UpcomingReleases> releases;
    static View.OnClickListener myOnClickListener;

    private boolean loading = true;
    private int counter = 0;

    int pastVisibleItems, visibleItemCount, totalItemCount;

    public FragmentTwo(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        return view;
        //return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        releases = new ArrayList<UpcomingReleases>();

        /*UpcomingReleases newRelease = new UpcomingReleases("newGame",
                "March 2017", "test.com", "test.com");*/
        for(counter = 0; counter < 10; counter ++){
            releases.add(new UpcomingReleases("oldGame" + counter, "April 2017", "storeURL" + counter, "imgURL"));
        }
        adapter = new CustomAdapter(releases);
        recyclerView.setAdapter(adapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView rv, int dx, int dy){
                if(dy > 0){
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if((visibleItemCount + pastVisibleItems) >= totalItemCount){
                        loading = false;

                        //Toast.makeText(FragmentOne.this, "Test!", Toast.LENGTH_SHORT).show();
                        releases.add(new UpcomingReleases("oldGame" + counter, "April 2017", "storeURL" + counter, "imgURL"));
                        recyclerView.post(new Runnable() {
                            public void run(){
                                adapter.notifyDataSetChanged();
                            }
                        });
                        counter++;
                        loading = true;
                    }
                }
            }
        });
    }
}
