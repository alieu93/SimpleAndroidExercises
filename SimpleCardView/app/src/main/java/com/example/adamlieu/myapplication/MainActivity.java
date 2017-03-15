package com.example.adamlieu.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<UpcomingReleases> releases;
    static View.OnClickListener myOnClickListener;

    private boolean loading = true;
    private int counter = 0;

    int pastVisibleItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //myOnClickListener = new MyOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        releases = new ArrayList<UpcomingReleases>();

        /*UpcomingReleases newRelease = new UpcomingReleases("newGame",
                "March 2017", "test.com", "test.com");*/
        for(counter = 0; counter < 40; counter ++){
            releases.add(new UpcomingReleases("newGame" + counter, "March 2017", "storeURL" + counter, "imgURL"));
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

                        Toast.makeText(MainActivity.this, "Test!", Toast.LENGTH_SHORT).show();
                        releases.add(new UpcomingReleases("newGame" + counter, "March 2017", "storeURL" + counter, "imgURL"));
                        adapter.notifyDataSetChanged();
                        counter++;
                        loading = true;
                    }
                }
            }
        });

    }


}
