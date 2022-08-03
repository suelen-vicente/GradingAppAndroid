package com.example.sueappforall.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sueappforall.R;
import com.example.sueappforall.adapters.ListAdapter;
import com.example.sueappforall.model.Grade;
import com.example.sueappforall.persistence.DBHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ListStudentsFragment extends Fragment {
    View v;
    RecyclerView rcStudents;
    LinearLayout lnListStudentsFrag;

    List<Grade> studentList = new ArrayList<>();
    DBHelper dbh;
    ListAdapter adapter;

    public ListStudentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_list_students, container, false);

        init();

        //Get all student from the database
        Cursor cursor = dbh.listStudents();

        //Protect for no record found
        if(cursor == null){
            Snackbar.make(lnListStudentsFrag, "No student record found.", Snackbar.LENGTH_LONG).show();
        }else{
            cursor.moveToFirst();

            //Bind the object for each student on the database
            do{
                Grade studentGrade = new Grade(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getInt(6)
                );

                //Adds the student to the list
                studentList.add(studentGrade);

            }while (cursor.moveToNext());
            //Close the conections
            cursor.close();
            dbh.close();

            //bind the adapter
            bindAdapter();
        }

        return v;
    }

    public void bindAdapter(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcStudents.setLayoutManager(layoutManager);

        adapter = new ListAdapter(studentList, getContext());
        rcStudents.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    private void init() {
        //Instantiates the widgets
        rcStudents = v.findViewById(R.id.rcListStudents);
        lnListStudentsFrag = v.findViewById(R.id.lnListStudentsFrag);

        //Instantiates the database
        dbh = new DBHelper(getActivity());
    }
}