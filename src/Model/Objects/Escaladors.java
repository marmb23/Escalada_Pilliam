package Model.Objects;

public class Escaladors {
    public String nom;
    public String alies;
    public Integer edat;
    public String nivell;
    public String estil_preferit;
    public String fita;
    public Integer viamaxim_id;

    public Escaladors(String nom, String alies, Integer edat, String nivell, String estil_preferit, String fita, Integer viamaxim_id) {
        this.nom = nom;
        this.alies = alies;
        this.edat = edat;
        this.nivell = nivell;
        this.estil_preferit = estil_preferit;
        this.viamaxim_id = viamaxim_id;
        this.fita = fita;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAlies() {
        return alies;
    }

    public void setAlies(String alies) {
        this.alies = alies;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public String getNivell() {
        return nivell;
    }

    public void setNivell(String nivell) {
        this.nivell = nivell;
    }

    public String getEstil_preferit() {
        return estil_preferit;
    }

    public void setEstil_preferit(String estil_preferit) {
        this.estil_preferit = estil_preferit;
    }

    public String getFita() {
        return fita;
    }

    public void setFita(String fita) {
        this.fita = fita;
    }

    public Integer getViaMaxim_id() {
        return viamaxim_id;
    }

    public void setViaMaxim_id(Integer viamaxim_id) {
        this.viamaxim_id = viamaxim_id;
    }

    @Override
    public String toString() {
        return "Escaladors{" +
                "nom='" + nom + '\'' +
                ", alies='" + alies + '\'' +
                ", edat=" + edat +
                ", nivell='" + nivell + '\'' +
                ", estil_preferit='" + estil_preferit + '\'' +
                ", fita='" + fita + '\'' +
                ", viamaxim_id=" + viamaxim_id +
                '}';
    }
}