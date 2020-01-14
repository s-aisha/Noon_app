package as.com.noonlibrary;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;



public class LibraryBooks extends Fragment {


    NamesStore namesStore= new NamesStore();
    private int instance = 0;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Store> al;
    private SharedPreferences prefs;
    private DatabaseHelper db;
    SQLiteOpenHelper sql;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.librarybooks,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prefs = getActivity().getSharedPreferences(namesStore.getNameInstance(), Context.MODE_PRIVATE);
        instance = prefs.getInt("instance", 0);
        initialiser(view);

        if(instance == 0){dataSource();}
        dataSource1();
    }

    private void dataSource1() {


        al = db.getAllNotes();
        Adapter adapter = new Adapter(getActivity(),al);
        recyclerView.setAdapter(adapter);
    }


    private void dataSource() {


        SharedPreferences.Editor editor = getActivity().getSharedPreferences(namesStore.getNameInstance(), Context.MODE_PRIVATE).edit();
        editor.putInt("instance", 1);
        editor.apply();



        db.insertBook(namesStore.getBid1(),R.drawable.c,namesStore.getBook1(),10,"Grade I");
        db.insertBook(namesStore.getBid2(),R.drawable.arduino,namesStore.getBook2(),10,"Grade II");
        db.insertBook(namesStore.getBid3(),R.drawable.cpp,namesStore.getBook3(),10,"Grade III");
        db.insertBook(namesStore.getBid4(),R.drawable.ds,namesStore.getBook4(),10,"Grade IV");
        db.insertBook(namesStore.getBid5(),R.drawable.java,namesStore.getBook5(),10,"Grade V");
        db.insertBook(namesStore.getBid6(),R.drawable.os,namesStore.getBook6(),10,"Grade VI");
        db.insertBook(namesStore.getBid7(),R.drawable.python,namesStore.getBook7(),10,"Grade VII");
        db.insertBook(namesStore.getBid8(),R.drawable.algo,namesStore.getBook8(),10,"Grade VIII");





    }

    private void initialiser(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,true);
        recyclerView.setLayoutManager(layoutManager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        al = new ArrayList<Store>();
        db = new DatabaseHelper(getActivity());


    }
}
