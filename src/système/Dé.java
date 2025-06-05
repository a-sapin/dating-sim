package système;

import java.util.Random;

public class Dé {
    Random aléatoire = new Random();

    public boolean jetOuiNon(double probabilitéSuccès)
    {
        double résultat = aléatoire.nextInt(100+1);
        résultat = résultat / 100;
        Boolean renvoi = false;

        if (résultat <= probabilitéSuccès) renvoi = true;
        return renvoi;
    }
}
