package example.org.test.week03day02sol;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_FOR_MAIN = 500;
    public static final String KEY_SHARED_PREF = "shared_pref";
    public static final String KEY_LAST_ENTERED_ID = "last_id";
    public static final String DATA_ENTRY_ACTION = "data.enty.activity";
    public static final String MY_FILE_TXT = "mytextfile.txt";

    //Declare views
    TextView tvStudentIdDisplay;
    TextView tvStudentNameDisplay;
    TextView tvStudentMajorDisplay;
    TextView tvStudentMinorDisplay;
    TextView tvStudentExpectedDisplay;
    TextView tvStudentGPADisplay;
    TextView tvStudentCompletedDisplay;
    SharedPreferences sharedPreferences;
    //Car Database Declaration
    StudentDatabaseHelper studentDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(KEY_SHARED_PREF, MODE_PRIVATE);
        studentDatabaseHelper = new StudentDatabaseHelper(this);
        bindViews();
    }
    public void bindViews(){
        tvStudentIdDisplay = findViewById(R.id.tvStudentId);
        tvStudentNameDisplay = findViewById(R.id.tvStudentName);
        tvStudentMajorDisplay = findViewById(R.id.tvStudentMajor);
        tvStudentMinorDisplay = findViewById(R.id.tvStudentMinor);
        tvStudentExpectedDisplay = findViewById(R.id.tvStudentExpected);
        tvStudentGPADisplay = findViewById(R.id.tvStudentGPA);
        tvStudentCompletedDisplay= findViewById(R.id.tvStudentCompleted);
    }
    public void populateTextViews(@NonNull Student studentInfoToPopulate) {
        tvStudentIdDisplay.setText(studentInfoToPopulate.getStudentId());
        tvStudentNameDisplay.setText(studentInfoToPopulate.getStudentName());
        tvStudentMajorDisplay.setText(studentInfoToPopulate.getStudentMajor());
        tvStudentMinorDisplay.setText(studentInfoToPopulate.getStudentMinor());
        tvStudentExpectedDisplay.setText(studentInfoToPopulate.getStudentExpected());
        tvStudentGPADisplay.setText(studentInfoToPopulate.getStudentGPA());
        tvStudentCompletedDisplay.setText(studentInfoToPopulate.getStudentCompleted());

    }
    public void onClick(View view) {
    }
    public void saveLastStudentIdToSharedPref(@NonNull Student student) {
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putString(KEY_LAST_ENTERED_ID, student.getStudentId());
        sharedPrefEditor.commit();
    }
    public void saveAndLogStudentInSharedPref(@NonNull Student student) {
        String id = sharedPreferences.getString(KEY_LAST_ENTERED_ID, "NO VALUE ENTERED");

        Log.d(
                "TAG",
                "saveAndLogStudentInSharedPref: IN SHARED PREF: ID = " + id );

        saveLastStudentIdToSharedPref(student);

        id = sharedPreferences.getString(KEY_LAST_ENTERED_ID, "NO VALUE ENTERED");

        Log.d(
                "TAG",
                "saveAndLogCarInSharedPref: IN SHARED PREF: ID = " + id );
    }
    public void saveStudentToDBandSeeLog(@NonNull Student student){
        studentDatabaseHelper.insertStudentIntoDatabase(student);
        ArrayList<Student> studentList = studentDatabaseHelper.getAllStudentsFromDatabase();
        for(Student currentStudent : studentList) {
            Log.d("TAG", currentStudent.toString());
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null) {
            Bundle resultBundle = data.getExtras();
            if(resultBundle != null){
                Student resultStudent = resultBundle.getParcelable(Activity_data_entry.KEY_STUDENT_RESULT);
                if(resultStudent != null) {
                    saveAndLogStudentInSharedPref(resultStudent);
                    saveStudentToDBandSeeLog(resultStudent);
                    populateTextViews(resultStudent);
                }

            }
        }
    }
    public void writeToFile() {
        try {

            FileOutputStream fileOutputStream= openFileOutput(MY_FILE_TXT, MODE_PRIVATE);
            fileOutputStream.write("SOME TEXT I AM SAVING".getBytes());
            Log.d("TAG", "writeToFile: " +fileOutputStream);
            fileOutputStream.close();
            Log.d("TAG", "writeToFile: TEXT WRITTEN TO FILE");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
