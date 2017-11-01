import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main_class {
    public static void main(String[] args) throws Exception
    {        //System.out.println("Enter your file : ");
        Student student=new Student();

        String line;
        try
        {
            File file = new File("1.txt");
            Scanner sc=new Scanner(file);

                while ((line = sc.nextLine()) != null) {
                    // Deal with the line
                    String[] input=line.split(" ");
                    if(input.length==1)
                    {
                        if(input[0].toUpperCase().compareTo("MAXIMUM")==0)
                        {
                            student.maximum();
                        }

                        else if(input[0].toUpperCase().compareTo("EXTRACT-MAX")==0)
                        {
                            student.extract_max();
                        }

                        else			//show
                        {
                            student.show();
                        }
                    }

                    else if(input.length==3)	//delete
                    {
                        student.delete(input[1], input[2]);
                    }

                    else
                    {
                        String[] courses=new String[input.length-3];

                        int j=0;
                        for(int i=3;i<input.length;i++)
                        {
                            courses[j++]=input[i];
                        }
                        student.insert(input[1], input[2], courses);
                    }

                    line="";
                }
        }

        catch (Exception e)
        {

        }
    }
}
