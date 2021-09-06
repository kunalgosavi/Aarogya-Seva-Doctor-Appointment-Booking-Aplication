package com.example.Aarogya_seva;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_PATIENT="register_patient";
    public static final String TABLE_DOCTOR="doctor";
    public static final String TABLE_APPOINTMENT="appointment";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null ,1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE register_patient(name varchar(50),email varchar(50),mobile_no number(10) ,username TEXT primary key,password TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE doctor(email varchar(20) primary key,password TEXT,name varchar(20),mobile_no number(10),clinic_address varchar(50),category varchar(20))");
        sqLiteDatabase.execSQL("CREATE TABLE appointment(username varchar(10) ,category varchar(20),doctor varchar(20),date varchar(20),time varchar(20) )");
           }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_PATIENT);
        onCreate(sqLiteDatabase);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_DOCTOR);
        onCreate(sqLiteDatabase);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_APPOINTMENT);
        onCreate(sqLiteDatabase);



    }
    public  long  add_pat(String name, String email, String mobile_no, String username, String password ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("mobile_no",mobile_no);
        contentValues.put("username",username);
        contentValues.put("password",password);
        long res=db.insert("register_patient",null,contentValues);
        db.close();
        return res;

    }

    public long add_doc(String password, String name, String mobile_no, String clinic_address, String email, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        contentValues.put("name", name);
        contentValues.put("mobile_no", mobile_no);
        contentValues.put("clinic_address", clinic_address);
        contentValues.put("email", email);
        contentValues.put("category", category);
        long add=db.insert("doctor",null,contentValues);
        db.close();
        return  add;
    }

    public long add_appointment(String appusername,String appcategory,String appdoctor,String appdate,String apptime)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", appusername);
        contentValues.put("category",appcategory );
        contentValues.put("doctor",appdoctor );
        contentValues.put("date",appdate );
        contentValues.put("time", apptime);
        long add=db.insert("appointment",null,contentValues);
        db.close();
        return  add;
    }

    public boolean checkUser(String username,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor,cursor1;
        cursor = db.rawQuery("Select * from register_patient where username=?",new String[]{username});
        cursor1 =db.rawQuery("Select * from register_patient where password=?",new String[]{password});
        if (cursor.getCount()>0&&cursor1.getCount()>0)
            return true;
        else
            return false;


    }

    public boolean checkDoctor( String doc_id,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor  cursor2,cursor3;
        cursor2=db.rawQuery("select * from doctor where email=?",new String[]{doc_id});
        cursor3=db.rawQuery("Select * from doctor where password=?",new String[]{password});
        if(cursor2.getCount()>0&&cursor3.getCount()>0)
            return  true;
        else
            return  false;

    }

    public boolean checkTime(String date,String time)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor4,cursor5;
        cursor4=db.rawQuery("select date from appointment where date=?",new String[]{date});
        cursor5=db.rawQuery("Select time from appointment where time=?",new String[]{time});
        if(cursor4.getCount()>0&&cursor5.getCount()>0)
            return  true;
        else
            return  false;

    }

   public List<String> getAllLabels(String category){
        List<String> list=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select name from doctor where category=?",new String[]{category});


        if (cursor.moveToFirst()){
            do {
                list.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
   }

    public Cursor getDoctorDetail()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor add=db.rawQuery("select * from "+TABLE_DOCTOR,null);
        return  add;
    }

    public Cursor getPatientDetail()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor add=db.rawQuery("select * from "+TABLE_PATIENT,null);
        return  add;
    }

    public Cursor getAppointmentDetail()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor add=db.rawQuery("select * from "+TABLE_APPOINTMENT,null);
        return  add;
    }

    public Cursor getAppDetail(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor add=db.rawQuery("select * from appointment where username=?",new String[]{name});
        return  add;
    }

    public Cursor getDoctorAppointmentDetail(String doctor_name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor add=db.rawQuery("select * from appointment where doctor=?",new String[]{doctor_name});
        return  add;
    }


}

