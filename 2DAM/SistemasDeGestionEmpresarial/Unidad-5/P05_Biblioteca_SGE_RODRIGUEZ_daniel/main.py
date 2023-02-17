from models.Libros import Libro
from models.Prestamo import Prestamo
from models.Usuarios import Usuario
usuarios = []
libros = []
prestamos = []
codigoPrestamo = 0


def altaSocio():
    print("Alta de socio")
    dni = input("Ingrese el DNI: ")
    nombre = input("Ingrese el nombre: ")
    correoElectronico = input("Ingrese el correo electrónico: ")
    telefono = input("Ingrese el teléfono: ")
    domicilio = input("Ingrese el domicilio: ")
    librosPrestados = input("Ingrese los libros prestados: ")
    usuario = Usuario(dni, nombre, correoElectronico, telefono, domicilio, librosPrestados)
    print(usuario)
    # Agregar usuario a la lista
    usuarios.append(usuario)
    print("Socio dado de alta")
    menu()
    pass


def bajaSocio():
    dni = input("Ingrese el DNI: ")
    for usuario in usuarios:
        if usuario.dni == dni:
            usuarios.remove(usuario)
            print("Socio dado de baja")
            menu()
            return
    print("Socio no encontrado")
    menu()

def altaLibro():
    print("Alta de libro")
    isbn = input("Ingrese el ISBN: ")
    titulo = input("Ingrese el título: ")
    autor = input("Ingrese el autor: ")
    genero = input("Ingrese el género: ")
    portada = input("Ingrese la portada: ")
    sinopsis = input("Ingrese la sinopsis: ")
    ejemplares = input("Ingrese los ejemplares: ")
    libro = Libro(isbn, titulo, autor, genero, portada, sinopsis, ejemplares)
    libros.append(libro)
    print(libro)
    print("Libro dado de alta")
    menu()
    pass


def bajaLibro():
    isbn = input("Ingrese el ISBN: ")
    for libro in libros:
        if libro.isbn == isbn:
            libros.remove(libro)
            print("Libro dado de baja")
            menu()
            return
    print("Libro no encontrado")
    menu()


def prestamoLibro():
    print("Prestamo de libro")
    codigoPrestamo = input("Ingrese el código de préstamo: ")
    isbn = input("Ingrese el ISBN: ")
    dni = input("Ingrese el DNI: ")
    fechaPrestamo = input("Ingrese la fecha de prestamo: ")
    fechaDevolucion = input("Ingrese la fecha de devolucion: ")
    prestamo = Prestamo(codigoPrestamo, isbn, dni, fechaPrestamo, fechaDevolucion)
    prestamos.append(prestamo)
    print(prestamo)
    print("Libro prestado")
    menu()


def devolucionLibro():
    codigoPrestamo = input("Ingrese el código de préstamo: ")
    for prestamo in prestamos:
        if prestamo.codigoPrestamo == codigoPrestamo:
            prestamos.remove(prestamo)
            print("Libro devuelto")
            menu()
            return
    print("Libro no encontrado")
    menu()

def consultaLibros():
    for libro in libros:
        print(libro)
    menu()
    pass



def consultaSocios():
    for usuario in usuarios:
        print(usuario)
    menu()

def consultaLibrosPrestados():
    for prestamo in prestamos:
        print(prestamo)
    menu()


def menu():
    print("Biblioteca")
    print("1. Alta socio")
    print("2. Baja socio")
    print("3. Alta libro")
    print("4. Baja libro")
    print("5. Prestamo de libro")
    print("6. Devolución de libro")
    print("7. Consulta de libros")
    print("8. Consulta de socios")
    print("9. Consulta de libros prestados")
    print("10. Salir")
    opcion = int(input("Ingrese una opción: "))
    if opcion == 1:
        altaSocio()
    elif opcion == 2:
        bajaSocio()
    elif opcion == 3:
        altaLibro()
    elif opcion == 4:
        bajaLibro()
    elif opcion == 5:
        prestamoLibro()
    elif opcion == 6:
        devolucionLibro()
    elif opcion == 7:
        consultaLibros()
    elif opcion == 8:
        consultaSocios()
    elif opcion == 9:
        consultaLibrosPrestados()
    elif opcion == 10:
        print("Hasta luego!")
        exit()
    else:
        print("Opción incorrecta")
        menu()
    return opcion

menu()














