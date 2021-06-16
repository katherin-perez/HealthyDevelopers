package com.example.healthydevelopers.model;

public class Progress {
    private int id, habitid, status;
    private String usermail;

    public Progress(int id, int habitid, int status, String usermail) {
        this.id = id;
        this.habitid = habitid;
        this.status = status;
        this.usermail = usermail;
    }

    public Progress(int habitid, int status, String usermail) {
        this.habitid = habitid;
        this.status = status;
        this.usermail = usermail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHabitId() {
        return habitid;
    }

    public void setHabitId(int habitId) {
        this.habitid = habitId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }
}
