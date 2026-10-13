package com.example.attendanceregister;

public class Student {
    public static final int UNMARKED = 0;
    public static final int PRESENT = 1;
    public static final int ABSENT = 2;

    private final String name;
    private int status = UNMARKED;

    public Student(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}