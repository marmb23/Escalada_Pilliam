package Model.Objects;

public class Via {
    public String nom;
    public Integer llargada;
    public String dificultat;
    public String orientacio;
    public String tipus;
    public String estat;
    public String ancoratge;
    public String roca;
    public Integer creador_id;
    public Integer sector_id;

    public Via(String nom, Integer llargada, String dificultat, String orientacio, String tipus, String estat, String ancoratge, String roca, Integer creador_id, Integer sector_id) {
        this.nom = nom;
        this.llargada = llargada;
        this.dificultat = dificultat;
        this.orientacio = orientacio;
        this.tipus = tipus;
        this.estat = estat;
        this.ancoratge = ancoratge;
        this.roca = roca;
        this.creador_id = creador_id;
        this.sector_id = sector_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getLlargada() {
        return llargada;
    }

    public void setLlargada(Integer llargada) {
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

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
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

    public Integer getCreador_id() {
        return creador_id;
    }

    public void setCreador_id(Integer creador_id) {
        this.creador_id = creador_id;
    }

    public Integer getSector_id() {
        return sector_id;
    }

    public void setSector_id(Integer sector_id) {
        this.sector_id = sector_id;
    }

    @Override
    public String toString() {
        return "Via{" +
                "nom='" + nom + '\'' +
                ", llargada=" + llargada +
                ", dificultat='" + dificultat + '\'' +
                ", orientacio='" + orientacio + '\'' +
                ", tipus='" + tipus + '\'' +
                ", estat='" + estat + '\'' +
                ", ancoratge='" + ancoratge + '\'' +
                ", roca='" + roca + '\'' +
                ", creador_id=" + creador_id +
                ", sector_id=" + sector_id +
                '}';
    }

}