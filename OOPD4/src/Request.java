public class Request
{
    int mobile;
    String username;
    String groupname;
    int group_index;

    public int getGroup_index()
    {
        return group_index;
    }

    public void setGroup_index(int group_index)
    {
        this.group_index = group_index;
    }

    Request(String username, int mobile)
    {
        this.setMobile(mobile);
        this.setUsername(username);
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    Request(String username, int mobile, String groupname)
    {
        this.setMobile(mobile);
        this.setUsername(username);
        this.setGroupname(groupname);
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getMobile()
    {
        return mobile;
    }

    public void setMobile(int mobile)
    {
        this.mobile = mobile;
    }
}
