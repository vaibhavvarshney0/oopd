import java.util.ArrayList;

public class Professor {
    String name;
    String area_of_expertise;
    ArrayList<String> course=new ArrayList<>();

    public ArrayList<String> getCourse()
    {
        return course;
    }

    public void setCourse(String course)
    {
        if(!this.course.contains(course))
            this.course.add(course);
    }

    public String getArea_of_expertise() {
        return area_of_expertise;
    }

    public void setArea_of_expertise(String area_of_expertise) {
        this.area_of_expertise = area_of_expertise;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void ShowProf()
    {
        System.out.println("PROF NAME : "+this.name);
        System.out.println("AREA OF EXPERTISE : "+ this.getArea_of_expertise());
        System.out.print("COURSES UNDER PROF. : ");
        for(String c : course)
            System.out.println(c+" ");
        System.out.println("--------------------------------------");
    }
}
