import java.util.ArrayList;

public class Course {
    protected String name;
    protected int max_stud;
    protected int enrolled=0;         //i.e., seats filled
    protected String professorname;
    protected ArrayList<String>  stud_Enrolled=new ArrayList<>();

    public ArrayList<String> getStud_Enrolled()
    {
        return stud_Enrolled;
    }

    public void setStud_Enrolled(String student_name)
    {
        this.stud_Enrolled.add(student_name);
        this.enrolled++;
    }

    public String getProfessorname()
    {
        return professorname;
    }

    public void setProfessorname(String professorname)
    {
        this.professorname = professorname;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getMax_stud()
    {
        return max_stud;
    }

    public void setMax_stud(int max_stud)
    {
        if(max_stud>=getEnrolled())
        {
            this.max_stud = max_stud;
            return;
        }
        System.out.println("cant update max students!!(more students are already enrolled)");
    }


    //array of student enrolled
    void Modify(int new_max, String prof)
    {
        this.setProfessorname(prof);
        this.setMax_stud(new_max);
    }

    public int getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(int enrolled) {
        this.enrolled = enrolled;
    }

    void Stud_UnEnrolled(String student_name)
    {
        stud_Enrolled.remove(student_name);
        setEnrolled(getEnrolled()-1);
    }

    void ShowCourse()
    {
        System.out.println("COURSE NAME : "+getName());
        System.out.println("MAX STUDENT STRENGTH : " +getMax_stud());
        System.out.println("INSTRUCTOR : "+getProfessorname());
        System.out.println("--------------------------------");
    }
}



