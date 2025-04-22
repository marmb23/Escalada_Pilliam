CREATE DATABASE escalada_pilliam;

CREATE TABLE escoles (
    id_escola SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    municipi VARCHAR(100) NOT NULL,
    aproximacio TEXT,
    popularitat VARCHAR(10),
    restriccions TEXT,
    num_vies INTEGER,

    CONSTRAINT escoles_popularitat_check CHECK (popularitat IN ('baixa', 'mitjana', 'alta'))
);

CREATE TABLE sectors (
    id_sector SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    coordenades VARCHAR(50) NOT NULL,
    aproximacio TEXT,
    popularitat VARCHAR(10),
    restriccions TEXT,
    num_vies INTEGER,
    escola_id INTEGER NOT NULL,

    CONSTRAINT sectors_popularitat_check CHECK (popularitat IN ('baixa', 'mitjana', 'alta')),
    FOREIGN KEY (escola_id) REFERENCES escoles(id_escola)
);

CREATE TABLE escaladors (
    id_escalador SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    alias VARCHAR(50) NOT NULL,
    edat INTEGER NOT NULL,
    nivell_maxim VARCHAR(3) NOT NULL,
    estil_preferit VARCHAR(10),
    fita TEXT,
    viamaxim_id INTEGER,

    CONSTRAINT valid_nivell_maxim CHECK (nivell_maxim ~ '^[4-9][abcABC]?[+-]?$'),
    CONSTRAINT escaladors_estil_preferit_check CHECK (estil_preferit IN ('esportiva', 'classica', 'gel'))
);

CREATE TABLE vies (
    id_via SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    llargada INTEGER NOT NULL,
    grau_dificultat VARCHAR(3),
    orientacio VARCHAR(5),
    tipus VARCHAR(20),
    estat VARCHAR(20) DEFAULT 'apte',
    data_canvi_estat TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ancoratges VARCHAR(50),
    temps_no_apte INTEGER DEFAULT 30,
    tipus_roca VARCHAR(50),
    creador_id INTEGER,
    sector_id INTEGER NOT NULL,
    CONSTRAINT vies_llargada_check CHECK (llargada > 0),
    CONSTRAINT grau_dificultat_check CHECK (grau_dificultat ~ '^[4-9]([abcABC])?[+-]?$'),
    CONSTRAINT vies_tipus_check CHECK (tipus IN ('esportiva', 'classica', 'gel')),
    CONSTRAINT vies_estat_check CHECK (estat IN ('construccio', 'tancada', 'apte'))
);

ALTER TABLE escaladors ADD FOREIGN KEY (viamaxim_id) REFERENCES vies(id_via) ON DELETE SET NULL;

CREATE TABLE historial (
    id_historial SERIAL PRIMARY KEY,
    escalador_id INTEGER NOT NULL,
    via_id INTEGER NOT NULL,
    data DATE NOT NULL,

    FOREIGN KEY (escalador_id) REFERENCES escaladors(id_escalador),
    FOREIGN KEY (via_id) REFERENCES vies(id_via)
);


CREATE OR REPLACE FUNCTION trg_after_delete_via() RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    UPDATE escoles
    SET num_vies = num_vies - 1
    WHERE id_escola = (SELECT escola_id FROM sectors WHERE id_sector = OLD.sector_id);
    RETURN OLD;
END;
$$;

CREATE OR REPLACE FUNCTION trg_after_insert_via() RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    UPDATE escoles
    SET num_vies = num_vies + 1
    WHERE id_escola = (SELECT escola_id FROM sectors WHERE id_sector = NEW.sector_id);
    RETURN NEW;
END;
$$;

CREATE OR REPLACE FUNCTION trg_after_insert_via_sector() RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    UPDATE sectors
    SET num_vies = num_vies + 1
    WHERE id_sector = NEW.sector_id;
    RETURN NEW;
END;
$$;

CREATE OR REPLACE FUNCTION trg_after_delete_via_sector() RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    UPDATE sectors
    SET num_vies = num_vies - 1
    WHERE id_sector = OLD.sector_id;
    RETURN OLD;
END;
$$;

CREATE OR REPLACE FUNCTION actualitzar_data_estat()
    RETURNS TRIGGER AS $$
BEGIN
    -- 1. Actualizar vías que ya deben estar en 'apte'
    UPDATE vies
    SET estat = 'apte'
    WHERE estat IN ('tancada', 'construccio')
      AND data_canvi_estat IS NOT NULL
      AND data_canvi_estat  + (temps_no_apte || ' minutes')::interval <= NOW();

    -- 2. Si la vía que se está insertando/actualizando ahora cambia a tancada o construccio,
    --    guardamos la fecha actual en data_estat
    IF TG_OP = 'UPDATE' AND NEW.estat IN ('tancada', 'construccio') AND (OLD.estat IS DISTINCT FROM NEW.estat) THEN
        NEW.data_canvi_estat  := CURRENT_TIMESTAMP;
    ELSIF TG_OP = 'INSERT' AND NEW.estat IN ('tancada', 'construccio') THEN
        NEW.data_canvi_estat  := CURRENT_TIMESTAMP;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_general_estats
    BEFORE INSERT OR UPDATE ON vies
    FOR EACH ROW
EXECUTE FUNCTION actualitzar_data_estat();

CREATE OR REPLACE TRIGGER trg_after_insert_via
    AFTER INSERT
    ON vies
    FOR EACH ROW
    EXECUTE PROCEDURE trg_after_insert_via();

CREATE OR REPLACE TRIGGER trg_after_delete_via
    AFTER DELETE
    ON vies
    FOR EACH ROW
    EXECUTE PROCEDURE trg_after_delete_via();

CREATE OR REPLACE TRIGGER after_insert_via_sector
    AFTER INSERT ON vies
    FOR EACH ROW
EXECUTE FUNCTION trg_after_insert_via_sector();

CREATE OR REPLACE TRIGGER after_delete_via_sector
    AFTER DELETE ON vies
    FOR EACH ROW
EXECUTE FUNCTION trg_after_delete_via_sector();

CREATE OR REPLACE TRIGGER check_via_estat
    BEFORE UPDATE ON vies
    FOR EACH ROW
EXECUTE FUNCTION trg_check_via_estat();