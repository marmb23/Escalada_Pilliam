package Model.Objects;

public class ViaGel extends Via {

    public ViaGel(String nom, Integer llargada, String dificultat, String orientacio,
                  String estat, String ancoratge, String roca,
                  Integer creador_id, Integer escola_id, Integer sector_id) {
        super(nom, llargada, dificultat, orientacio, "gel", estat, ancoratge, roca, creador_id, escola_id, sector_id);
    }
}