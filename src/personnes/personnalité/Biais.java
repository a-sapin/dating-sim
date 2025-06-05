package personnes.personnalité;

import personnes.identité.Genre;

import java.util.EnumMap;
import java.util.Random;

public class Biais {

    // Liste des biais d'une personne en fonction du genre //

    EnumMap<Qualité, Double> biaisQualités = new EnumMap<>(Qualité.class);
    EnumMap<Qualité, Double> biaisPréférences = new EnumMap<>(Qualité.class);
    Random aléatoire = new Random();

    public Biais(Genre g)
    {
        if(g == Genre.M)
        {
            biaisQualités.put(Qualité.RICHESSE, 2.5);
            biaisQualités.put(Qualité.SANTÉ, 2.0);
            biaisQualités.put(Qualité.CHARISME, 1.0);
            biaisQualités.put(Qualité.INTELLIGENCE, 0.5);
            biaisQualités.put(Qualité.EMPATHIE, -1.0);


            biaisPréférences.put(Qualité.BEAUTÉ, 1.5);
            biaisPréférences.put(Qualité.SANTÉ, 0.5);
            biaisPréférences.put(Qualité.CHARISME, 0.5);
            biaisPréférences.put(Qualité.INTELLIGENCE, 0.5);

        }

        else if(g == Genre.F)
        {
            biaisQualités.put(Qualité.BEAUTÉ, 2.5);
            biaisQualités.put(Qualité.CHARISME, 1.75);
            biaisQualités.put(Qualité.EMPATHIE, 0.75);
            biaisQualités.put(Qualité.CULTURE, 0.5);
            biaisQualités.put(Qualité.SANTÉ, -0.25);

            biaisPréférences.put(Qualité.BEAUTÉ, 0.5);
            biaisPréférences.put(Qualité.SANTÉ, 1.5);
            biaisPréférences.put(Qualité.CHARISME, 2.5);
            biaisPréférences.put(Qualité.INTELLIGENCE, 0.75);
        }

    }

    double pondérationBiais(Qualité q)
    {
        double pondérant = 0;
        if (biaisQualités.containsKey(q)) {
            pondérant = biaisQualités.get(q);
            if (pondérant > 0) {
                pondérant = aléatoire.nextDouble(pondérant);
            }
            else {
                pondérant = pondérant * -1;
                pondérant = aléatoire.nextDouble(pondérant);
                pondérant = pondérant * -1;
            }


        }
        return pondérant;
    }


    double pondérationPréférence(Qualité q)
    {
        double pondérant = 0;
        if (biaisPréférences.containsKey(q)) {
            pondérant = biaisPréférences.get(q);
            if (pondérant > 0) {
                pondérant = aléatoire.nextDouble(pondérant);
            }
            else {
                pondérant = pondérant * -1;
                pondérant = aléatoire.nextDouble(pondérant);
                pondérant = pondérant * -1;
            }


        }
        return pondérant;
    }

}
