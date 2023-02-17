# Clase Libro


class Libro:
    def __init__(self, isbn, titulo, autor, genero, portada, sinopsis, ejemplares):
        self.isbn = isbn
        self.titulo = titulo
        self.autor = autor
        self.genero = genero
        self.portada = portada
        self.sinopsis = sinopsis
        self.ejemplares = ejemplares

    def __str__(self):
        return f"ISBN: {self.isbn} - Titulo: {self.titulo} - Autor: {self.autor} - Genero: {self.genero} - Portada: {self.portada} - Sinopsis: {self.sinopsis} - Ejemplares: {self.ejemplares}"
