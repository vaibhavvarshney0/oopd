import java.util.ArrayList;
import java.util.Random;

public class Admin extends Parent
{
    ArrayList<Group> group = new ArrayList<>();
    ArrayList<Message> messages = new ArrayList<>();
    ArrayList<Req> requests = new ArrayList<>();

    public ArrayList<Req> getRequests() {
        return requests;
    }

    public void setRequests(Req requests) {
        this.requests.add(requests);
    }

    public ArrayList<Group> getGroup() {
        return group;
    }

    Admin()
    {
        int id = Management.count;
        this.setMobile(id);
        this.setUsername("admin"+Integer.toString(id));
        Management.count++;
    }

    Group createGroup(String groupname)
    {
        Group g = new Group();
        g.setName(groupname);
        g.setGroupId(new Random().nextInt());
        g.setAdminusername(this.getUsername());
        group.add(g);

        return g;
    }

    boolean isValid(String groupname)
    {
        return true;
    }

    Group addToGroup(String username, String groupname)
    {
        Group g = null;
        for(Group i:group)
        {
            if(i.getName().compareTo(groupname) == 0)
            {
                System.out.println("yes!!");
                g = i;
//                System.out.println(g.getName());
                break;
            }
        }

        int index = group.indexOf(g);

//        System.out.println(index);

        g.setUsers(username);
        group.set(index, g);
        System.out.println("cool");
        return g;
    }

    Group removeuser(String username, String groupname)
    {
        Group g = null;

        for(Group i : group)
        {
            if(i.getName().compareTo(groupname) == 0)
            {
                g = i;
                break;
            }
        }
        int index = group.indexOf(g);
        g.users.remove(username);
        group.set(index, g);

        return g;
    }

    Message sendMessage(String[] message)
    {
        Message m = new Message();
        m.setUsername(message[1]);
        m.setGroupname(message[2]);
        String mes = "";
        for(int i=3 ; i<message.length;i++)
           mes+=message[i]+" ";

        m.setMessage(mes);
        return m;
    }

    Group reply(String[] input, Group g)
    {
        Message m = g.messages.get(Integer.parseInt(input[3]));
        String mes = "";

        for(int i=4;i<input.length;i++)
            mes+=input[i]+" ";

        mes += " @replied by "+ input[1];
        m.reply = mes;

        g.messages.set(Integer.parseInt(input[3]), m);

        return g;
    }

    void view(String[] input, Group g)
    {
        System.out.println();
        System.out.println("--------------------"+ g.getName() +"-------------------------");
        System.out.println(String.format("| %-20s | %-20s | %s" , "user ", "message" , "reply"));
        System.out.println();

        for(int i=0;i<g.messages.size();i++)
        {
            Message m =g.messages.get(i);
            System.out.println(String.format("| %-20s | %-20s | %s" , m.username, m.message , m.reply));
        }

        System.out.println("---------------------------------------------");
    }
}
