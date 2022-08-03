package com.example.sueappforall.model;

import com.google.android.material.snackbar.Snackbar;

//Class to handle Grades object in the database
public class Grade {
    private int studentId;
    private String studentName;
    private String program;
    private int course1;
    private int course2;
    private int course3;
    private int course4;
    private int totalMarks;

    public Grade(String studentName, String program, int course1, int course2, int course3, int course4) {
        build(0, studentName, program, course1, course2, course3, course4);
    }

    public Grade(int studentId, String studentName, String program, int course1, int course2, int course3, int course4) {
        build(studentId, studentName, program, course1, course2, course3, course4);
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getProgram() {
        return program;
    }

    public int getCourse1() {
        return course1;
    }

    public int getCourse2() {
        return course2;
    }

    public int getCourse3() {
        return course3;
    }

    public int getCourse4() {
        return course4;
    }

    public int getTotalMarks() { return totalMarks; }

    //Sum the grade informed
    public void sumGrade(String course, int grade){

        switch (course){
            case Course.COURSE_1:
                this.course1 += grade;
                break;
            case Course.COURSE_2:
                this.course2 += grade;
                break;
            case Course.COURSE_3:
                this.course3 += grade;
                break;
            case Course.COURSE_4:
                this.course4 += grade;
                break;
            default:
                break;
        }
    }

    public String validate(){
        if((this.studentName == null || this.studentName.isEmpty())){
            return "Student name is mandatory.";
        }

        if(this.program == null || this.program.isEmpty()){
            return "Program is mandatory.";
        }

        if((this.course1 > 100 || this.course1 < 0) ||
            (this.course2 > 100 || this.course2 < 0) ||
            (this.course3 > 100 || this.course3 < 0) ||
            (this.course4 > 100 || this.course4 < 0)){
            return "Marks should be between 0 and 100";
        }

        return null;
    }

    //Build the object in the constructors
    private void build(int studentId, String studentName, String program, int course1, int course2, int course3, int course4){
        this.studentId = studentId;
        this.studentName = studentName;
        this.program = program;
        this.course1 = course1;
        this.course2 = course2;
        this.course3 = course3;
        this.course4 = course4;
        this.totalMarks = course1 + course2 + course3 + course4;
    }
}
