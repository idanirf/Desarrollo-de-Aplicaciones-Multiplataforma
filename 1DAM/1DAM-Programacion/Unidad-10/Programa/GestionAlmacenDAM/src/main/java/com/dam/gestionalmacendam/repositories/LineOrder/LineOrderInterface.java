package com.dam.gestionalmacendam.repositories.LineOrder;

import com.dam.gestionalmacendam.repositories.CRUDRepository;
import com.dam.gestionalmacendam.repositories.SearchByUuid;

public interface LineOrderInterface<LineOrder, String> extends CRUDRepository<LineOrder, java.lang.String>, SearchByOrderBelongs<LineOrder, java.lang.String>, SearchByUuid<LineOrder, java.lang.String> {
}
