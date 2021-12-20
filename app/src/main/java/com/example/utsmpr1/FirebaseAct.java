package com.example.utsmpr1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseAct extends AppCompatActivity {

    private DatabaseReference mDatabase;
    EditText name, nim;
    ListView listView;
    List<User> listIndex;
    String selectedID;
    Button tambah, edit, hapus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        listView = findViewById(R.id.list_view);

        name = findViewById(R.id.nameEdit);
        nim = findViewById(R.id.nimEdit);

        tambah = findViewById(R.id.tambahBtn);
        edit = findViewById(R.id.editBtn);
        hapus =  findViewById(R.id.hapusBtn);

        listIndex = new ArrayList<>();

        if(tambah!=null){
            tambah.setVisibility(View.VISIBLE);
        }

        if(edit!=null){
            edit.setVisibility(View.INVISIBLE);
        }

        if(hapus!=null){
            hapus.setVisibility(View.INVISIBLE);
        }

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int
                    position, long id) {
                User user = listIndex.get(position);
                name.setText(user.getName());
                nim.setText(user.getNim());
                selectedID = user.getId();
                tambah.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.VISIBLE);
                hapus.setVisibility(View.VISIBLE);
                return true;
            }
        });
        GetData();
    }

    private void GetData(){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listIndex.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    listIndex.add(user);
                }
                ListAdapter listAdapter = new ListAdapter(FirebaseAct.this, listIndex);
                listView.setAdapter(listAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        mDatabase.child("users").addValueEventListener(valueEventListener);
    }

    public void tambahuser(View view) {
        String name = this.name.getText().toString();
        String nim = this.nim.getText().toString();

        String id = String.valueOf(System.currentTimeMillis());
        User user = new User(id,name,nim);

        mDatabase.child("users").child(id).setValue(user);
    }

    public void Edit(View view) {
        DatabaseReference reference = mDatabase.child("users").child(selectedID);
        User user = new User(selectedID,name.getText().toString(),nim.getText().toString());
        reference.setValue(user);

        selectedID = "";
        name.setText("");
        nim.setText("");
        tambah.setVisibility(View.VISIBLE);
        edit.setVisibility(View.INVISIBLE);
        hapus.setVisibility(View.INVISIBLE);
    }

    public void Hapus(View view) {
        DatabaseReference reference = mDatabase.child("users").child(selectedID);
        reference.removeValue();
        selectedID = "";
        name.setText("");
        nim.setText("");
        tambah.setVisibility(View.VISIBLE);
        edit.setVisibility(View.INVISIBLE);

        hapus.setVisibility(View.INVISIBLE);
    }
}