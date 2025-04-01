package Model.Objects;

public class Escaladors {
    public String nom;
    public String alies;
    public int edat;
    public String nivell;
    public String nom_via;
    public String estil_preferit;
    public String historial;
    public String fita;

    public Escaladors(String nom, String alies, int edat, String nivell, String nom_via, String estil_preferit, String historial, String fita) {
        this.nom = nom;
        this.alies = alies;
        this.edat = edat;
        this.nivell = nivell;
        this.nom_via = nom_via;
        this.estil_preferit = estil_preferit;
        this.historial = historial;
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

    public String getNom_via() {
        return nom_via;
    }

    public void setNom_via(String nom_via) {
        this.nom_via = nom_via;
    }

    public String getEstil_preferit() {
        return estil_preferit;
    }

    public void setEstil_preferit(String estil_preferit) {
        this.estil_preferit = estil_preferit;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public String getFita() {
        return fita;
    }

    public void setFita(String fita) {
        this.fita = fita;
    }

    @Override
    public String toString() {
        return "Escaladors{" +
                "nom='" + nom + '\'' +
                ", alies='" + alies + '\'' +
                ", edat=" + edat +
                ", nivell='" + nivell + '\'' +
                ", nom_via='" + nom_via + '\'' +
                ", estil_preferit='" + estil_preferit + '\'' +
                ", historial='" + historial + '\'' +
                ", fita='" + fita + '\'' +
                '}';
    }
}