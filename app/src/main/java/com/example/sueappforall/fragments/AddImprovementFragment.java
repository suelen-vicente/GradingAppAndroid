package com.example.sueappforall.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.sueappforall.R;
import com.example.sueappforall.model.Course;
import com.example.sueappforall.model.Grade;
import com.example.sueappforall.model.Improvement;
import com.example.sueappforall.persistence.DBHelper;
import com.google.android.material.snackbar.Snackbar;

public class AddImprovementFragment extends Fragment {

    LinearLayout lnImprovementFrag;
    View v;
    EditText edtStudentId, edtImprovementMark;
    Spinner spnCourse;
    Button btnSubmit;

    String course = Course.COURSE_1;

    DBHelper dbh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_add_improvement, container, false);

        //Instantiates all widgets
        init();

        //Listener for spinner that will manipulate selected items
        spnCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                course = spnCourse.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create the improvement object using the values inserted in the screen
                Improvement improvement = createImprovement();

                //Retrieves the grade from database of this student
                Grade grade = dbh.getGrade(improvement.getStudentId());

                if(grade == null){
                    Snackbar.make(lnImprovementFrag, "Student not found.", Snackbar.LENGTH_LONG).show();
                    return;
                }

                //Sum the grade
                grade.sumGrade(improvement.getCourse(), improvement.getMark());

                //Updates the grade
                String message = dbh.insertOrUpdateGrade(grade);

                //Verify if the grade was updated correctly
                if (message != null) {
                    Snackbar.make(lnImprovementFrag, message, Snackbar.LENGTH_LONG).show();
                    return;
                }

                //Insert the improvement if the grade was update ok
                message = dbh.insertOrUpdateImprovement(improvement);

                //Verify if the improvement was updated correctly
                if (message == null) {
                    Snackbar.make(lnImprovementFrag, "Record added successfully.", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(lnImprovementFrag, message, Snackbar.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }

    //Create improvement object
    private Improvement createImprovement(){
        //Transforms the strings into integers
        String studentIdString = edtStudentId.getText().toString().trim();
        String marksString = edtImprovementMark.getText().toString().trim();

        int marks = (marksString == null || marksString.isEmpty()) ? 0 : Integer.parseInt(marksString);
        int studentId = (studentIdString == null || studentIdString.isEmpty()) ? 0 : Integer.parseInt(studentIdString);

        return new Improvement(
                studentId,
                course,
                marks
        );
    }

    //Instantiates all widgets
    private void init() {
        lnImprovementFrag = (LinearLayout) v.findViewById(R.id.lnImprovementFrag);
        edtStudentId = (EditText) v.findViewById(R.id.edtStudentIdImprovement);
        edtImprovementMark = (EditText) v.findViewById(R.id.edtImprovementMark);
        spnCourse = (Spinner) v.findViewById(R.id.spnCourse);
        btnSubmit = (Button) v.findViewById(R.id.btnSubmitImprovement);

        //Instantiates the database helper
        dbh = new DBHelper(getActivity());

        //Defining the array adapter that will serve as data source to spinner widget
        ArrayAdapter<String> adapterSpace = new ArrayAdapter<>(v.getContext(),
                android.R.layout.simple_spinner_item,
                Course.getArray());

        //Specify the layout to use when the list of choices appears
        adapterSpace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Apply the adapter to the spinner
        spnCourse.setAdapter(adapterSpace);
    }
}