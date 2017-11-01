import java.io.File;
import java.util.Scanner;

public class Main_class
{
    public static void main(String args[]) throws Exception
    {
        CourseManagement cm=new CourseManagement();

        String line;
        try
        {
            File file = new File("input.txt");
            Scanner sc=new Scanner(file);

            while ((line = sc.nextLine()) != null)
            {
                String[] input=line.split(" ");
                for(int i=0;i<input.length;i++)
                {
                    input[i]=input[i].toUpperCase();
                }

                switch(input[0])
                {
                    case "ADDS":
                    {
                        cm.addS(input[1],input[2],input[3],input[4]);

                        break;
                    }

                    case "ADDP":
                    {
                        cm.addP(input[1],input[2]);
//                        for(Professor p:cm.professors)
//                            p.ShowProf();
//                            System.out.println(p.ShowProf());
//                        System.out.println("cool");

                        break;
                    }

                    case "ADDC":
                    {
                        if(input.length==4)
                        {
                            cm.addC(input[1],input[2],input[3]);
                        }
                        else
                            cm.addC_pre_req(input[1], input[2], input[3], input[4], input[5], input[6]);
//                        for(Course p:cm.courses)
//                            p.ShowCourse();
                        break;
                    }

                    case "ENROLL":
                    {
                        cm.enroll(input[1],input[2]);
//                        for(Student s:cm.students)
//                            s.ShowStudent();
                        break;
                    }

                    case "UNENROLL":
                    {
                        cm.unroll(input[1],input[2]);
                        break;
                    }

                    case "SHOWS":
                    {
                        cm.showS(input[1]);
                        break;
                    }

                    case "SHOWP":
                    {
                        //System.out.println("hsdcss");
                        cm.showP(input[1]);
                        break;
                    }

                    case "SHOWC":
                    {
                        cm.showC(input[1]);
                        break;
                    }

                    case "MODIFY":
                    {
                        cm.modify(input[1],input[2],input[3]);
                        break;
                    }
                }
                line="";
            }
        }
        catch (Exception e)
        {

        }
    }       //main close
}       //main_class close

