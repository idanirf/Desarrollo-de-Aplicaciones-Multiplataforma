package es.drodriguez.com;

public class CreatorRandom {

    public static int edadAleatorio(){
        int probabilidad = (int)(Math.random()*100);
        if (probabilidad <= 50){
            //25-18 = 7 intervalo de edades aleatorias a asignar
            return 18 + (int)(Math.random()*7);
        }else if (probabilidad <= 80){
            return 25 + (int)(Math.random()*7);
        } else {
            return 32 + (int)(Math.random()*7);
        }
    }

    private static String[] nameBoysAleatorios={"Daniel", "José Luis", "José Manuel",
            "Juan", "Miguel", "Manuel", "Javier", "Carlos", "Jesus"};


    private static String[] nameGirlsAleatorios={"Mamen", "Isabel", "Pilar",
            "Raquel", "Mari Mar", "Mercedes", "Maria Dolores", "Dolores"};

    private static String[] personasApellidos={"Rodríguez","Fernández", "García", "López", "Ortiz",
                "González", "Sánchez", "Muñoz", "Heredia", "Alvarez", "Torres", "Molina",  "Serrano", "Marin", "Ramos", "Gil",
                "Vázquez", "Diaz", "Romero"};


    public static String nameGrilsAleatorios(){
        int probabilidad = (int) (Math.random()* nameGirlsAleatorios.length);
        return nameGirlsAleatorios[probabilidad];
    }

    public static String nameBoysAleatorios(){
        int probabilidad = (int) (Math.random()* nameBoysAleatorios.length);
        return nameBoysAleatorios[probabilidad];
    }

    public static String personasApellidos(){
        int probabilidad = (int) (Math.random()*personasApellidos.length);
        return personasApellidos[probabilidad];
    }

}
