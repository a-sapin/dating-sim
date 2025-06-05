package personnes.personnalité;

import personnes.identité.Genre;
import système.Dé;
import système.Paramètres;

import java.util.EnumMap;
import java.util.Random;

public class Profil {

    EnumMap<Qualité, Double> tableauQualités = new EnumMap<>(Qualité.class);
    EnumMap<Qualité, Double> tableauPréférences = new EnumMap<>(Qualité.class);
    private static final double QUAL_MIN = 0.0;
    private static final double QUAL_MAX = 10.0;
    private static final double PREF_MIN = 0.0;
    private static final double PREF_MAX = 4.0;
    private static final double MOYENNE = (QUAL_MIN+QUAL_MAX)/2;
    private static final double ECART_TYPE = 2.0;
    private static final Random aléatoire = new Random();
    private static final Dé dé = new Dé();
    private static final Paramètres param = new Paramètres();

    public Profil() {
        for (Qualité q : Qualité.values()) {
            // Génère une valeur suivant une loi normale tronquée entre QUAL_MIN et QUAL_MAX
            double valeur = générerValeurQualité(QUAL_MIN, QUAL_MAX, MOYENNE, ECART_TYPE);
            tableauQualités.put(q, valeur);
            double pref = générerValeurQualité(PREF_MIN, PREF_MAX, MOYENNE, ECART_TYPE-0.5);
            tableauPréférences.put(q, pref);
        }


    }

    private double générerValeurQualité(double MIN, double MAX, double MOY, double ECART) {
        double valeur;
        do {
            valeur = aléatoire.nextGaussian() * ECART + MOY;
        } while (valeur < MIN || valeur > MAX);
        return valeur;
    }


    public void applicationBiais(Genre g)
    {
        Biais b = new Biais(g);

        for (Qualité q : tableauQualités.keySet())
        {
            if(dé.jetOuiNon(param.prendreProbabilitéBiaisGenré())) {
                double pd = b.pondérationBiais(q);
                if (pd != 0) {
                    pd = pd * param.prendreImpactBiaisGenré();
                    tableauQualités.put(q, tableauQualités.get(q) + pd);
                    if (param.siDebug()) {
                        System.err.println("DEBUG : Biais de " + pd + " appliqué à qualité " + q);
                    }
                }
            }
        }

        for (Qualité q : tableauPréférences.keySet())
        {
            if(dé.jetOuiNon(param.prendreProbabilitéBiaisGenré())) {
                double pd = b.pondérationPréférence(q);
                if (pd != 0) {
                    pd = pd * param.prendreImpactBiaisGenré();
                    tableauPréférences.put(q, tableauPréférences.get(q) + pd);
                    if (param.siDebug()) {
                        System.err.println("DEBUG : Biais de " + pd + " appliqué à préférence " + q);
                    }
                }
            }
        }
    }

    public String toString()
    {
        String résultat = "";
        for(Qualité q : tableauQualités.keySet())
        {
            résultat = résultat + q + ": " + tableauQualités.get(q) + " ; ";
        }
        return résultat;
    }

    public EnumMap<Qualité, Double> prendreQualités()
    {
        return tableauQualités;
    }

    public EnumMap<Qualité, Double> prendrePréférences()
    {
        return tableauPréférences;
    }
}
