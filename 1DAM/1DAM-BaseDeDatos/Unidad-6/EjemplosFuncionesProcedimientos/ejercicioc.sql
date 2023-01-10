DELIMITER $$
CREATE PROCEDURE ejercicioc(inout cad varchar(10), out tam int)
BEGIN
	select upper (cad) into cad;
	select length(cad) into tam;
END; $$