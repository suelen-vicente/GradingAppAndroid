package com.example.sueappforall.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sueappforall.R;
import com.example.sueappforall.model.Grade;
import com.example.sueappforall.persistence.DBHelper;
import com.google.android.material.snackbar.Snackbar;

public class SearchGradeFragment extends Fragment {

    View v;
    LinearLayout lnSearchGradeFrag;
    TextView edtStudentId, txtStudentName, txtProgram, txtMarkCourse1, txtMarkCourse2, txtMarkCourse3, txtMarkCourse4, txtTotalMark;
    Button btnSubmit;

    DBHelper dbh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_search_grade, container, false);

        init();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Instantiates the database
                dbh = new DBHelper(getActivity());

                //Get all student from the database
                String studentIdString = edtStudentId.getText().toString().trim();
                int studentId = (studentIdString == null || studentIdString.isEmpty()) ? 0 : Integer.parseInt(studentIdString);
                Grade grade = dbh.getGrade(studentId);

                if(grade == null){
                    Snackbar.make(lnSearchGradeFrag, "No record found.", Snackbar.LENGTH_LONG).show();
                    return;
                }else{
                    //Populate the widgets
                    txtStudentName.setText(grade.getStudentName());
                    txtProgram.setText(grade.getProgram());
                    txtMarkCourse1.setText(String.valueOf(grade.getCourse1()));
                    txtMarkCourse2.setText(String.valueOf(grade.getCourse2()));
                    txtMarkCourse3.setText(String.valueOf(grade.getCourse3()));
                    txtMarkCourse4.setText(String.valueOf(grade.getCourse4()));
                    txtTotalMark.setText(String.valueOf(grade.getTotalMarks()));
                }
            }
        });

        return v;
    }

    private void init() {
        //Instantiates the widgets
        lnSearchGradeFrag = (LinearLayout) v.findViewById(R.id.lnSearchGradeFrag);
        edtStudentId = (EditText) v.findViewById(R.id.edtStudentIdSearchGrade);
        txtStudentName = (TextView) v.findViewById(R.id.txtStudentNameSearch);
        txtProgram = (TextView) v.findViewById(R.id.txtProgramSearch);
        txtMarkCourse1 = (TextView) v.findViewById(R.id.txtMarkCourse1Search);
        txtMarkCourse2 = (TextView) v.findViewById(R.id.txtMarkCourse2Search);
        txtMarkCourse3 = (TextView) v.findViewById(R.id.txtMarkCourse3Search);
        txtMarkCourse4 = (TextView) v.findViewById(R.id.txtMarkCourse4Search);
        txtTotalMark = (TextView) v.findViewById(R.id.txtTotalMarkSearch);
        btnSubmit = (Button) v.findViewById(R.id.btnSubmitSearch);

        //Instantiates the database
        dbh = new DBHelper(getActivity());
    }
}