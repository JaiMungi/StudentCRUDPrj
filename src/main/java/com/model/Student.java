package com.model;


public class Student {

    private int id;
    private String name;
    private String branch;
    private String semester;
    private String gender;
    private String hobby; // stored as comma-separated values, e.g. "Reading, Dancing"

    public Student() {
    }

    public Student(int id, String name, String branch, String semester, String gender, String hobby) {
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.semester = semester;
        this.gender = gender;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
