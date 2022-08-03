package com.example.sueappforall.model;

//Class to handle Improvement object in the database
public class Improvement {
    private int improvementId;
    private int studentId;
    private String course;
    private int mark;

    public Improvement(int studentId, String course, int mark) {
        this.improvementId = 0;
        this.studentId = studentId;
        this.course = course;
        this.mark = mark;
    }

    public int getImprovementId() {
        return improvementId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCourse() {
        return course;
    }

    public int getMark() {
        return mark;
    }

    public String validate(){
        if(this.mark < 0 || this.mark > 100){
            return "Marks should be between 0 and 100";
        }

        return null;
    }
}
