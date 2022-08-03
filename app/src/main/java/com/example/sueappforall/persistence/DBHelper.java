package com.example.sueappforall.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sueappforall.model.Grade;
import com.example.sueappforall.model.Improvement;

public class DBHelper extends SQLiteOpenHelper {

    //Databse name
    static final String DBNAME = "School.db";
    static final int VERSION = 1;

    //Grades Table
    //StudentId, StudentName, Program, Course1, Course2, Course3, Course4
    static final String GRADES_TABLE_NAME = "Grades";
    static final String GRADES_COL1 = "studentId";
    static final String GRADES_COL2 = "studentName";
    static final String GRADES_COL3 = "program";
    static final String GRADES_COL4 = "course1";
    static final String GRADES_COL5 = "course2";
    static final String GRADES_COL6 = "course3";
    static final String GRADES_COL7 = "course4";

    //Improvement Table
    //ImprovementId, StudentId, Course, and Marks
    static final String IMPROV_TABLE_NAME = "Improvement";
    static final String IMPROV_COL1 = "improvementId";
    static final String IMPROV_COL2 = "studentId";
    static final String IMPROV_COL3 = "course";
    static final String IMPROV_COL4 = "mark";

    //Create Grades Table
    static final String CREATE_GRADES_TABLE = " CREATE TABLE " + GRADES_TABLE_NAME
            + " ( "
            + GRADES_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + GRADES_COL2 + " TEXT NOT NULL, "
            + GRADES_COL3 + " TEXT NOT NULL, "
            + GRADES_COL4 + " INTEGER NOT NULL, "
            + GRADES_COL5 + " INTEGER NOT NULL, "
            + GRADES_COL6 + " INTEGER NOT NULL, "
            + GRADES_COL7 + " INTEGER NOT NULL "
            + " ); ";

    //Create Improvement Table
    static final String CREATE_IMPROVEMENT_TABLE = " CREATE TABLE " + IMPROV_TABLE_NAME
            + " ( "
            + IMPROV_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + IMPROV_COL2 + " INTEGER NOT NULL, "
            + IMPROV_COL3 + " TEXT NOT NULL, "
            + IMPROV_COL4 + " INTEGER NOT NULL "
            + " ); ";

    //Drop tables
    static final String DROP_GRADES_TABLE = "DROP TABLE IF EXISTS " + GRADES_TABLE_NAME;
    static final String DROP_IMPROVEMENT_TABLE = "DROP TABLE IF EXISTS " + IMPROV_TABLE_NAME;

    //Constructor
    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GRADES_TABLE);
        db.execSQL(CREATE_IMPROVEMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_GRADES_TABLE);
        db.execSQL(DROP_IMPROVEMENT_TABLE);
        onCreate(db);
    }

    //Insert Grade
    public String insertOrUpdateGrade(Grade grade){
        String message = grade.validate();

        if(message != null){
            return message;
        }

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(GRADES_COL2, grade.getStudentName());
        cv.put(GRADES_COL3, grade.getProgram());
        cv.put(GRADES_COL4, grade.getCourse1());
        cv.put(GRADES_COL5, grade.getCourse2());
        cv.put(GRADES_COL6, grade.getCourse3());
        cv.put(GRADES_COL7, grade.getCourse4());

        long result = -1;

        if(grade.getStudentId() != 0){
            result = db.update(GRADES_TABLE_NAME, cv,
                    GRADES_COL1 + " = ?",
                    new String[]{String.valueOf(grade.getStudentId())});
        }else{
            result = db.insert(GRADES_TABLE_NAME, null, cv);
        }

        return (result == -1 ? "Record could not be added." : null);

    }

    //Insert Improvement
    public String insertOrUpdateImprovement(Improvement improvement){
        String message = improvement.validate();

        if(message != null){
            return message;
        }

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(IMPROV_COL2, improvement.getStudentId());
        cv.put(IMPROV_COL3, improvement.getCourse());
        cv.put(IMPROV_COL4, improvement.getMark());

        long result = -1;

        if(improvement.getImprovementId() != 0){
            result = db.update(IMPROV_TABLE_NAME, cv,
                    IMPROV_COL1 + " = ?",
                    new String[]{String.valueOf(improvement.getImprovementId())});
        }else{
            result = db.insert(IMPROV_TABLE_NAME, null, cv);
        }

        return (result == -1 ? "Record could not be added." : null);

    }

    //List All Students
    public Cursor listStudents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM " + GRADES_TABLE_NAME, null);

        //If data was found, return it.
        if(cursor.moveToFirst()){
            return cursor;
        }

        return null;
    }

    //Retrieve grade record by student id
    public Grade getGrade(Integer studentId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + GRADES_TABLE_NAME +
                " WHERE studentId = ?",
                new String[]{String.valueOf(studentId)});

        //If data was found, return it.
        if(cursor.moveToFirst()){
            Grade grade = new Grade(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6)
            );
            cursor.close();
            db.close();

            return grade;
        }

        return null;
    }
}
