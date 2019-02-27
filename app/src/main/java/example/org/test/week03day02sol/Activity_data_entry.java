package example.org.test.week03day02sol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Activity_data_entry extends AppCompatActivity {
    public static final String KEY_STUDENT_RESULT ="car_result";
    public static final int RESULT_CODE = 525;

    EditText etStudentIdDisplay;
    EditText etStudentNameDisplay;
    EditText etStudentMajorDisplay;
    EditText etStudentMinorDisplay;
    EditText etStudentExpectedDisplay;
    EditText etStudentGPADisplay;
    EditText etStudentCompletedDisplay;
    Intent sentIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        sentIntent = getIntent();
        bindViews();
    }

    public void bindViews(){
    etStudentIdDisplay = findViewById(R.id.etStudentId);
    etStudentNameDisplay = findViewById(R.id.etStudentName);
    etStudentMajorDisplay = findViewById(R.id.etStudentMajor);
    etStudentMinorDisplay = findViewById(R.id.etStudentMinor);
    etStudentExpectedDisplay = findViewById(R.id.etStudentExpected);
    etStudentGPADisplay = findViewById(R.id.etStudentGPA);
    etStudentCompletedDisplay= findViewById(R.id.etStudentCompleted);
    }
    public Student generateStudentObjectFromInput(){
        Student returnStudent = new Student();
        returnStudent.setStudentName(etStudentNameDisplay.getText()!= null ? etStudentNameDisplay.getText().toString():"");
        returnStudent.setStudentId(etStudentIdDisplay.getText()!= null ? etStudentIdDisplay.getText().toString():"");
        returnStudent.setStudentMajor(etStudentMajorDisplay.getText()!= null ? etStudentMajorDisplay.getText().toString():"");
        returnStudent.setStudentMinor(etStudentMinorDisplay.getText()!= null ? etStudentMinorDisplay.getText().toString():"");
        returnStudent.setStudentExpected(etStudentExpectedDisplay.getText()!= null ? etStudentExpectedDisplay.getText().toString():"");
        returnStudent.setStudentName(etStudentGPADisplay.getText()!= null ? etStudentGPADisplay.getText().toString():"");
        returnStudent.setStudentName(etStudentCompletedDisplay.getText()!= null ? etStudentCompletedDisplay.getText().toString():"");

        return returnStudent;
    }

    public void onClick(View view) {
        Student studentResult =generateStudentObjectFromInput();
        Bundle bundleOfTheStudentResults = new Bundle();
        bundleOfTheStudentResults.putParcelable(KEY_STUDENT_RESULT, studentResult);
        sentIntent.putExtras(bundleOfTheStudentResults);
        setResult(RESULT_CODE, sentIntent);
        finish();
    }
}
