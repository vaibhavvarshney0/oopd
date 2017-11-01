import java.util.ArrayList;
import java.util.Random;

public class Team
{
    Random r=new Random();
    private ArrayList<Weapon> weapons=new ArrayList<>();
    private int score;

    public int getScore()
    {
        return score;
    }
    public void setScore(int score)
    {
        this.score = score;
    }

    Team()
    {
        this.setScore(0);
        weapons.add(new Rock(Math.abs((int)r.nextInt())%10000, "rock"));
        weapons.add(new Paper(Math.abs((int)r.nextInt())%10000, "paper"));
        weapons.add(new Scissor(Math.abs((int)r.nextInt())%10000, "scissor"));
    }

    void fight(Team t)
    {
        ArrayList<Weapon> temp_weapon_1=new ArrayList<>();
        temp_weapon_1.addAll(this.weapons);
        ArrayList<Weapon> temp_weapon_2=new ArrayList<>();
        temp_weapon_2.addAll(t.weapons);
        int count1=0;
        int count2=0;

        for(int i=0;i<=2;i++)
        {
            int r1=r.nextInt(3-i);
            int r2=r.nextInt(3-i);

            Weapon w1=temp_weapon_1.get(r1);
            Weapon w2=temp_weapon_2.get(r2);

            int outcome=0;

            if(w1 instanceof Rock)
            {
                w1=(Rock)w1;

                if(w2 instanceof Rock)
                    outcome=((Rock) w1).battle((Rock)w2);
                else if(w2 instanceof Paper)
                    outcome=((Rock) w1).battle((Paper)w2);
                else
                    outcome=((Rock) w1).battle((Scissor)w2);
            }

            else if(w1 instanceof Paper)
            {
                w1=(Paper)w1;

                if(w2 instanceof Rock)
                    outcome=((Paper) w1).battle((Rock)w2);
                else if(w2 instanceof Paper)
                    outcome=((Paper) w1).battle((Paper)w2);
                else
                    outcome=((Paper) w1).battle((Scissor)w2);
            }

            if(w1 instanceof Scissor)
            {
                w1=(Scissor)w1;

                if(w2 instanceof Rock)
                    outcome=((Scissor) w1).battle((Rock)w2);
                else if(w2 instanceof Paper)
                    outcome=((Scissor) w1).battle((Paper)w2);
                else
                    outcome=((Scissor) w1).battle((Scissor)w2);
            }

            if(outcome>0)
                count1++;
            else if(outcome<0)
                count2++;

            temp_weapon_1.remove(w1);
            temp_weapon_2.remove(w2);
        }

        if(count1>=2)
            this.setScore(this.getScore()+1);
        else if(count2>=2)
            t.setScore(t.getScore()+1);

    }
}
