import java.util.ArrayList;

public class Group
{
    String name;
    int groupId;
    String adminusername;
    ArrayList<String> users = new ArrayList<>();
    ArrayList<Message> messages = new ArrayList<>();



    public String getAdminusername()
    {
        return adminusername;
    }

    public void setAdminusername(String adminusername)
    {
        this.adminusername = adminusername;
    }

    public int getGroupId()
    {
        return groupId;
    }

    public void setGroupId(int groupId)
    {
        this.groupId = groupId;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(String username) {
        this.users.add(username);
    }

    public void setName(String name)
    {
        this.name = name;
    }


    void remove(String username)
    {
        if(users.contains(username)== false)
        {
            System.out.println("user not present in this group!!!");
            return;
        }

        else
        {
            users.remove(username);
        }
    }
}
