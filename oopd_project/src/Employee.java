import java.sql.*;

public class Employee extends User
{
    int register(String senderId, String senderName, String postId, String[] input_token)
    {
        try
        {
            //connection with database
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/FMS","root","1");

            //check if employee is already present
            PreparedStatement check_stmnt = con.prepareStatement("select * from Employee where emp_id = ? and work = ?");
            check_stmnt.setString(1, senderId);
            check_stmnt.setString(2, input_token[2]);

            ResultSet rs = check_stmnt.executeQuery();
            if(!rs.isBeforeFirst())
            {
                //Employee(EmployeeId, empName, worktype, total_task, task_completed)
                PreparedStatement stmt=con.prepareStatement("INSERT INTO Employee " + "VALUES (?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, senderId);
                stmt.setString(2, senderName);
                stmt.setString(3, input_token[2]);
                stmt.setInt(4, 0);
                stmt.setInt(5, 0);
                stmt.setInt(6, 1);
                stmt.setInt(7, 0);      //reported

                int status = stmt.executeUpdate();
                if(status==1)
                {System.out.println("employee entry done!!!");
                    con.close();
                    return 1;
                }
                else
                {System.out.println("employee entry failed!!!");
                    con.close();
                    return 0;
                }


            }
            else
            {
                System.out.println("employee with same id and profession already registered!!!");
                return 0;
            }
        }
        catch(Exception e){ System.out.println(e);
            return 0;}
    }

    //status done
    int updateRequestStatus(String senderId, String senderName, String postId, String[] message, long time_difference)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FMS", "root", "1");

            PreparedStatement check_id = con.prepareStatement("select * from Complaint where emp_id = ? and status = ? and id = ?");
            check_id.setString(1,senderId);
            check_id.setInt(2, 0);
            check_id.setString(3, postId);

            ResultSet rs = check_id.executeQuery();

            System.out.println("here now");

            if(!rs.isBeforeFirst()) //is empty
            {
                //
                System.out.println("incorrect status details!!!");
                return 0;
            }

            if(message[1].toLowerCase().compareTo("done") == 0)
            {
                System.out.println("here donw");
                //complaint table update
                PreparedStatement stmnt = con.prepareStatement("update Complaint set status=? where id = ?");
                stmnt.setInt(1,1);
                stmnt.setString(2, postId);
                System.out.println("here donw 1");
                stmnt.executeUpdate();
                System.out.println("here donw 2");
                //get employee details
                stmnt = con.prepareStatement("SELECT * FROM Employee where emp_id = ?");
                stmnt.setString(1, senderId);
                rs = stmnt.executeQuery();

                //employee table update
                rs.next();
                stmnt = con.prepareStatement("update Employee set availability=?, task_completed = ? where emp_id = ?");
                stmnt.setInt(1,1);
                if (time_difference<=8)
                {
                    stmnt.setInt(2, rs.getInt(5) + 1);
                }
                else
                    stmnt.setInt(2, rs.getInt(5));

                stmnt.setString(3, senderId);
                System.out.println("here donw 3");
                stmnt.executeUpdate();

                System.out.println("status update done by employee!!!");
                return 1;
            }

            else if (message[1].toLowerCase().compareTo("not done") == 0)
            {
                System.out.println("here not done");
                PreparedStatement stmnt = con.prepareStatement("update Complaint set status=? where id = ?");
                stmnt.setInt(1,1);
                stmnt.setString(2, postId);

                stmnt.executeUpdate();

                //employee table update
                stmnt = con.prepareStatement("update Employee set availability=? where emp_id = ?");
                stmnt.setInt(1,1);
                stmnt.setString(2, senderId);

                stmnt.executeUpdate();

                System.out.println("status update done by employee!!!");
                return -1;
            }
            else
            {
                System.out.println("incorrect status update!! Try again");
                return 0;
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
            return 2;
        }

    }

    int saveFeedback(String senderId, String senderName, String postId, String message)
    {
        try
        {
            System.out.println("here now before!!!!!!!!!!!!!!1");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FMS", "root", "1");

            PreparedStatement check_id = con.prepareStatement("select * from Complaint where student_id = ? and status = ? and id = ?");
            check_id.setString(1,senderId);
            check_id.setInt(2, 1);
            check_id.setString(3, postId);


            ResultSet rs = check_id.executeQuery();

            System.out.println("here now");

            System.out.println();
            if(!rs.isBeforeFirst()) //is empty
            {
                //
                System.out.println("incorrect status details!!!");
                return 0;
            }

            System.out.println("here donw");
            //complaint table update
            PreparedStatement stmnt = con.prepareStatement("update Complaint set feedback=? where id = ?");
            stmnt.setString(1,message);
            stmnt.setString(2, postId);
            System.out.println("here donw 1");
            stmnt.executeUpdate();
            System.out.println("here donw 2");

            return 1;

        }catch (Exception e){return 0;}
    }
}
