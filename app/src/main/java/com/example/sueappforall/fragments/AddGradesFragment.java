package com.example.sueappforall.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sueappforall.R;
import com.example.sueappforall.model.Grade;
import com.example.sueappforall.persistence.DBHelper;
import com.google.android.material.snackbar.Snackbar;

public class AddGradesFragment extends Fragment {

    //Refers to all widgets
    View v;
    EditText edtStudentName, edtProgram, edtMarksCourse1, edtMarksCourse2, edtMarksCourse3, edtMarksCourse4;
    Button btnSubmit;
    LinearLayout lnAddGradesFrag;

    DBHelper dbh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_add_grades, container, false);

        //Instantiates all widgets
        init();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Grade grade = createGrade();
                String message = dbh.insertOrUpdateGrade(grade);

                if(message == null){
                    Snackbar.make(lnAddGradesFrag, "Record added successfully.", Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(lnAddGradesFrag, message, Snackbar.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }

    public Grade createGrade(){
        //Transforms the strings into integers
        String course1String = edtMarksCourse1.getText().toString().trim();
        String course2String = edtMarksCourse2.getText().toString().trim();
        String course3String = edtMarksCourse3.getText().toString().trim();
        String course4String = edtMarksCourse4.getText().toString().trim();

        int marksCourse1 = (course1String == null || course1String.isEmpty()) ? 0 : Integer.parseInt(course1String);
        int marksCourse2 = (course2String == null || course2String.isEmpty()) ? 0 : Integer.parseInt(course2String);
        int marksCourse3 = (course3String == null || course3String.isEmpty()) ? 0 : Integer.parseInt(course3String);
        int marksCourse4 = (course4String == null || course4String.isEmpty()) ? 0 : Integer.parseInt(course4String);

        return new Grade(
                edtStudentName.getText().toString().trim(),
                edtProgram.getText().toString().trim(),
                marksCourse1,
                marksCourse2,
                marksCourse3,
                marksCourse4
        );
    }

    //Instantiates all widgets
    private void init() {
        lnAddGradesFrag = v.findViewById(R.id.lnAddGradesFrag);
        edtStudentName = v.findViewById(R.id.edtStudentName);
        edtProgram = v.findViewById(R.id.edtProgram);
        edtMarksCourse1 = v.findViewById(R.id.edtMarksCourse1);
        edtMarksCourse2 = v.findViewById(R.id.edtMarksCourse2);
        edtMarksCourse3 = v.findViewById(R.id.edtMarksCourse3);
        edtMarksCourse4 = v.findViewById(R.id.edtMarksCourse4);
        btnSubmit = v.findViewById(R.id.btnSubmitGrade);

        //Instantiates the database helper
        dbh = new DBHelper(getActivity());
    }
}