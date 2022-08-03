package com.example.sueappforall.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueappforall.R;
import com.example.sueappforall.model.Grade;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView studentId, studentName, program,
                marksCourse1, marksCourse2, marksCourse3, marksCourse4, totalMarks;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Instantiates all widgets
            studentId = (TextView) itemView.findViewById(R.id.txtStudentIdRecord);
            studentName = (TextView) itemView.findViewById(R.id.txtStudentNameRecord);
            program = (TextView) itemView.findViewById(R.id.txtProgramRecord);
            marksCourse1 = (TextView) itemView.findViewById(R.id.txtMarkCourse1Record);
            marksCourse2 = (TextView) itemView.findViewById(R.id.txtMarkCourse2Record);
            marksCourse3 = (TextView) itemView.findViewById(R.id.txtMarkCourse3Record);
            marksCourse4 = (TextView) itemView.findViewById(R.id.txtMarkCourse4Record);
            totalMarks = (TextView) itemView.findViewById(R.id.txtTotalMarksRecord);

        }
    }

    //Create list of students
    private final List<Grade> studentList;

    public ListAdapter(List<Grade> gradeList, Context context) {
        super();
        //Add the students to the internal list
        this.studentList = gradeList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_record_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Bind the values in the object to the widgets
        Grade studentGrade = studentList.get(position);
        ((ViewHolder) holder).studentId.setText(String.valueOf(studentGrade.getStudentId()));
        ((ViewHolder) holder).studentName.setText(studentGrade.getStudentName());
        ((ViewHolder) holder).program.setText(studentGrade.getProgram());
        ((ViewHolder) holder).marksCourse1.setText(String.valueOf(studentGrade.getCourse1()));
        ((ViewHolder) holder).marksCourse2.setText(String.valueOf(studentGrade.getCourse2()));
        ((ViewHolder) holder).marksCourse3.setText(String.valueOf(studentGrade.getCourse3()));
        ((ViewHolder) holder).marksCourse4.setText(String.valueOf(studentGrade.getCourse4()));
        ((ViewHolder) holder).totalMarks.setText(String.valueOf(studentGrade.getTotalMarks()));
    }

    //Return the total of students
    @Override
    public int getItemCount() {
        return studentList.size();
    }
}
