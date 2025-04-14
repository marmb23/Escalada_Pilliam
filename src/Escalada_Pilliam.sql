CREATE DATABASE IF NOT EXISTS escalada_pilliam CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE escalada_pilliam;

CREATE TABLE escoles (
    id_escola INTEGER AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    municipi VARCHAR(100) NOT NULL,
    aproximacio TEXT,
    popularitat ENUM('baixa', 'mitjana', 'alta'),
    restriccions TEXT,
    num_vies INTEGER
);

CREATE TABLE IF NOT EXISTS sectors (
    id_sector INTEGER AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    coordenades VARCHAR(50) NOT NULL,
    aproximacio TEXT,
    popularitat ENUM('baixa', 'mitjana', 'alta'),
    restriccions TEXT,
    escola_id INTEGER NOT NULL,

    FOREIGN KEY (escola_id) REFERENCES escoles(id_escola)
);

CREATE TABLE IF NOT EXISTS escaladors (
    id_escalador INTEGER AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    alias VARCHAR(50) NOT NULL,
    edat INTEGER NOT NULL,
    nivell_maxim VARCHAR(3) NOT NULL,
    estil_preferit ENUM('esportiva', 'classica', 'gel'),
    fita TEXT,

    CHECK (nivell_maxim REGEXP '^[4-9][abcABC]?[+-]?$')
    );

CREATE TABLE IF NOT EXISTS vies (
    id_via INTEGER AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    llargada INT NOT NULL,
    grau_dificultat VARCHAR(3) NOT NULL,
    orientacio VARCHAR(2),
    tipus ENUM('esportiva', 'classica', 'gel') NOT NULL,
    estat ENUM('apte', 'construccio', 'tancada') NOT NULL DEFAULT 'apte',
    data_canvi_estat TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ancoratges VARCHAR(20),
    tipus_roca VARCHAR(20),
    creador_id INTEGER,
    escola_id INTEGER NOT NULL,
    sector_id INTEGER NOT NULL,

    CHECK (grau_dificultat REGEXP '^[4-9][abcABC][+-]?$'),
    CHECK (llargada > 0),

    UNIQUE (nom, sector_id),

    FOREIGN KEY (creador_id) REFERENCES escaladors(id_escalador) ON DELETE SET NULL,
    FOREIGN KEY (sector_id) REFERENCES sectors(id_sector),
    FOREIGN KEY (escola_id) REFERENCES escoles(id_escola)
);

ALTER TABLE escaladors
ADD COLUMN viamaxim_id INTEGER,
ADD FOREIGN KEY (viamaxim_id) REFERENCES vies(id_via) ON DELETE SET NULL;

CREATE TABLE IF NOT EXISTS historial (
    id_historial INTEGER AUTO_INCREMENT PRIMARY KEY,
    escalador_id INTEGER NOT NULL,
    via_id INTEGER NOT NULL,
    data DATE NOT NULL,

    FOREIGN KEY (escalador_id) REFERENCES escaladors(id_escalador),
    FOREIGN KEY (via_id) REFERENCES vies(id_via)
);

-- Triggers per mantenir el nombre de vies totals a les escoles:
DELIMITER //

-- després d'afegir una nova via, es suma 1 al total de vies que te l'escola
CREATE TRIGGER trg_after_insert_via
    AFTER INSERT ON vies
    FOR EACH ROW
BEGIN
    DECLARE escola_id INT;
    SELECT escola_id INTO escola_id
    FROM sectors
    WHERE id_sector = NEW.sector_id;

    UPDATE escoles
    SET num_vies = num_vies + 1
    WHERE id_escola = escola_id;
END;

-- quan s'elimina una via, es resta 1 al total de vies que te l'escola
CREATE TRIGGER trg_after_delete_via
    AFTER DELETE ON vies
    FOR EACH ROW
BEGIN
    DECLARE escola_id INT;
    SELECT escola_id INTO escola_id
    FROM sectors
    WHERE id_sector = OLD.sector_id;

    UPDATE escoles
    SET num_vies = num_vies - 1
    WHERE id_escola = escola_id;
END;

CREATE TRIGGER trg_check_via_estat
    BEFORE UPDATE ON vies
    FOR EACH ROW
BEGIN
    IF NEW.estat IN ('construccio', 'tancada') AND
       TIMESTAMPDIFF(DAY, OLD.data_canvi_estat, NOW()) >= 30 THEN
        -- s'actualitza a "apte" si han pasat 30 dies
        SET NEW.estat = 'apte';
        SET NEW.data_canvi_estat = NOW();  -- establir la nova data del canvi
    END IF;
END;

DELIMITER ;