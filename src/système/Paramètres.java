package système;

public class Paramètres {
    int nombrePersonnes = 20000; // Le nombre de personnes créées dans la simulation //
    double probabilitéBiaisGenré = 0.66; // La probabilité qu'un biais genré affecte la valeur d'une Qualité donnée pour un individu genré, homme ou femme //
    double impactBiaisGenré = 1.0; // Coefficient qui augmente ou réduit l'impact du biais genré //
    double flexibilitéSexuelle = 0.05; // Coefficient qui influe la pénalité de compatibilité entre deux personnes d'orientation incompatible //
    double pénalitéDeRedondance = 0.35; // Coefficient qui influe la pénalité de compatibilité envers un partenaire déjà rencontré //
    double incidenceNB = 0.07; // Proportion de la population ne répondant au genre ni d'homme, ni femme //
    double exigence = 0.65; // Le seuil normal d'exigence amoureuse de la population //
    boolean débogage = false;
    int nombreDeCollisions = 60; // Nombre de fois où le brassage sera effectué //

    public int getNombreDeCollisions() {
        return nombreDeCollisions;
    }

    public boolean siDebug()
    {
        return débogage;
    }

    public void debug()
    {
        débogage = true;
    }

    public int prendreNombrePersonnes() {
        return nombrePersonnes;
    }

    public void réglerNombrePersonnes(int nombrePersonnes) {
        this.nombrePersonnes = nombrePersonnes;
    }

    public double prendreProbabilitéBiaisGenré() {
        return probabilitéBiaisGenré;
    }

    public void réglerProbabilitéBiaisGenré(double probabilitéBiaisGenré) {
        this.probabilitéBiaisGenré = probabilitéBiaisGenré;
    }

    public double prendreImpactBiaisGenré() {
        return impactBiaisGenré;
    }

    public void réglerImpactBiaisGenré(double impactBiaisGenré) {
        this.impactBiaisGenré = impactBiaisGenré;
    }

    public double prendreFlexibilitéSexuelle() {
        return flexibilitéSexuelle;
    }

    public void réglerFlexibilitéSexuelle(double flexibilitéSexuelle) {
        this.flexibilitéSexuelle = flexibilitéSexuelle;
    }

    public double prendrePénalitéDeRedondance() {
        return pénalitéDeRedondance;
    }

    public void réglerPénalitéDeRedondance(double pénalitéDeRedondance) {
        this.pénalitéDeRedondance = pénalitéDeRedondance;
    }

    public double prendreIncidenceNB() {
        return incidenceNB;
    }

    public void réglerIncidenceNB(double incidenceNB) {
        this.incidenceNB = incidenceNB;
    }

    public double prendreExigence() {
        return exigence;
    }

    public void réglerExigence(double exigence) {
        this.exigence = exigence;
    }
}
