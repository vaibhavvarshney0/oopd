public class Req
{
    int mobile;
    String username;
    String groupname;
    int group_index;
    String address;
    int user_index;

    public int getUser_index() {
        return user_index;
    }

    public void setUser_index(int user_index) {
        this.user_index = user_index;
    }

    public int getGroup_index() {
        return group_index;
    }

    public void setGroup_index(int group_index) {
        this.group_index = group_index;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    Req(){}
    Req(String username, String groupname)
    {
//        this.setMobile(mobile);
        this.setUsername(username);
        this.setGroupname(groupname);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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


