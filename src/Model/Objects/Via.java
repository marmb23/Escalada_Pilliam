package Model.Objects;

public class Via {
    public String nom;
    public int llargada;
    public String dificultat;
    public String orientacio;
    public String estat;
    public String nom_escola;
    public String nom_sector;
    public String ancoratge;
    public String roca;
    public String creada_per;


    public Via(String nom, int llargada, String dificultat, String orientacio, String nom_escola, String estat, String nom_sector, String ancoratge, String roca, String creada_per) {
        this.nom = nom;
        this.llargada = llargada;
        this.dificultat = dificultat;
        this.orientacio = orientacio;
        this.nom_escola = nom_escola;
        this.estat = estat;
        this.nom_sector = nom_sector;
        this.ancoratge = ancoratge;
        this.roca = roca;
        this.creada_per = creada_per;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getLlargada() {
        return llargada;
    }

    public void setLlargada(int llargada) {
        this.llargada = llargada;
    }

    public String getDificultat() {
        return dificultat;
    }

    public void setDificultat(String dificultat) {
        this.dificultat = dificultat;
    }

    public String getOrientacio() {
        return orientacio;
    }

    public void setOrientacio(String orientacio) {
        this.orientacio = orientacio;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public String getNom_escola() {
        return nom_escola;
    }

    public void setNom_escola(String nom_escola) {
        this.nom_escola = nom_escola;
    }

    public String getNom_sector() {
        return nom_sector;
    }

    public void setNom_sector(String nom_sector) {
        this.nom_sector = nom_sector;
    }

    public String getAncoratge() {
        return ancoratge;
    }

    public void setAncoratge(String ancoratge) {
        this.ancoratge = ancoratge;
    }

    public String getRoca() {
        return roca;
    }

    public void setRoca(String roca) {
        this.roca = roca;
    }

    public String getCreada_per() {
        return creada_per;
    }

    public void setCreada_per(String creada_per) {
        this.creada_per = creada_per;
    }

    @Override
    public String toString() {
        return "Via{" +
                "nom='" + nom + '\'' +
                ", llargada=" + llargada +
                ", dificultat='" + dificultat + '\'' +
                ", orientacio='" + orientacio + '\'' +
                ", estat='" + estat + '\'' +
                ", nom_escola='" + nom_escola + '\'' +
                ", nom_sector='" + nom_sector + '\'' +
                ", ancoratge='" + ancoratge + '\'' +
                ", roca='" + roca + '\'' +
                ", creada_per='" + creada_per + '\'' +
                '}';
    }
}