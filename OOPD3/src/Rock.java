public class Rock extends Weapon
{
    Rock(int strength, String name)
    {
        this.setStrength(strength);
        this.setName(name);
    }

    int battle(Weapon opponent) {
        int result = -1;
        if (this.getName().equals(opponent.getName()))
        {
            if (this.getStrength() > opponent.getStrength())
                result = 1;
            else if(this.getStrength()<opponent.getStrength())
                result = -1;
            else
                result=0;
        }
        else if (opponent.getName().equals("paper"))
        {
            int this_strength = this.getStrength() / 2;
            int opp_strength = opponent.getStrength() * 2;

            result=value_return(this_strength, opp_strength);
        }

        else if (opponent.getName().equals("scissor"))
        {
            int this_strength = this.getStrength() * 2;
            int opp_strength = opponent.getStrength() / 2;

            result=value_return(this_strength, opp_strength);
        }

        return result;
    }

}
