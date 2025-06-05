package système;

import personnes.Personne;
import personnes.identité.Genre;
import personnes.identité.Orientation;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Paramètres param = new Paramètres();
        List<Personne> population = new LinkedList<Personne>();

        for (int i = 1; i <= param.nombrePersonnes; i++) {
            population.add(new Personne());
        }

        for(Personne p : population) {
            System.out.println(p.toString());
        }

        System.out.println("Fin de la création du bassin de population");
        Personne one = new Personne();
        Personne two = new Personne();



        boolean avisOne = one.accordDatingVerbeux(two);
        boolean avisTwo = two.accordDatingVerbeux(one);

        brassageRomantique(population);
        évaluationCritique(population);

    }

    static void brassageRomantique(List<Personne> pp)
    {
        Paramètres param = new Paramètres();
        int nbCl = 0;
        Random aléatoire = new Random();
        LinkedList<Personne> ppEligibles = new LinkedList<Personne>();
        for (Personne p : pp)
        {
            ppEligibles.add(p);
        }

        int nbPairings = pp.size();
        for (int i = 0; i< nbPairings*1.33; i++) {
            if(ppEligibles.size()>0)
            {
                Personne p1; Personne p2;
                int indice = aléatoire.nextInt(ppEligibles.size());
                int redondanceTrip = 0;
                while (!ppEligibles.get(indice).getCélibataire() && redondanceTrip < 12)
                {
                    indice = aléatoire.nextInt(ppEligibles.size());
                    redondanceTrip++;
                }
                p1 = ppEligibles.get(indice);
                redondanceTrip =0;
                indice = aléatoire.nextInt(ppEligibles.size());
                while (!ppEligibles.get(indice).getCélibataire() && redondanceTrip < 50)
                {
                    indice = aléatoire.nextInt(pp.size());
                    redondanceTrip++;
                }
                p2 = ppEligibles.get(indice);
                if (p1.getCélibataire() && p2.getCélibataire())
                {
                    nbCl++;
                    if(p1 != p2) {
                        boolean yes1 = p1.accordDating(p2);
                        boolean yes2 = p2.accordDating(p1);
                        if (yes1 && yes2)
                        {
                            p1.dating(p2);
                            ppEligibles.remove(p1);
                            ppEligibles.remove(p2);
                        }
                        if (yes1 && !yes2)
                            System.out.println(p2.prendrePrénom() + " n'a pas voulu de " + p1.prendrePrénom() + " ... </3");
                        if (!yes1 && yes2)
                            System.out.println(p1.prendrePrénom() + " n'a pas voulu de " + p2.prendrePrénom() + " ... </3");
                        if (!yes1 && !yes2)
                            System.out.println(p2.prendrePrénom() + " et " + p1.prendrePrénom() + " ne peuvent pas se voir en peinture!");
                    }

                }


            }
            }
        System.out.println("Brassage terminé, nombre d'essais romantiques = "+ nbCl + " pour une population de " + pp.size() + " personnes.");
    }

    static void évaluationCritique(List<Personne> pp)
    {
        System.out.println("Bilan de la population :");
        System.out.println("Nombre de personnes :" + pp.size());
        int nbHom = 0, nbFem = 0, nbEnby = 0;
        int gyno = 0, andro = 0, pan = 0;
        int nbCelib = 0, nbRomanced = 0;
        int hommeCouple = 0, femmeCouple = 0, nbCouple = 0;
        int androCouple = 0, gynoCouple = 0, panCouple = 0;
        int hommeHeteroCouple = 0, hommeGayCouple = 0, hommePanCouple = 0;
        int femmeHeteroCouple = 0, femmeGayCouple = 0, femmePanCouple = 0;
        int enbyAndroCouple = 0, enbyGynoCouple = 0, enbyPanCouple = 0;

        for(Personne p : pp)
        {
            if (p.getGenre() == Genre.M) nbHom++;
            if (p.getGenre() == Genre.F) nbFem++;
            if (p.getGenre() == Genre.NB) nbEnby++;
            if (p.getOrientation() == Orientation.ANDROSEXUEL) andro++;
            if (p.getOrientation() == Orientation.GYNOSEXUEL) gyno++;
            if (p.getOrientation() == Orientation.PANSEXUEL) pan++;
            if (p.getCélibataire()) nbCelib++;
            else nbRomanced++;


            if (p.getGenre() == Genre.M && !p.getCélibataire()) hommeCouple++;
            if (p.getGenre() == Genre.F && !p.getCélibataire()) femmeCouple++;
            if (p.getGenre() == Genre.NB && !p.getCélibataire()) nbCouple++;
            if (p.getOrientation() == Orientation.ANDROSEXUEL && !p.getCélibataire()) androCouple++;
            if (p.getOrientation() == Orientation.GYNOSEXUEL && !p.getCélibataire()) gynoCouple++;
            if (p.getOrientation() == Orientation.PANSEXUEL && !p.getCélibataire()) panCouple++;


            // HOMMES
            if (p.getGenre() == Genre.M && p.getOrientation() == Orientation.GYNOSEXUEL && !p.getCélibataire())
                hommeHeteroCouple++;
            if (p.getGenre() == Genre.M && p.getOrientation() == Orientation.ANDROSEXUEL && !p.getCélibataire())
                hommeGayCouple++;
            if (p.getGenre() == Genre.M && p.getOrientation() == Orientation.PANSEXUEL && !p.getCélibataire())
                hommePanCouple++;

// FEMMES
            if (p.getGenre() == Genre.F && p.getOrientation() == Orientation.ANDROSEXUEL && !p.getCélibataire())
                femmeHeteroCouple++;
            if (p.getGenre() == Genre.F && p.getOrientation() == Orientation.GYNOSEXUEL && !p.getCélibataire())
                femmeGayCouple++;
            if (p.getGenre() == Genre.F && p.getOrientation() == Orientation.PANSEXUEL && !p.getCélibataire())
                femmePanCouple++;

// PERSONNES NON BINAIRES
            if (p.getGenre() == Genre.NB && p.getOrientation() == Orientation.ANDROSEXUEL && !p.getCélibataire())
                enbyAndroCouple++;
            if (p.getGenre() == Genre.NB && p.getOrientation() == Orientation.GYNOSEXUEL && !p.getCélibataire())
                enbyGynoCouple++;
            if (p.getGenre() == Genre.NB && p.getOrientation() == Orientation.PANSEXUEL && !p.getCélibataire())
                enbyPanCouple++;
        }

        System.out.println(nbHom + " hommes, " + nbFem + " femmes, " + nbEnby + " autres personnes.");
        System.out.println(andro + " androsexuels, " + gyno + " gynosexuels, " + pan + " pansexuels.");
        System.out.println(nbCelib + " restés célibataires (hélas) " + "et " + nbRomanced + " ont trouvé l'amour et formé " + nbRomanced/2 + " couples.");

        // Répartition des personnes en couple par genre
        System.out.println(hommeCouple + " hommes en couple, " + femmeCouple + " femmes en couple, " + nbCouple + " personnes non-binaires en couple.");

// Répartition des personnes en couple par orientation
        System.out.println(androCouple + " androsexuels en couple, " + gynoCouple + " gynosexuels en couple, " + panCouple + " pansexuels en couple.");

// Répartition croisée genre + orientation (9 cas)
        if (nbHom != 0) {
            System.out.println(hommeHeteroCouple + " hommes hétéros en couple, soit " + (hommeHeteroCouple * 100.0 / nbHom) + "% de tous les hommes.");
            System.out.println(hommeGayCouple + " hommes gays en couple, soit " + (hommeGayCouple * 100.0 / nbHom) + "% de tous les hommes.");
            System.out.println(hommePanCouple + " hommes pansexuels en couple, soit " + (hommePanCouple * 100.0 / nbHom) + "% de tous les hommes.");
        } else {
            System.out.println("Aucun homme dans la population.");
        }

// FEMMES
        if (nbFem != 0) {
            System.out.println(femmeHeteroCouple + " femmes hétéros en couple, soit " + (femmeHeteroCouple * 100.0 / nbFem) + "% de toutes les femmes.");
            System.out.println(femmeGayCouple + " femmes lesbiennes en couple, soit " + (femmeGayCouple * 100.0 / nbFem) + "% de toutes les femmes.");
            System.out.println(femmePanCouple + " femmes pansexuelles en couple, soit " + (femmePanCouple * 100.0 / nbFem) + "% de toutes les femmes.");
        } else {
            System.out.println("Aucune femme dans la population.");
        }

// PERSONNES NON-BINAIRES
        if (nbEnby != 0) {
            System.out.println(enbyAndroCouple + " personnes autres androsexuelles en couple, soit " + (enbyAndroCouple * 100.0 / nbEnby) + "% des personnes NB.");
            System.out.println(enbyGynoCouple + " personnes autres gynosexuelles en couple, soit " + (enbyGynoCouple * 100.0 / nbEnby) + "% des personnes NB.");
            System.out.println(enbyPanCouple + " personnes autres pansexuelles en couple, soit " + (enbyPanCouple * 100.0 / nbEnby) + "% des personnes NB.");
        } else {
            System.out.println("Aucune personne non-binaire dans la population.");
        }

    }


        }
