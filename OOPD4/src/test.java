import java.util.Scanner;

public class test
{
    public static void main(String...s)
    {
        Scanner sc = new Scanner(System.in);

        String in = sc.nextLine();
        System.out.println(in);

        String[] input = in.split("\\s+");
        for(String i :input)
        {
            System.out.println(i);
        }

        String add = "";
        for(int i = 2;i<input.length;i++)
        {
            add = add+input[i];
        }

        System.out.println(add);
    }
}
