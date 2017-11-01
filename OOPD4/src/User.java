import java.util.ArrayList;

public class User extends Parent
{
    public ArrayList<String> getGroups() {
        return groups;
    }

    public void setGroups(String groupname) {
        this.groups.add(groupname);
    }

    ArrayList<String> groups = new ArrayList<>();




    Message sendMessage(String[] message)
    {
        Message m = new Message();
        m.setUsername(message[1]);
        m.setGroupname(message[2]);
        String mes = "";
        for(int i=3 ; i<message.length;i++)
        {
            mes+=message[i]+" ";
        }

        m.setMessage(mes);

        return m;
    }

    void exitGroup(String groupname)
    {
        if(groups.contains(groupname)==false)
        {
            System.out.println("user is not present in this group!!!");
            return;
        }

        groups.remove(groupname);
    }

    Group reply(String[] input, Group g)
    {
        Message m = g.messages.get(Integer.parseInt(input[3]));
        if(m==null)
        {
            System.out.println("no such message available!!");
            System.out.println("------------------------------------");
            return null;
        }
        String mes = "";

        for(int i=4;i<input.length;i++)
        {
            mes+=input[i]+" ";
        }
        mes += " @replied by "+ input[1];
        m.reply = mes;

        g.messages.set(Integer.parseInt(input[3]), m);
        return g;
    }

    void view(String[] input, Group g)
    {
        System.out.println("--------------------"+ g.getName() +"-------------------------");
        System.out.println(String.format("| %-20s | %-20s | %s" , "user ", "message" , "reply"));
        System.out.println();

//        System.out.println(g.messages.get(0).message);
        for(int i=0;i<g.messages.size();i++)
        {
            Message m =g.messages.get(i);
            System.out.println(String.format("| %-20s | %-20s | %s" , m.username, m.message , m.reply));
        }
        System.out.println("---------------------------------------------");
    }
}
