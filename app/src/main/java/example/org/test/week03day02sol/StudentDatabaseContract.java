package example.org.test.week03day02sol;

import android.util.Log;

import java.util.Locale;

public class StudentDatabaseContract {
    public static final String DATABASE_NAME = "student_db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Students";
    //Columns in database names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MAJOR = "major";
    public static final String COLUMN_MINOR = "minor";
    public static final String COLUMN_EXPECTED = "expected";
    public static final String COLUMN_GPA = "gpa";
    public static final String COLUMN_COMPLETED = "completed";

    public static String createQuery() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("CREATE TABLE ");
        queryBuilder.append(TABLE_NAME);
        queryBuilder.append(" ( ");
        queryBuilder.append(COLUMN_ID);
        queryBuilder.append(" ");
        queryBuilder.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        queryBuilder.append(COLUMN_ID);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_NAME);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_MAJOR);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_MINOR);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_EXPECTED);
        queryBuilder.append(" TEXT )");
        queryBuilder.append(COLUMN_GPA);
        queryBuilder.append(" TEXT )");
        queryBuilder.append(COLUMN_COMPLETED);
        queryBuilder.append(" TEXT )");

        //Log the query so we can check for correctness
        Log.d("TAG", "createQuery: " + queryBuilder.toString());

        return queryBuilder.toString();
    }
    public static String getAllStudentsQuery() {
        return "SELECT * FROM " + TABLE_NAME;
    }

    public static String getOneStudentById(int id) {
        return String.format("SELECT * FROM %s WHERE %s = \"%d\"", TABLE_NAME, COLUMN_ID, id);
    }

    public static String getWhereClauseById() {
        return String.format(Locale.US, "%s = ", COLUMN_ID);
    }

}
