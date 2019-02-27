package example.org.test.week03day02sol;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private String studentId;
    private String studentName;
    private String studentMajor;
    private String studentMinor;
    private String studentExpected;
    private String studentGPA;
    private String studentCompleted;

    public Student(){}

    public Student(String studentId, String studentName, String studentMajor, String studentMinor, String studentExpected, String studentGPA, String studentCompleted) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentMajor = studentMajor;
        this.studentMinor = studentMinor;
        this.studentExpected = studentExpected;
        this.studentGPA = studentGPA;
        this.studentCompleted = studentCompleted;
    }

    protected Student(Parcel in) {
        studentId = in.readString();
        studentName = in.readString();
        studentMajor = in.readString();
        studentMinor = in.readString();
        studentExpected = in.readString();
        studentGPA = in.readString();
        studentCompleted = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(studentId);
        dest.writeString(studentName);
        dest.writeString(studentMajor);
        dest.writeString(studentMinor);
        dest.writeString(studentExpected);
        dest.writeString(studentGPA);
        dest.writeString(studentCompleted);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public String getStudentMinor() {
        return studentMinor;
    }

    public void setStudentMinor(String studentMinor) {
        this.studentMinor = studentMinor;
    }

    public String getStudentExpected() {
        return studentExpected;
    }

    public void setStudentExpected(String studentExpected) {
        this.studentExpected = studentExpected;
    }

    public String getStudentGPA() {
        return studentGPA;
    }

    public void setStudentGPA(String studentGPA) {
        this.studentGPA = studentGPA;
    }

    public String getStudentCompleted() {
        return studentCompleted;
    }

    public void setStudentCompleted(String studentCompleted) {
        this.studentCompleted = studentCompleted;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentMajor='" + studentMajor + '\'' +
                ", studentMinor='" + studentMinor + '\'' +
                ", studentExpected='" + studentExpected + '\'' +
                ", studentGPA='" + studentGPA + '\'' +
                ", studentCompleted='" + studentCompleted + '\'' +
                '}';
    }
}
