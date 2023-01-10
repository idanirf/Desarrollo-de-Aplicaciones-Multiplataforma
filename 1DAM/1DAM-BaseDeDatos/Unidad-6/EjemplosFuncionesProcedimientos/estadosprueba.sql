DELIMITER $$
DROP FUNCTION IF EXISTS estado$$
CREATE FUNCTION estado(estado CHAR(1))
	RETURNS VARCHAR(20)
BEGIN
	DECLARE rtdo VARCHAR(20);

	IF estado='P' THEN SET rtdo='caducado';
	ELSEIF estado='O' THEN SET rtdo='activo';
	ELSEIF estado='N' THEN SET rtdo='nuevo';
	END IF;

	RETURN (rtdo);

END;$$