package com.example.mehreenathar.attendancesystem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context myContext;
    private ArrayList<Student> getting_students;
    private ArrayList<Student>presentMarked=new ArrayList<Student>();
    public   ArrayList<Student> getMarkedStudents()
    {
        return presentMarked;
    }
    public void onBindViewHolder(@NonNull MyViewHolder studentViewHolder, int i)
    {
        final Student student=getting_students.get(i);
        String displayText=getting_students.get(i).getStudentName();
        studentViewHolder.names.setText(displayText);
        studentViewHolder.checkBox.setChecked(student.isSelected());
        getting_students.get(i).attendancePercentage();
        //+":"+Integer.toString(getting_students.get(i).getAttendacePercentage());
        //
        String attendancePer="Attendance % "+" : "+Integer.toString(getting_students.get(i).getAttendacePercentage());//Integer.toString(19);// int value=student.attendancePercentage();
        studentViewHolder.attendancePercentage.setText(attendancePer);
        studentViewHolder.setItemClickListener(new MyViewHolder.ItemClickListener(){

            @Override
            public void onItemClick(View v, int position) {
                CheckBox cBox=(CheckBox)v;
                Student currentStudent=getting_students.get(position);
                if(cBox.isChecked())
                {
                    currentStudent.setSelected(true);
                    presentMarked.add(currentStudent);
                }
                else if(!(cBox.isChecked()))
                {
                    currentStudent.setSelected(false);
                    presentMarked.remove(currentStudent);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        if(getting_students!=null) {
            return getting_students.size();
        }
        else
        {
            return 0;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_student_row,viewGroup,false);
        return new MyViewHolder(v,(MarkAttendance)myContext);
    }
    public MyAdapter(Context mContext, ArrayList<Student>get_students)
    {
        this.myContext=mContext;

        this.getting_students=get_students;
    }

    /********************VIEW HOLDER CLASS*************************/
    public static class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener
    {

        public TextView names;
        public TextView attendancePercentage;
        public CheckBox checkBox;
        public MarkAttendance markAttendance;
        ItemClickListener itemClickListener;
        //private ArrayList<Student>markedStudents;
        public MyViewHolder(View itemView,MarkAttendance markAttendance){
            super(itemView);

            names=(TextView)itemView.findViewById(R.id.name);
            checkBox=(CheckBox) itemView.findViewById(R.id.checkBox);
            attendancePercentage=(TextView)itemView.findViewById(R.id.name3);
            this.markAttendance=markAttendance;
            checkBox.setOnClickListener(this);

        }
        public void  setItemClickListener(ItemClickListener il)
        {
            this.itemClickListener=il;
        }
        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getAdapterPosition());
        }
        interface  ItemClickListener
        {
            void onItemClick(View v,int position);

        }
    }

}

