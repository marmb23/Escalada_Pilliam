package Model.Objects;

public class Sector {
    public String nom;
    public String coordenades;
    public String lloc;
    public String aproximacio;
    public int numero_vies;
    public String popularitat;
    public String restriccions;

    public Sector(String nom, String coordenades, String lloc, String aproximacio, int numero_vies, String popularitat, String restriccions) {
        this.nom = nom;
        this.coordenades = coordenades;
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

    public String getCoordenades() {
        return coordenades;
    }

    public void setCoordenades(String coordenades) {
        this.coordenades = coordenades;
    }

    public String getLloc() {
        return lloc;
    }

    public void setLloc(String lloc) {
        this.lloc = lloc;
    }

    public String getAproximacio() {
        return aproximacio;
    }

    public void setAproximacio(String aproximacio) {
        this.aproximacio = aproximacio;
    }

    public int getNumero_vies() {
        return numero_vies;
    }

    public void setNumero_vies(int numero_vies) {
        this.numero_vies = numero_vies;
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
        return "Sector{" +
                "nom='" + nom + '\'' +
                ", coordenades='" + coordenades + '\'' +
                ", lloc='" + lloc + '\'' +
                ", aproximacio='" + aproximacio + '\'' +
                ", numero_vies=" + numero_vies +
                ", popularitat='" + popularitat + '\'' +
                ", restriccions='" + restriccions + '\'' +
                '}';
    }
}
