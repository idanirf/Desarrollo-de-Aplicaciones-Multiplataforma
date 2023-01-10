
package es.drodriguez.com.Utils;

import Models.Personas;

import java.util.Comparator;

public class PersonasNameComparator implements Comparator<Personas> {
    @Override
    public int compare(Personas o1, Personas o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

