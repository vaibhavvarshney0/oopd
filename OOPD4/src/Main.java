import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{

    public static void main(String...s) throws Exception
    {
//        Management management = new Management();

//        Scanner sc = new Scanner(System.in);

//        while(true)
//        {
//            System.out.print(">>");
//            String in = sc.nextLine();
//            management.input(in);
//
//        }//while end

        try {
            File file = new File("input.txt");
            Scanner sc = new Scanner(file);

            System.out.println("Available admins are : ");
            Management management = new Management();

            String line = "";
            while ((line = sc.nextLine()) != null)
            {
                System.out.print(">>");
                management.input(line);
                line = "";
            }
        }
        catch (Exception e)
        {
//            System.out.println("exception occured file reading input from file!!!");
        }
    }
}
