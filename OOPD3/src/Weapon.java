public class Weapon
{
    protected int strength;
    protected String name;

    public int getStrength()
    {
        return strength;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    Weapon()
    {
    }

    Weapon(int strength, String name)
    {
        this.setStrength(strength);
        this.setName(name);
    }

    int value_return(int this_strength, int opp_strength)
    {
        if (this_strength > opp_strength)
            return 1;
        else if (opp_strength > this_strength)
            return -1;
        else
            return 0;
    }
}
