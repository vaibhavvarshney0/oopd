import java.util.ArrayList;

public class Student {
    private String name;
    String roll_no;
    private String degree;
    private String year;
    private ArrayList<String> course=new ArrayList<String>();

    public String getRoll_no()
    {
        return roll_no;
    }

    public void setRoll_no(String roll_no)
    {
        this.roll_no = roll_no;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<String> getCourse()
    {
        return course;
    }

    public void setCourse(String course)
    {
        this.course.add(course);
    }

    public void setName(String name)
    {
        this.name = name;

    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    //course


    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }


    void UnEnroll(String course_name)
    {
        getCourse().remove(course_name);
    }

    int Enroll(String name)
    {
        if(this.getCourse().contains(name))
        {
            System.out.println("student already enrolled for this course!!!");
            System.out.println("------------------------------------");
            return -1;
        }
        this.course.add(name);
        return 0;
    }

    void ShowStudent()
    {
        System.out.println("NAME : "+getName());
        System.out.print("courses : ");
        for(String c:getCourse())
            System.out.print(c+" ");
        System.out.println();
        System.out.println("Degree :"+getDegree());
        System.out.println("year :"+getYear());
        System.out.println("-------------------------------");
    }
}
