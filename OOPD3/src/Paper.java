public class Paper extends Weapon {
    Paper(int strength, String name) {
        this.setStrength(strength);
        this.setName(name);
    }

    int battle(Weapon opponent) {
        int result = -1;
        if (this.getName().equals(opponent.getName()))
        {
            result = value_return(this.getStrength(), opponent.getStrength());
        }
        else if (opponent.getName().equals("scissor"))
        {
            int this_strength = this.getStrength() / 2;
            int opp_strength = opponent.getStrength() * 2;

            result = value_return(this_strength, opp_strength);
        }

        else if (opponent.getName().equals("rock"))
        {
            int this_strength = this.getStrength() * 2;
            int opp_strength = opponent.getStrength() / 2;

            result = value_return(this_strength, opp_strength);
        }
        return result;
    }
}