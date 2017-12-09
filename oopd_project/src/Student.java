import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Student extends User
{
    //REGISTER student CLASSS YEAR ROOM
    int register(String senderId, String senderName, String postId, String[] input_token)
    {
        try
        {
            //connection with database
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/FMS","root","1");

            //check if student is already present
//            System.out.println(senderId + senderName);
            PreparedStatement check_stmnt = con.prepareStatement("select * from Student where id = ?");
            check_stmnt.setString(1,senderId);

            ResultSet rs = check_stmnt.executeQuery();

            if(!rs.isBeforeFirst())
            {
                PreparedStatement stmt=con.prepareStatement("INSERT INTO Student " + "VALUES (?, ?, ?, ?, ?)");
                stmt.setString(1, senderId);
                stmt.setString(2, senderName);
                stmt.setString(3, input_token[2]);
                stmt.setString(4, input_token[3]);
                stmt.setString(5, input_token[4]);

                int status = stmt.executeUpdate();
                con.close();
                System.out.println("student entry done!!!");
                return 1;
            }
            else
            {
                System.out.println("student already registered!!!");
                con.close();
                return 0;
            }

        }
        catch(Exception e){ System.out.println(e);
            return 0;}
    }

    int fileRequest(String senderId, String senderName, String postId, String[] input_token)
    {
        System.out.println("File Request");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FMS", "root", "1");

            //check if student is valid
            PreparedStatement check_student = con.prepareStatement("select id from Student where id = ?");
            check_student.setString(1, senderId);

            ResultSet rs1 = check_student.executeQuery();

            if(!rs1.isBeforeFirst())
            {
                System.out.println("No student present with id posting on facebook!!!");
                return 0;
            }

            //entry in Complaint table start
            String sql = "select * from Complaint where id=?";
            PreparedStatement check_comp = con.prepareStatement(sql);
            check_comp.setString(1, postId);
            ResultSet rs2 = check_comp.executeQuery();
            //need complaint id
            if(rs2.isBeforeFirst())//is not empty
            {
                System.out.println("Complaint Already exists");
                return 0;
            }

            //check if employee work type is valid and he is available
            PreparedStatement check_stmnt = con.prepareStatement("select * from Employee where work = ? and availability = ?");
            //System.out.println("work"+input_token[1]);
            check_stmnt.setString(1, input_token[1]);
            check_stmnt.setInt(2, 1);

            ResultSet rs = check_stmnt.executeQuery();
            //System.out.println("hhhjhhh");

            //System.out.println("status"+rs1.getCursorName());
            if(!rs.isBeforeFirst()) //ie is empty
            {
                System.out.println("No employee available right now!!!");
                return 2;
            }
            else
            {
                System.out.println("here");
                //allocate employee and update employee details
                while(rs.next()) {
                    check_stmnt = con.prepareStatement("update Employee set availability=? , total_task = ? where emp_id = ?");
                    System.out.println("after here");
                    check_stmnt.setInt(1,0);
                    check_stmnt.setInt(2, rs.getInt(4)+1);
                    System.out.println("after here");
                    String e_id=rs.getString(1);
                    check_stmnt.setString(3,e_id );

                    Boolean status = check_stmnt.execute();
                    System.out.println("Status"+status);



                    //message with complaint
                    String request_dialogue = "";
                    for(int i=3; i<input_token.length;i++)
                        request_dialogue += input_token[i];

                    PreparedStatement stmt = con.prepareStatement("INSERT INTO Complaint " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                    stmt.setString(1, postId);
                    stmt.setString(2, e_id);
                    stmt.setString(3, senderId);
                    stmt.setString(4, input_token[1]);
                    stmt.setString(5, request_dialogue);
                    stmt.setInt(6, 0);

                    java.sql.Timestamp ts= new java.sql.Timestamp(new java.util.Date().getTime());
                    stmt.setTimestamp(7,ts);
                    stmt.setString(8, "");

                    //update complaint entry
                    int st = stmt.executeUpdate();
                    if(st>=1)
                    {System.out.println("Complaint registered successfully!!!");
                        return 1;
                    }
                    else
                    {System.out.println("Complaint registeration failed!");
                        return 0;
                    }

                }
                return 0;
                //System.out.println("clsoed");
            }

            //con.close();
        }
        catch(Exception e){ System.out.println(e);
            return 0;}
    }
}
