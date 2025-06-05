package personnes;

import personnes.identité.*;
import personnes.personnalité.Profil;
import personnes.personnalité.Qualité;
import système.Dé;
import système.Paramètres;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Personne {

    Paramètres param = new Paramètres();
    int âge;
    String prénom;
    String nom;
    Genre genre;
    Orientation orientation;
    Profil qualités;
    Personne partenaireRomantique = null;
    double exigence; double flexibilité;
    double impactBiais; Random aléatoire = new Random();
    Noms[] librairieNoms = Noms.values(); Dé dé = new Dé();
    List<Personne> personnesDéjàRencontrées = new LinkedList<Personne>();

    public Personne() {
        nom = ((char) ('A' + aléatoire.nextInt(26))) + ".";
        prénom = librairieNoms[aléatoire.nextInt(librairieNoms.length)].toString();
        âge = aléatoire.nextInt(15) + 18;

        // Détermination du genre : NB, homme, femme //
        if (dé.jetOuiNon(param.prendreIncidenceNB()))
        {
           genre = Genre.NB;
        }
        else
        {
            if (aléatoire.nextInt(100) <= 50) genre = Genre.M;
            else genre = Genre.F;
        }

        // ORIENTATION SEXUELLE //
        double probabilitéAndrosexuel = 0.25;
        double probabilitéGynosexuel = 0.25;
        double probabilitéPansexuel = 0.5;

        switch (genre) {
            case NB:
                // Nothing to see here ; default values //
                break;
            case M:
                probabilitéAndrosexuel = 0.15;
                probabilitéGynosexuel = 0.75;
                probabilitéPansexuel = 0.10;
                break;
            case F:
                probabilitéAndrosexuel = 0.7;
                probabilitéGynosexuel = 0.08;
                probabilitéPansexuel = 0.22;
                break;
        }

        // DETERMINATION DE L'ORIENTATION SEXUELLE //
        double srand = Math.random();

        if (srand < probabilitéAndrosexuel) {
            orientation = Orientation.ANDROSEXUEL;
        } else if (srand < probabilitéAndrosexuel + probabilitéGynosexuel) {
            orientation = Orientation.GYNOSEXUEL;
        } else {
            orientation = Orientation.PANSEXUEL;
        }

        if (orientation == null) orientation = Orientation.PANSEXUEL;


        qualités = new Profil();
        qualités.applicationBiais(genre);
    }

    public String toString()
    {
        String ID = "";
        ID = ID + prénom + " " + nom + " ";
        String type = "personne";
        if(genre == Genre.M) type = "homme";
        else if (genre == Genre.F) type = "femme";
        ID = ID + type + " de " + âge + " ans";
        ID = ID + " — " + qualités.toString();
        return ID;
    }


    // Essaie de dater une autre personne //
    public boolean accordDating(Personne p)
    {
        boolean accord = false;
        accord = dé.jetOuiNon(évaluerCompatibilitéAmoureuse(p));
        personnesDéjàRencontrées.add(p);
        return accord;
    }

    public boolean accordDatingVerbeux(Personne p)
    {
        boolean accord = false;
        accord = dé.jetOuiNon(évaluerCompatibilitéAmoureuseVerbeux(p));
        personnesDéjàRencontrées.add(p);
        return accord;
    }
    public double évaluerCompatibilitéAmoureuseVerbeux(Personne p)
    {
        // Valeur de départ //
        double compatibilitéAmoureuse = 0.1;
        String pre = this.prénom + " " + this.nom;
        String pres = p.prénom + " " + p.nom;

        System.out.println(pre + " rencontre " + pres + "et évalue sa compatibilité amoureuse. Compatibilité de départ : " + compatibilitéAmoureuse*100 + "%");


        Profil profilAutrui = p.prendreProfil();
        EnumMap<Qualité, Double> préférences = qualités.prendrePréférences();


        for(Qualité q : profilAutrui.prendreQualités().keySet())
        {
            double gainCompatibilité = profilAutrui.prendreQualités().get(q) * 0.006;
            gainCompatibilité = gainCompatibilité * préférences.get(q);
            compatibilitéAmoureuse = compatibilitéAmoureuse + gainCompatibilité;

                System.out.println("La qualité " + q + " de " + pres + ", qui vaut " + profilAutrui.prendreQualités().get(q) + " et lui confère une base de " + profilAutrui.prendreQualités().get(q) * 0.006 * 100 + " points de compatibilité.");
                System.out.println(pre + " a une affinité de " + préférences.get(q) + " pour la qualité " + q + ".");
                System.out.println("Ainsi " + pres + " gagne " + profilAutrui.prendreQualités().get(q) * 0.006 * 100 + " x " + préférences.get(q) + " = " +  gainCompatibilité * 100 + "% auprès de " + prénom + " au lieu de " + profilAutrui.prendreQualités().get(q) * 0.006 * 100 + "%");
                System.out.println("    [ Compatibilité so far : " + compatibilitéAmoureuse * 100 + "%]");
            }


        System.out.println(pres + " de genre " + p.genre + " et " + pre + " d'orientation " + orientation + "—");
        double coefOrientationCompatible = coefficientSexuel(p.genre);
        System.out.println("En raison de son orientation sexuelle, " + pre + " applique un multiplicateur de x" + coefficientSexuel(p.genre));


        System.out.println("Résultat final de compatibilité avec " + pres + " pour " + pre + ": " + compatibilitéAmoureuse * coefficientSexuel(p.genre)* 100 + "%");
        return compatibilitéAmoureuse * coefOrientationCompatible;
    }


    public double évaluerCompatibilitéAmoureuse(Personne p)
    {
        Boolean debug = false;


        // Valeur de départ //
        double compatibilitéAmoureuse = 0.1;

        Profil profilAutrui = p.prendreProfil();
        EnumMap<Qualité, Double> préférences = qualités.prendrePréférences();


        for(Qualité q : profilAutrui.prendreQualités().keySet())
        {
            double gainCompatibilité = profilAutrui.prendreQualités().get(q) * 0.006;
            gainCompatibilité = gainCompatibilité * préférences.get(q);
            compatibilitéAmoureuse = compatibilitéAmoureuse + gainCompatibilité;

            if (debug)
            {
                System.out.println("La qualité " + q + " de " + p.prénom + ", qui vaut " + profilAutrui.prendreQualités().get(q) + " lui confère en temps normal " + profilAutrui.prendreQualités().get(q) * 0.006 + " points de compatibilité.");
                System.out.println(prénom + " a une affinité de " + préférences.get(q) + " pour la qualité " + q + ".");
                System.out.println("Ainsi " + p.prénom + " gagne " + gainCompatibilité + " auprès de " + prénom + " au lieu de " + profilAutrui.prendreQualités().get(q) * 0.006);
                System.out.println("    [ Compatibilité so far : " + compatibilitéAmoureuse + "]");
            }
        }

        double coefOrientationCompatible = coefficientSexuel(p.genre);

        return compatibilitéAmoureuse * coefOrientationCompatible;
    }

    public Profil prendreProfil()
    {
        return qualités;
    }


    double coefficientSexuel(Genre g)
    {
        double coef = 1.0;
        if (orientation == Orientation.PANSEXUEL) return coef;
        else if (g == Genre.NB) return 0.4;

        if (orientation == Orientation.ANDROSEXUEL)
        {
            if(g == Genre.M) return coef;
            else return param.prendreFlexibilitéSexuelle();
        }

        else {
            if (g == Genre.F) return coef;
            else return param.prendreFlexibilitéSexuelle();
        }
    }

    public Genre getGenre() {
        return genre;
    }

    public boolean getCélibataire() {
        if (partenaireRomantique == null) return true;
        else return false;
    }

    public void dating(Personne datee)
    {
        datee.partenaireRomantique = this;
        this.partenaireRomantique = datee;
        System.err.println(prénom + " et " + datee.prénom + " sont maintenant amoureux! ♥");
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public String prendrePrénom()
    {
        return prénom;
    }
}

