package com.example.mehreenathar.attendancesystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Teacher implements Serializable
{
    private String name;
    private String password;
    private String email;
    private ArrayList<Course>courses;
    public  Teacher()
    {
        name=null;
        password=null;
        email=null;
        courses=null;
    }
    public Teacher(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
    public Course getCourseObj(Course course)
    {
        for(int i=0;i<courses.size();i++)
        {
            Course c=courses.get(i);
            if(c.getCourseName().equals(course.getCourseName()))
            {
                return c;
            }
        }
        return course;
    }
    public void updateCourseObjectAfterAttendanceMark(Course course)//as no pass by reference in java
    {
       for(int i=0;i<courses.size();i++)
       {
           Course c=courses.get(i);
           if(c.getCourseName().equals(course.getCourseName()))
           {
               //insert this object in courses list and remove previous object
               courses.add(course);//new course object added
               courses.remove(i);//old course object removed
               return;
           }
       }
    }
    public Teacher(String name, String password, String email, ArrayList<Course> courses) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
    public void addCourse(Course course)
    {
        if(this.courses!=null)
        {
            this.courses.add(course);
        }
        else
        {
            this.courses=new ArrayList<Course>();
            this.courses.add(course);
        }
    }
}
