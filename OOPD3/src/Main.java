public class Main
{
    public static void main(String args[])
    {
        Team team1=new Team();
        Team team2=new Team();


        for(int i=0;i<20;i++)
        {
          team1.fight(team2);
        }

        System.out.println("team 1 : "+ team1.getScore());
        System.out.println("team 2 : "+team2.getScore());
    }
}
