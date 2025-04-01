package Model.Objects;

public class Escola {
    public String nom;
    public String lloc;
    public String aproximacio;
    public int numero_vies;
    public String popularitat;
    public String restriccions;

    public Escola(String nom, String lloc, String aproximacio, int numero_vies, String popularitat, String restriccions) {
        this.nom = nom;
        this.lloc = lloc;
        this.aproximacio = aproximacio;
        this.numero_vies = numero_vies;
        this.popularitat = popularitat;
        this.restriccions = restriccions;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLloc() {
        return lloc;
    }

    public void setLloc(String lloc) {
        this.lloc = lloc;
    }

    public int getNumero_vies() {
        return numero_vies;
    }

    public void setNumero_vies(int numero_vies) {
        this.numero_vies = numero_vies;
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
                ", lloc='" + lloc + '\'' +
                ", aproximacio='" + aproximacio + '\'' +
                ", numero_vies=" + numero_vies +
                ", popularitat='" + popularitat + '\'' +
                ", restriccions='" + restriccions + '\'' +
                '}';
    }
}