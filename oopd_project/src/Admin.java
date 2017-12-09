import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin
{
    void viewReport()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/FMS","root","1");

            //check if student is already present
//            System.out.println(senderId + senderName);
            PreparedStatement check_stmnt = con.prepareStatement("select * from Employee where reported = ?");
            check_stmnt.setInt(1,1);

            ResultSet rs = check_stmnt.executeQuery();

            if(!rs.isBeforeFirst())
            {
                System.out.println("no employees reported!!!");
                return;
            }

            System.out.println("reported employees ------------------");

            while (rs.next())
            {
                System.out.println("employee id : " + rs.getString(1) + "employee name : " + rs.getString(2));
            }

        }catch (Exception e){}
    }

    void report()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/FMS","root","1");

            PreparedStatement check_stmnt = con.prepareStatement("select emp_id from Employee where total_task - task_completed >=?");
            check_stmnt.setInt(1,10);

            ResultSet rs = check_stmnt.executeQuery();

            if(!rs.isBeforeFirst())
            {
                System.out.println("no employees reported!!!");
                return;
            }

            while (rs.next())
            {
                PreparedStatement ps = con.prepareStatement("UPDATE Employee set reported = ? where emp_id = ?");
                ps.setInt(1,1);
                ps.setString(2, rs.getString(1));
            }

        }catch (Exception e){}
    }
}
