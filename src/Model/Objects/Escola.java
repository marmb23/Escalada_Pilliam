package Model.Objects;

public class Escola {
    public String nom;
    public String municipi;
    public String aproximacio;
    public String popularitat;
    public String restriccions;

    public Escola(String nom, String municipi, String aproximacio, String popularitat, String restriccions) {
        this.nom = nom;
        this.municipi = municipi;
        this.aproximacio = aproximacio;
        this.popularitat = popularitat;
        this.restriccions = restriccions;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMunicipi() {
        return municipi;
    }

    public void setMunicipi(String lloc) {
        this.municipi = municipi;
    }

    public String getAproximacio() {
        return aproximacio;
    }

    public void setAproximacio(String aproximacio) {
        this.aproximacio = aproximacio;
    }

    public String getPopularitat() {
        return popularitat;
    }

    public void setPopularitat(String popularitat) {
        this.popularitat = popularitat;
    }

    public String getRestriccions() {
        return restriccions;
    }

    public void setRestriccions(String restriccions) {
        this.restriccions = restriccions;
    }

    @Override
    public String toString() {
        return "Escola{" +
                "nom='" + nom + '\'' +
                ", lloc='" + municipi + '\'' +
                ", aproximacio='" + aproximacio + '\'' +
                ", popularitat='" + popularitat + '\'' +
                ", restriccions='" + restriccions + '\'' +
                '}';
    }
}