package com.example.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.widget.SearchView;

import com.example.search.adapter.AdapterBusiness;
import com.example.search.pojo.business;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<business> list;
    RecyclerView rv;
    SearchView searchView;
    AdapterBusiness adapter;

    LinearLayoutManager lm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Business");
        ref = FirebaseDatabase.getInstance().getReference().child("Business");
        rv = findViewById(R.id.rv);
        searchView = findViewById(R.id.search_bar);
        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new AdapterBusiness(list);
        rv.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        business bs = snapshot.getValue(business.class);
                        list.add(bs);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value");
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s ) {
                lookup(s);
                return true;
            }
        });
    }

    private void lookup(String s) {
        ArrayList<business> myList = new ArrayList<>();
        for (business obj : list){
            if(obj.getName().toLowerCase().contains(s.toLowerCase())){
                myList.add(obj);
            }
        }
        AdapterBusiness adapter = new AdapterBusiness(myList);
        rv.setAdapter(adapter);
    }
}
