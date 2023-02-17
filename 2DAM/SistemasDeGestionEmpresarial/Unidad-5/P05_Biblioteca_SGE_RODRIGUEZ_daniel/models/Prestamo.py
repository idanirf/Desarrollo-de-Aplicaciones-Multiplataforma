# Clase Prestamos libro a usuario

class Prestamo:
    def __init__(self, codigoPrestamo, isbn, dni, fechaPrestamo, fechaDevolucion):
        # codigo prestamos autoincremental
        self.codigoPrestamo = codigoPrestamo
        self.isbn = isbn
        self.dni = dni
        self.fechaPrestamo = fechaPrestamo
        self.fechaDevolucion = fechaDevolucion

    def __str__(self):
        return f"Código de préstamo: {self.codigoPrestamo} - ISBN: {self.isbn} - DNI: {self.dni} - Fecha de préstamo: {self.fechaPrestamo} - Fecha de devolución: {self.fechaDevolucion}"