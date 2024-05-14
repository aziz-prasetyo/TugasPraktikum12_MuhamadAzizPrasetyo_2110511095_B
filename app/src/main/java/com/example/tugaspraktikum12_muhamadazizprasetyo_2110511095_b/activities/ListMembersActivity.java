package com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.R;
import com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.adapter.MemberAdapter;
import com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.db.DBHelper;
import com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.model.Member;

import java.util.ArrayList;

public class ListMembersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MemberAdapter adapter;
    private ArrayList<Member> membersArrayList;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_members);

        recyclerView = findViewById(R.id.rview);
        recyclerView.setHasFixedSize(true);

        adapter = new MemberAdapter(this);
        dbHelper = new DBHelper(this);
        membersArrayList = dbHelper.getAllUsers();
        adapter.setListMembers(membersArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListMembersActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        membersArrayList = dbHelper.getAllUsers();
        adapter.setListMembers(membersArrayList);
        adapter.notifyDataSetChanged();
    }
}