package Model.Objects;

public class Sector {
    public String nom;
    public String coordenades;
    public String aproximacio;
    public String popularitat;
    public String restriccions;
    public Integer escola_id;

    public Sector(String nom, String coordenades, String aproximacio, String popularitat, String restriccions, Integer escola_id) {
        this.nom = nom;
        this.coordenades = coordenades;
        this.aproximacio = aproximacio;
        this.popularitat = popularitat;
        this.restriccions = restriccions;
        this.escola_id = escola_id;
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

    public Integer getEscola_id() {
        return escola_id;
    }

    public void setEscola_id(Integer escola_id) {
        this.escola_id = escola_id;
    }

    @Override
    public String toString() {
        return "Sector{" +
                "nom='" + nom + '\'' +
                ", coordenades='" + coordenades + '\'' +
                ", aproximacio='" + aproximacio + '\'' +
                ", popularitat='" + popularitat + '\'' +
                ", restriccions='" + restriccions + '\'' +
                ", escola_id=" + escola_id +
                '}';
    }

}
