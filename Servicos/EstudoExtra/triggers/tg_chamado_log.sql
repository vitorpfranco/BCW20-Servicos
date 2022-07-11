USE servicosdb;

CREATE TABLE log_chamado
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    status        VARCHAR(255) NOT NULL,
    antigoStatus  VARCHAR(255) NOT NULL,
    idChamado     INT          NOT NULL,
    dataAlteracao DATE         NOT NULL
);

DELIMITER //

CREATE TRIGGER tg_chamado_log
    BEFORE UPDATE
    ON chamado
    FOR EACH ROW
BEGIN
    IF (OLD.status <> NEW.status) THEN
        INSERT INTO log_chamado VALUES (NULL, NEW.status, OLD.status, OLD.id_chamado, CURRENT_DATE());
    END IF;
END //

DELIMITER ;
