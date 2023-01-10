DELIMITER $$
DROP PROCEDURE IF EXISTS muestra_estado$$
CREATE PROCEDURE muestra_estado(in numero int)
BEGIN
	IF(esimpar(numero))THEN
		SELECT CONCAT(numero," es impar");
	ELSE
		SELECT CONCAT(numero," es par");
	END IF;
END;$$