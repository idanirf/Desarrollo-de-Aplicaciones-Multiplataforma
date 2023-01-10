DELIMITER $$
DROP FUNCTION IF EXISTS diaSemana$$
CREATE FUNCTION diaSemana(numero int)
	RETURNS VARCHAR(10)
BEGIN
	DECLARE diaRes VARCHAR(10);

	CASE numero
		WHEN 1 THEN SET diaRes='Lunes';
		WHEN 2 THEN SET diaRes='Martes';
		WHEN 3 THEN SET diaRes='Miercoles';
		WHEN 4 THEN SET diaRes='Jueves';
		WHEN 5 THEN SET diaRes='Viernes';
		WHEN 6 THEN SET diaRes='Sabado';
		WHEN 7 THEN SET diaRes='Domingo';

		ELSE SET diaRes='error';
	END CASE;

	RETURN diaRes;
END; $$
