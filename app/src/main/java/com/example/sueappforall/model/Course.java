package com.example.sueappforall.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    public static final String COURSE_1 = "Course 1";
    public static final String COURSE_2 = "Course 2";
    public static final String COURSE_3 = "Course 3";
    public static final String COURSE_4 = "Course 4";

    public static List<String> getArray(){
        List<String> courseArray = new ArrayList<>();

        courseArray.add(COURSE_1);
        courseArray.add(COURSE_2);
        courseArray.add(COURSE_3);
        courseArray.add(COURSE_4);

        return courseArray;
    }
}
