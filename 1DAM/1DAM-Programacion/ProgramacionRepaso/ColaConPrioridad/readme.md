Cola Con Prioridad, SIN ELEMENTOS REPETIDOS

Proceso
int id
String nombre
int prioridad [1-9]

Test JUNIT
- Un id no puede estar repetido
- La prioridad tiene que estar entre 1 y 9

#ANOTACIONES
- Crear un aleatorio de los numeros de id que pueden ir del 1 al 100 por ejemplo así no se sale de ahí por lo tanto, pues en la cola solo van a poder haber 100 procesos
ya que el resto serían iguales y no se puede

- Crear un random de la prioridad que puede tener un proceso que es entre 1 y 9, el proceso que tenga mayor prioridad será el primero en entrar.

- El nombre del proceso por ahora lo dejo estático, hasta resolver el id y prioridad del proceso. Después podré crear una lista con nombres de procesos y que se asignen de forma aleatoria.

##En el controlador del programa/algoritmia del programa:
- Comprobar que el id del proceso no se encuentra ya dentro de la cola.
- Comprobar que la prioridad del proceso que va entrar es el mayor, forma de resolver con sort que me ordene los procesos de mayor a menor y después entre a la cola. En la clase POJO Proceso he creado un equals para ordenar, se puede implementar un compare o comparator.

