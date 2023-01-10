DELIMITER $$
DROP FUNCTION IF EXISTS esimpar$$
CREATE FUNCTION esimpar(numero int)
	RETURNS int
BEGIN
	DECLARE impar INT;	
	IF MOD(numero,2)=0 THEN 
		SET impar=FALSE;
	ELSE SET impar=TRUE;
	END IF;
	RETURN(impar);
END; $$
