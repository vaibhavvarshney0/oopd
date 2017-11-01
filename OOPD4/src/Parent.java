import java.util.ArrayList;

public abstract class Parent
{
    String username;
    int mobile;
    String address;

    ArrayList<Integer> groupId = new ArrayList<>();

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public int getMobile()
    {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {

        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

//    Message sendMessage(String[] input)
//    {
//        Message m = new Message();
//        m.setUsername(input[1]);
//        m.setGroupname(input[2]);
//        String mes = "";
//        for(int i=3 ; i<input.length;i++)
//        {
//            mes+=input[i]+" ";
//        }
//        m.setMessage(mes);
//
//        return m;
//    }

    abstract Group reply(String[] input, Group g);
    abstract void view(String[] input, Group g);

}
