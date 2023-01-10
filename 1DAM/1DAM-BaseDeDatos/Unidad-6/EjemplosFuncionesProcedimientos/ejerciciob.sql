DELIMITER $$
CREATE PROCEDURE ejerciciob(IN base int, in exp int, out potencia int)
	select pow(base,exp) into potencia;
$$