import java.util.ArrayList;
import java.util.Scanner;

public class Management
{
    Scanner sc = new Scanner(System.in);
    ArrayList<Admin> admins = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Group> groups = new ArrayList<>();
    static int count = 0;

    Admin masteradmin = new Admin();

    Management()
    {
        for(int i=0;i<5;i++)
        {
            admins.add(new Admin());
            System.out.println(admins.get(i).username);
        }
        System.out.println("---------------------------------------------");
    }

    void input(String line)
    {
        String[] input_token = line.split("\\s+");
        if(verify(input_token)<0)
            return;
//      exit
        if("exit".compareTo(input_token[0].toLowerCase()) == 0)
        {
            System.out.println("BYE BYE!!!");
            System.exit(0);
        }
//      creategroup adminname groupname
        else if("creategroup".compareTo(input_token[0].toLowerCase()) == 0)
        {
            if(verify(input_token)<0)
                return;
            int admin_index = admin_search(input_token[1]);
            if(admin_index < 0)
            {
                System.out.println("no such admin present!!!");
                System.out.println("---------------------------------------------");
                return;
            }
            Admin admin = admins.get(admin_index);
            Group updated = admin.createGroup(input_token[2]);
            groups.add(updated);
            admins.set(admin_index, admin);
            System.out.println("group created!!!");
            System.out.println(" group name ==== "+ input_token[2]);
            System.out.println("---------------------------------------------");
            return;
        }
//      addtogroup adminname username groupname
        else if("addtogroup".compareTo(input_token[0].toLowerCase()) == 0)
        {
            int admin_index = admin_search(input_token[1]);
            Admin admin = admins.get(admin_index);

            int user_index = user_search(input_token[2]);       //print not present
            int group_index = group_search(input_token[3]);

            if(verify(admin_index, user_index, group_index)<0)
                return;

//            System.out.println("user : "+ input_token[2]+ input_token[3]);
            Group updated = admin.addToGroup(input_token[2], input_token[3]);
            admins.set(admin_index, admin);

            groups.set(group_index, updated);

            User u = users.get(user_index);
            u.setGroups(input_token[3]);
            users.set(user_index, u);
            System.out.println("user added to group!!! ====== username : " + input_token[2] + " added by : "+ input_token[1] + " group : "+ input_token[3]);
            System.out.println("---------------------------------------------");
            return;
        }
//      deletegroup adminname groupname
        else if("deletegroup".compareTo(input_token[0].toLowerCase()) == 0)
        {
            int admin_index = admin_search(input_token[1]);
            int group_index = group_search(input_token[2]);

            if(admin_index < 0)
            {
                System.out.println("no such admin!!!");
                System.out.println("---------------------------------------------");
                return;
            }

            if(group_index < 0)
            {
                System.out.println("no such group!!!");
                System.out.println("---------------------------------------------");
                return;
            }

            Group g = groups.get(group_index);
            Admin a = admins.get(admin_index);

            if(a.getUsername().compareTo(g.getAdminusername()) == 0)
            {
                a.group.remove(g);
                groups.remove(g);

                for(int i=0;i<users.size();i++)
                {
                    User u = users.get(i);
                    u.groups.remove(g.getName());
                    users.set(i , u);
                }

                System.out.println("Group" + input_token[2] + "successfully deleted!!!");
                System.out.println("---------------------------------------------");
                return;
            }

            else
            {
                System.out.println("this is not admin of this group!!!");
                System.out.println("---------------------------------------------");
                return;
            }
        }

//        removeuser adminname username groupname
        else if("removeuser".compareTo(input_token[0].toLowerCase()) == 0)
        {
            int admin_index = admin_search(input_token[1]);
            int user_index = user_search(input_token[2]);
            int group_index = group_search(input_token[3]);

            if(verify(admin_index, user_index, group_index)<0)
                return;

            Admin admin = admins.get(admin_index);

            Group g = admin.removeuser(input_token[2], input_token[3]);
            admins.set(admin_index, admin);

            groups.set(group_index, g);

            users.remove(user_index);
            System.out.println("user" + input_token[2] + "has been removed!!!");
            System.out.println("---------------------------------------------");
            return;
        }

        //      verify adminname
        else if("verify".compareTo(input_token[0].toLowerCase()) == 0)
        {
            if(input_token[1].compareTo("masteradmin") == 0)
            {
                createUsers();
                return;
            }

            int admin_index = admin_search(input_token[1]);
            if(admin_index<0)
            {
                System.out.println("invalid admin!!!");
                System.out.println("---------------------------------------------");
                return;
            }
            Admin admin = admins.get(admin_index);

            for(int i=0;i<admin.requests.size();i++)
            {
                Req r = admin.requests.get(i);
                Group g = admin.addToGroup(r.getUsername(), r.getGroupname());
                groups.set(r.getGroup_index(), g);
                User u = users.get(r.getUser_index());
                u.setGroups(r.getGroupname());
                users.set(r.getUser_index(), u);

            }
            System.out.println("all pending requests of current admin verified!");
            System.out.println("---------------------------------------------");
            admin.requests.clear();
            admins.set(admin_index, admin);
            return;
        }

        //user functions
        //(5) createuser username mobile address
        else  if("createuser".compareTo(input_token[0].toLowerCase()) == 0)
        {
            Req r = new Req();
            r.setUsername(input_token[1]);
            r.setMobile(Integer.parseInt(input_token[2]));
            String add = "";
            for(int i = 3;i<input_token.length;i++)
            {
                add = add+input_token[i];
            }

            r.setAddress(add);
            masteradmin.setRequests(r);
            System.out.println("REQUEST SUBMITTED!!!");
            System.out.println("---------------------------------------------");
            return;
        }
//      requesttoadd username groupname
        else if("requesttoadd".compareTo(input_token[0].toLowerCase()) == 0)
        {
            int user_index = user_search(input_token[1]);
            int group_index = group_search(input_token[2]);

            if(verify(user_index, group_index)<0)
                return;

            Group g = groups.get(group_index);
            String adminusername = g.getAdminusername();

            int admin_index = admin_search(adminusername);
            Admin a = admins.get(admin_index);                                          //admin updated
            Req r = new Req(input_token[1], input_token[2]);
            r.setGroup_index(group_index);
            r.user_index = user_index;
            a.setRequests(r);
            admins.set(admin_index, a);      //gropu updated
            System.out.println("request submitted!!!");
            return;
        }

//        exit username groupname
        else if("exitgroup".compareTo(input_token[0].toLowerCase()) == 0)
        {
            int user_index = user_search(input_token[1]);
            int group_index = group_search(input_token[2]);

            if(verify(user_index, group_index)<0)
                return;

            Group g = groups.get(group_index);
            g.remove(input_token[1]);
            groups.set(group_index, g);

            int admin_index = admin_search(g.getAdminusername());
            Admin a = admins.get(admin_index);

            User u = users.get(user_index);
            u.exitGroup(input_token[2]);
            users.set(user_index, u);
            return;
        }

        //send username groupname message...
        else if("send".compareTo(input_token[0].toLowerCase()) == 0)
        {
            if(admin_search(input_token[1]) >=0)
            {
                int admin_index = admin_search(input_token[1]);
                int group_index = group_search(input_token[2]);

                if(verify(admin_index, group_index)<0)
                    return;

                Admin admin = admins.get(admin_index);

                Group g = groups.get(group_index);
                g.messages.add(admin.sendMessage(input_token));
                groups.set(group_index, g);
                System.out.println("message sent!!!");
                System.out.println("---------------------------------------------");
                return;
            }

            if(user_search(input_token[1]) >=0)
            {
                int user_index = user_search(input_token[1]);
                int group_index = group_search(input_token[2]);

                if(verify(user_index, group_index)<0)
                    return;
                User u = users.get(user_index);

                Group g = groups.get(group_index);
                g.messages.add(u.sendMessage(input_token));
                groups.set(group_index, g);
                System.out.println("message sent!!!");
                return;
            }

            System.out.println("invalid username!!!");
            return;
        }

//      reply username/adminname groupname messageid replymessage           //message index will be the message id
        else if("reply".compareTo(input_token[0].toLowerCase()) == 0)
        {
            int admin_index = admin_search(input_token[1]);
            int group_index = group_search(input_token[2]);
            int user_index = user_search(input_token[1]);

            if(group_index < 0)
            {
                System.out.println("no such group present!!!");
                return;
            }
            Group g = groups.get(group_index);

            if(admin_index < 0)
            {
                if(user_index<0)
                {
                    System.out.println("no entity present with this username!!!");
                    System.out.println("---------------------------------------------");
                    return;
                }

                User u = users.get(user_index);
                groups.set(group_index, u.reply(input_token, g));
                System.out.println("reply sent!!!");
                System.out.println("---------------------------------------------");
                return;
            }

            Admin a = admins.get(admin_index);
            groups.set(group_index, a.reply(input_token, g));
            System.out.println("reply sent!!!");
            return;
        }

        //    view username groupname
        else if("view".compareTo(input_token[0].toLowerCase()) == 0)
        {
            int user_index = user_search(input_token[1]);
            int admin_index = admin_search(input_token[1]);
            int group_index = group_search(input_token[2]);
            if(group_index < 0)
            {
                System.out.println("no such group present!!!");
                return;
            }
            Group g = groups.get(group_index);

            if(admin_index < 0)
            {
                if(user_index < 0)
                {
                    System.out.println("no such user present!!!");
                    return;
                }

                User u = users.get(user_index);
                if(u.getGroups().contains(g.getName()) == false)
                {
                    System.out.println("this user is not member of given group !!!");
                    System.out.println("---------------------------------------------");
                    return;
                }
                u.view(input_token, g);
                return;
            }

            Admin a = admins.get(admin_index);
//            if(a.getGroup().contains(g.getName()) == false)
//            {
//                System.out.println("this user is not member of given group !!!");
//                return;
//            }
            a.view(input_token, g);
            return;
        }

//      please view messages before share!!
//      share A group1_source group2_dest messageid
        else if("share".compareTo(input_token[0].toLowerCase()) == 0)
        {
            int user_index = user_search(input_token[1]);
            int g1_index = group_search(input_token[2]);
            int g2_index = group_search(input_token[3]);

            if(user_index< 0 )
            {
                System.out.println("user not present !!!");
                System.out.println("---------------------------------------------");
                return;
            }

            if(g1_index < 0 || g2_index < 0)
            {
                System.out.println("one or more group not present !!!");
                System.out.println("---------------------------------------------");
                return;
            }

            User u = users.get(user_index);
            Group g1 = groups.get(g1_index);
            Group g2 = groups.get(g2_index);

            if(g1.users.contains(u.getUsername()) && g2.users.contains(u.getUsername()))
            {
                Message m = g1.messages.get(Integer.parseInt(input_token[4]));
                m.setUsername(m.getUsername()+" (shared by "+ u.getUsername()+ ")");
                m.reply="";
                g2.messages.add(m);
                groups.set(g1_index, g1);
                groups.set(g2_index, g2);
            }

            else
            {
                System.out.println("user should be present in both groups !!!");
                System.out.println("---------------------------------------------");
                return;
            }
        }

        else
        {
            System.out.println("enter valid command!!!");
            System.out.println("---------------------------------------------");
            return;
        }
    }


    int admin_search(String adminname)
    {
        Admin a = null;
        for(Admin i: admins)
            if(i.getUsername().compareTo(adminname) ==0)
                return admins.indexOf(i);
        return -1;
    }

    int group_search(String groupname)
    {
        Group g = null;
        for(Group i:groups)
            if(i.getName().compareTo(groupname) == 0)
                return groups.indexOf(i);
        return -1;
    }

    int user_search(String username)
    {
        User u = null;
        for(User i : users)
            if(i.getUsername().compareTo(username) == 0)
                return users.indexOf(i);
        return -1;
    }

    void createUsers()
    {
        for(int i=0;i<masteradmin.requests.size();i++)
        {
            Req r = masteradmin.requests.get(i);
            User u = new User();

            if(user_search(r.getUsername())>=0)
            {
                System.out.println("this is already present!!! USER :"+ r.getUsername());
                System.out.println("---------------------------------------------");
                continue;
            }
            u.setUsername(r.getUsername());
            u.setMobile(r.getMobile());
            u.setAddress(r.getAddress());
            users.add(u);

            System.out.println("user added : "+ r.getUsername());
            System.out.println("---------------------------------------------");
        }
        masteradmin.requests.clear();

    }

    int verify(int a_index, int u_index, int g_index)
    {
        if(u_index<0)
        {
            System.out.println("user not present!!!");
            System.out.println("---------------------------------------------");
            return -1;//print not valid
        }

        if(a_index<0)
        {
            System.out.println("invalid admin and group!!!");
            System.out.println("---------------------------------------------");
            return -1;//print not valid
        }

        if(g_index <0)
        {
            System.out.println("group not present!!!");
            System.out.println("---------------------------------------------");
            return -1;
        }

        return 0;
    }

    int verify(int u_index, int g_index)
    {
        if(u_index<0)
        {
            System.out.println("invalid identity!!!");
            return -1;//print not valid
        }

        if(g_index <0)
        {
            System.out.println("invalid identity!!!");
            return -1;
        }

        return 0;
    }

    int verify(String[] input) {
        if (input.length < 2) {
            System.out.println("enter valid command!!!");
            System.out.println("---------------------------------------------");
            return -1;
        }
        return 0;
    }
}
