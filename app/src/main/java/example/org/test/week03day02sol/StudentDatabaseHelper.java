package example.org.test.week03day02sol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import static example.org.test.week03day02sol.StudentDatabaseContract.COLUMN_COMPLETED;
import static example.org.test.week03day02sol.StudentDatabaseContract.COLUMN_EXPECTED;
import static example.org.test.week03day02sol.StudentDatabaseContract.COLUMN_GPA;
import static example.org.test.week03day02sol.StudentDatabaseContract.COLUMN_ID;
import static example.org.test.week03day02sol.StudentDatabaseContract.COLUMN_MAJOR;
import static example.org.test.week03day02sol.StudentDatabaseContract.COLUMN_MINOR;
import static example.org.test.week03day02sol.StudentDatabaseContract.COLUMN_NAME;
import static example.org.test.week03day02sol.StudentDatabaseContract.DATABASE_NAME;
import static example.org.test.week03day02sol.StudentDatabaseContract.DATABASE_VERSION;
import static example.org.test.week03day02sol.StudentDatabaseContract.TABLE_NAME;
import static example.org.test.week03day02sol.StudentDatabaseContract.getWhereClauseById;

public class StudentDatabaseHelper extends SQLiteOpenHelper {
    public StudentDatabaseHelper(@NonNull Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(StudentDatabaseContract.createQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        onCreate(database);
    }
    public long insertStudentIntoDatabase(@NonNull Student student) {
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ID, student.getStudentId());
        contentValues.put(COLUMN_NAME, student.getStudentName());
        contentValues.put(COLUMN_MAJOR, student.getStudentMajor());
        contentValues.put(COLUMN_MINOR, student.getStudentMinor());
        contentValues.put(COLUMN_EXPECTED, student.getStudentExpected());
        contentValues.put(COLUMN_GPA, student.getStudentGPA());
        contentValues.put(COLUMN_COMPLETED, student.getStudentCompleted());

        return writeableDatabase.insert(TABLE_NAME, null, contentValues);
    }
    public ArrayList<Student> getAllStudentsFromDatabase() {
        ArrayList<Student> returnStudentList = new ArrayList<>();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(StudentDatabaseContract.getAllStudentsQuery(), null);
        if(cursor.moveToFirst()) {
            do {
                //get values
                String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String major = cursor.getString(cursor.getColumnIndex(COLUMN_MAJOR));
                String minor = cursor.getString(cursor.getColumnIndex(COLUMN_MINOR));
                String expected = cursor.getString(cursor.getColumnIndex(COLUMN_EXPECTED));
                String gpa = cursor.getString(cursor.getColumnIndex(COLUMN_GPA));
                String completed = cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETED));

                returnStudentList.add(new Student(id, name, major, minor, expected, gpa,completed));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return returnStudentList;
    }
    public Student getStudentById(int id) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Student returnStudent = new Student();
        Cursor cursor = readableDatabase.rawQuery(StudentDatabaseContract.getOneStudentById(id), null);
        if(cursor.moveToFirst()){
            String idFromDB = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String major = cursor.getString(cursor.getColumnIndex(COLUMN_MAJOR));
            String minor = cursor.getString(cursor.getColumnIndex(COLUMN_MINOR));
            String expected = cursor.getString(cursor.getColumnIndex(COLUMN_EXPECTED));
            String gpa = cursor.getString(cursor.getColumnIndex(COLUMN_GPA));
            String completed = cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETED));
            returnStudent = new Student(idFromDB, name, major, minor, expected, gpa,completed );
        }
        cursor.close();
        return returnStudent;
    }
    public long updateStudentInDatabase(@NonNull Student newStudentInfo) {
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ID, newStudentInfo.getStudentId());
        contentValues.put(COLUMN_NAME, newStudentInfo.getStudentName());
        contentValues.put(COLUMN_MAJOR, newStudentInfo.getStudentMajor());
        contentValues.put(COLUMN_MINOR, newStudentInfo.getStudentMinor());
        contentValues.put(COLUMN_EXPECTED, newStudentInfo.getStudentExpected());
        contentValues.put(COLUMN_GPA, newStudentInfo.getStudentGPA());
        contentValues.put(COLUMN_COMPLETED, newStudentInfo.getStudentCompleted());

        return writeableDatabase.update(TABLE_NAME,
                contentValues,
                getWhereClauseById(),
                new String[]{String.valueOf(newStudentInfo.getStudentId())});
    }
    public long deleteFromDatabaseById(String[] id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, getWhereClauseById() + id[0], null);
    }

}
