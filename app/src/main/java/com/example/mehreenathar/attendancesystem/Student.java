package com.example.mehreenathar.attendancesystem;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable
{
    private  static int studentId=1; //incremented every time (created once used always for all instances)
    private String studentName;
    private String fatherName;
    private  String gender;
    private String contactNo;
    private boolean isSelected;
    private int attendacePercentage;
    private ArrayList<Attendance>stuAttendanceRecord; //student's attance record kept in arraylist

    public int getAttendacePercentage() {
        return attendacePercentage;
    }

    public void setAttendacePercentage(int attendacePercentage) {
        this.attendacePercentage = attendacePercentage;
    }


    public static void setStudentId(int studentId) {
        Student.studentId = studentId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    //get total attendances
    public int totalAttendanceCount()
    {
        int count;

        for(count=0;count<stuAttendanceRecord.size();count++)
        {
            if(!(stuAttendanceRecord.get(count).isMarked()))//true
            {
                return 0;
            }
        }
        return count;
    }
    public int presentCount()
    {
        int counter=0;
        for(int count=0;count<stuAttendanceRecord.size();count++)
        {
            if(stuAttendanceRecord.get(count).getStatus().equals("P"))//true
            {
               counter=counter+1;
            }
        }
        return counter;
    }
    //mark attendance
    public boolean markAttendance(Attendance attendance)
    {
        boolean marked=false;
        //total classes of a course are at max 20
        if(totalAttendanceCount()<20) {
            stuAttendanceRecord.add(attendance);
            marked=true;
        }
        return marked;

    }
    //calculate attendance percentage ...totalClasses=20....for absent classes check where attendance.status='A'
    public void attendancePercentage()
    {
        if(totalAttendanceCount()!=0)//divide by 0 should never be allowed
        {
            attendacePercentage=(presentCount()/totalAttendanceCount())*100;
        }


    }
    public Student(String studentName, String fatherName, String gender, String contactNo, ArrayList<Attendance> stuAttendanceRecord) {
        this.studentName = studentName;
        this.fatherName = fatherName;
        this.gender = gender;
        this.contactNo = contactNo;
        this.stuAttendanceRecord = stuAttendanceRecord;
    }

    public Student(String studentName, String fatherName, String gender, String contactNo) {
        this.studentName = studentName;
        this.fatherName = fatherName;
        this.gender = gender;
        this.contactNo = contactNo;
        isSelected=false;
        this.attendacePercentage=0;
        studentId=studentId+1;                 //increment counter for generating new student id
        stuAttendanceRecord=new ArrayList<Attendance>();
    }

    public static int getStudentId() {
        return studentId;
    }


    /*
        public void takeCourse(Course course)
        {
            if(this.coursesTaken!=null)
            {
                for(int i=0;i<coursesTaken.size();i++)
                {
                    if(!(coursesTaken.get(i).getCourseName().equals(course.getCourseName())))
                    {
                        this.coursesTaken.add(course);//only take course if not already registered in it
                    }


                }

            }
            else
            {
                this.coursesTaken=new ArrayList<Course>();
                this.coursesTaken.add(course);
            }
        }
    */
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public ArrayList<Attendance> getStuAttendanceRecord() {
        return stuAttendanceRecord;
    }

    public void setStuAttendanceRecord(ArrayList<Attendance> stuAttendanceRecord) {
        this.stuAttendanceRecord = stuAttendanceRecord;
    }
}
