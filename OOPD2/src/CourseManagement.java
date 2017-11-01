import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;

public class CourseManagement {
    ArrayList<Student> students=new ArrayList<>();
    ArrayList<Course> courses=new ArrayList<>();
    ArrayList<Professor> professors=new ArrayList<>();
    ArrayList<CourseRequirement> courses_pre_req=new ArrayList<>();

    int isPresentStudent(String name)
    {
        for(Student s:students)
        {
            if(s.getName().compareTo(name)==0)
                return students.indexOf(s);
        }

        return -1;
    }

    int isPresentProfessor(String name)
    {
        for(Professor p:professors)
        {
            if(p.getName().compareTo(name)==0)
                return professors.indexOf(p);
        }

        return -1;
    }

    int isCoursePresent(String name)
    {
        for(Course c:courses)
        {
            if(c.getName().compareTo(name)==0)
            {
                return courses.indexOf(c);
            }
        }
        return -2;          //invalid course name
    }

    int isCourse_pre_reqPresent(String name)
    {
        for(CourseRequirement c:courses_pre_req)
        {
            if(c.getName().compareTo(name)==0)
            {
                return courses_pre_req.indexOf(c);
            }
        }
        return -1;
    }


    void addS(String name, String roll, String degree, String year)
    {
        Student s=new Student();
        s.setName(name);
        s.setRoll_no(roll);
        s.setDegree(degree);
        s.setYear(year);

        students.add(s);

        System.out.println("student added successfully!!!");
        System.out.println("-------------------------------------");

    }

    void addP(String name, String expertise)
    {
        Professor p=new Professor();
        p.setName(name);
        p.setArea_of_expertise(expertise);

        professors.add(p);

        System.out.println("professor added successfully!!!");
        System.out.println("-------------------------------------");

    }

    void addC(String name, String max_stud, String prof)
    {
        int prof_pos = isPresentProfessor(prof);
        if(prof_pos>=0)
        {
            Course c=new Course();
            c.setName(name);
            c.setMax_stud(Integer.parseInt(max_stud));
            c.setProfessorname(prof);

            courses.add(c);
        }
        else
        {
            System.out.println("invalid prof. name!!!");
            System.out.println("------------------------------------");
            return;
        }

        System.out.println("course added successfully!!!");
        System.out.println("-------------------------------------");
    }

     void enroll(String course_name, String student_name)
    {
        int student_pos=isPresentStudent(student_name);
        int course_pos=isCoursePresent(course_name);
        int course_pre_req_pos=isCourse_pre_reqPresent(course_name);

        if(course_pos<0 && course_pre_req_pos<0)
        {
            System.out.println("invalid course name!!!");
            System.out.println("------------------------------------");
            return;
        }

        if(student_pos!=-1 && course_pos>=0)         //define condition
        {
            Student s=students.get(student_pos);
            Course c=courses.get(course_pos);

            if(c.getEnrolled()<c.getMax_stud())
            {
                int status = s.Enroll(course_name);
                if(status==-1)                  //course already assigned!!
                    return;

                students.set(student_pos, s);

                c.setStud_Enrolled(student_name);
                courses.set(course_pos, c);
            }
            else
            {
                System.out.println("No seats available in the course!!!");
                System.out.println("------------------------------------");
                return;
            }


        }

        else if(student_pos==-1)
        {
            System.out.println("Invalid student name!!!");
            System.out.println("------------------------------------");
            return;
        }

        else if(course_pos==-2)
        {
            Student s=students.get(student_pos);

            CourseRequirement cr=courses_pre_req.get(course_pre_req_pos);

            if(course_pre_req_pos!=-1)
            {
                if(s.getDegree().compareTo(cr.getDegree())==0 &&
                        s.getYear().compareTo(cr.getYear())==0 &&
                        s.getCourse().contains(cr.getPre_req_course()))
                {
                    if(cr.getEnrolled()<cr.getMax_stud())
                    {
                        s.Enroll(course_name);
                        students.set(student_pos, s);

                        cr.setStud_Enrolled(student_name);
                        courses_pre_req.set(course_pre_req_pos, cr);
                    }
                }
                else
                {
                    System.out.println("requirements not fulfilled!!!");
                    System.out.println("------------------------------------");
                    return;
                }
            }
            else
            {
                System.out.println("invalid course name!!!");
                System.out.println("------------------------------------");
                return;
            }
        }

        System.out.println("enrolled successfully!!!");
        System.out.println("-----------------------------------");

    }

     void unroll(String course_name, String student_name)
    {
        int student_pos=isPresentStudent(student_name);
        int course_pos=isCoursePresent(course_name);

        if(course_pos==-2)
        {
            System.out.println("invalid course name!!!");
            return;
        }

        if(student_pos!=-1)
        {
            Student s=students.get(student_pos);

            if(!s.getCourse().contains(course_name))
            {
                System.out.println("student is not enrolled in this course!!!");
                return;
            }

            s.getCourse().remove(course_name);
            students.set(student_pos, s);           //update student after unenrolling

            Course c=courses.get(course_pos);
            c.getStud_Enrolled().remove(student_name);
            c.setEnrolled(c.getEnrolled()-1);

            courses.set(course_pos, c);         //update the course after unenrolling
        }

        Student s=students.get(student_pos);
        s.UnEnroll(course_name);
        students.set(student_pos, s);

        Course c=courses.get(course_pos);
        c.Stud_UnEnrolled(student_name);
        courses.set(course_pos, c);

        System.out.println("unenrolled successfully!!!");
        System.out.println("-------------------------------------");

    }

    void showS(String name)
    {
        int student_pos=isPresentStudent(name);

        if(student_pos!=-1)
        {
            Student s=students.get(student_pos);
            s.ShowStudent();
        }
        else
            System.out.println("No such record!!");
    }

     void showP(String name)
    {
        int prof_pos=isPresentProfessor(name);
        if (prof_pos!=-1)
        {
            Professor p=professors.get(prof_pos);
            p.ShowProf();
        }
        else
            System.out.println("No such record!!!");
    }

    void showC(String name)
    {
        int course_pos=isCoursePresent(name);

        int course_pre_req_pos=isCourse_pre_reqPresent(name);

        if(course_pre_req_pos<0 && course_pos<0)
        {
            System.out.println("No such record!!!");
            System.out.println("------------------------------------");
            return;
        }

        if(course_pos>=0)
        {
            Course c=courses.get(course_pos);
            c.ShowCourse();
        }

        if(course_pre_req_pos>=0)
        {
            CourseRequirement cr=courses_pre_req.get(course_pre_req_pos);
            cr.ShowCourse();
        }
    }

    void modify(String course_name, String new_max, String prof_name)
    {
        int course_pos=isCoursePresent(course_name);
        int course_req=isCourse_pre_reqPresent(course_name);

        if(course_pos<0 && course_req==-1)
        {
            System.out.println("invalid course name");
            System.out.println("------------------------------------");
            return;
        }

        if(course_pos>=0)
        {
            Course c=(Course)courses.get(course_pos);

            int prof_pos=isPresentProfessor(prof_name);
            if(prof_pos==-1)
            {
                System.out.println("invalid professor name!!!");
                System.out.println("------------------------------------");
                return;
            }
            Professor p=professors.get(prof_pos);
            c.Modify(Integer.parseInt(new_max), prof_name);

            courses.set(course_pos, c);
            //look here
            p.setCourse(course_name);
            professors.set(prof_pos, p);
        }

        if(course_req>=0) {
            CourseRequirement c = (CourseRequirement) courses_pre_req.get(course_req);

            int prof_pos = isPresentProfessor(prof_name);
            if (prof_pos == -1) {
                System.out.println("invalid professor name!!!");
                System.out.println("------------------------------------");
                return;
            }
            Professor p = professors.get(prof_pos);

            c.Modify(Integer.parseInt(new_max), prof_name);
            courses_pre_req.set(course_req, c);
            //look here
            p.setCourse(course_name);
            professors.set(prof_pos, p);
        }

        System.out.println("Modified successfully!!!");
        System.out.println("-------------------------------------");
    }

    void addC_pre_req(String name, String max_stud, String prof_name, String degree, String year, String course_pre_req_name)
    {
        CourseRequirement cr=new CourseRequirement();

        cr.setName(name);
        cr.setMax_stud(Integer.parseInt(max_stud));
        cr.setProfessorname(prof_name);
        cr.setDegree(degree);
        cr.setYear(year);
        cr.setPre_req_course(course_pre_req_name);

        courses_pre_req.add(cr);

        System.out.println("course added successfully!!!");
        System.out.println("------------------------------------");
    }
}
