package com.example.mehreenathar.attendancesystem;

import java.io.Serializable;
import java.util.ArrayList;

//to auto generate constructor and getter setters "right click class Name->click "generate" and click whatever you wannna generate
public class Course implements Serializable {
    private String courseName;
    private String courseCode;
    private int seats;
    private int totalClasses;//by default total classes are 20...if 5 absents automatically send sms...rough idea
    private ArrayList<Student>enrolledStudents;
    public void sendSmsToShortAttendanceStudents()
    {
        //write send sms code here
    }

    public ArrayList<String> get_shortAttendance()
    {
        ArrayList<String> shortAttendanceList=new ArrayList<>();
        for(int i=0;i<enrolledStudents.size();i++)
        {
            enrolledStudents.get(i).attendancePercentage();
            if(enrolledStudents.get(i).getAttendacePercentage()<=60)
            {
                // shortAttendanceList.add(enrolledStudents.get(i));
                shortAttendanceList.add(enrolledStudents.get(i).getContactNo());
            }
        }
        return shortAttendanceList;
    }
    //less than 60 all short attendees

    public ArrayList<Student> shortAttendance()
    {
        ArrayList<Student> shortAttendanceList=new ArrayList<>();
        for(int i=0;i<enrolledStudents.size();i++)
        {
            enrolledStudents.get(i).attendancePercentage();
            if(enrolledStudents.get(i).getAttendacePercentage()<=60)
            {
                shortAttendanceList.add(enrolledStudents.get(i));
            }
        }
        return shortAttendanceList;
    }
    public Course(String courseName, String courseCode, int seats, ArrayList<Student> enrolledStudents) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.seats = seats;
        this.enrolledStudents = enrolledStudents;
    }

    //initially hen course offered no students may be there so a constructor without students needed too
    public Course(String courseName, String courseCode, int seats) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.seats = seats;
        this.totalClasses=20;  //default classes that a course will have

    }
    //when a new student enrolls in course add him/her to that course's arraylist
    public boolean enrollInCourse(Student s)
    {
        if(enrolledStudents==null)//means we first gotta instantiate the array
        {
            enrolledStudents=new ArrayList<Student>();
            enrolledStudents.add(s);
            return true;
        }
        else
        {
            //check if student already registered in that course
            //considering contact number unique for now
            for (int i=0;i<enrolledStudents.size();i++)
            {

               if(enrolledStudents.get(i).getContactNo().equals(s.getContactNo()))
               {
                   return false;        //student already registered in that course
               }
            }
            enrolledStudents.add(s);
            return true;

        }
    }
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(ArrayList<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    @Override
    public String toString() {
        return  courseName ;//toString method whenever called by adapter or any other method for course class displays this
    }

}
