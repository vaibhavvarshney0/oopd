import java.util.ArrayList;

public class CourseRequirement extends Course
{
    //pre-requisite part
    private String degree;
    private String year;
    private String pre_req_course;


    public String getPre_req_course()
    {
        return pre_req_course;
    }

    public void setPre_req_course(String pre_req_course)
    {
        this.pre_req_course = pre_req_course;
    }

    public String getYear()
    {

        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getDegree()
    {
        return degree;
    }

    public void setDegree(String degree)
    {
        this.degree = degree;
    }

    void Stud_UnEnrolled(String student_name)
    {
        stud_Enrolled.remove(student_name);
        setEnrolled(getEnrolled()-1);
    }

    void ShowCourse()                   //overridding
    {
        System.out.println("COURSE NAME : "+getName());
        System.out.println("MAX STUDENT STRENGTH : " +getMax_stud());
        System.out.println("INSTRUCTOR : "+getProfessorname());
        System.out.println("PRE-REQUISITE COURSE : "+ getPre_req_course());
        System.out.println("PRE-REQUISITE YEAR : "+ getYear());
        System.out.println("PRE-REQUISITE DEGREE : "+ getDegree());
        System.out.println("--------------------------------");
    }
}
