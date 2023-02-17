# Clase Usuario

class Usuario:
    def __init__(self, dni, nombre, correoElectronico, telefono, domicilio, librosPrestados):
        self.dni = dni
        self.nombre = nombre
        self.correoElectronico = correoElectronico
        self.telefono = telefono
        self.domicilio = domicilio
        self.librosPrestados = librosPrestados

    def __str__(self):
        return f"DNI: {self.dni} - Nombre: {self.nombre} - Correo Electronico: {self.correoElectronico} - Telefono: {self.telefono} - Domicilio: {self.domicilio} - Libros Prestados: {self.librosPrestados}"
