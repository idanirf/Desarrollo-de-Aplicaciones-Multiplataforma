package com.dam.gestionalmacendam.repositories.Order;

import com.dam.gestionalmacendam.repositories.CRUDRepository;
import com.dam.gestionalmacendam.repositories.SearchByUuid;

public interface OrderInterface<Order, String> extends CRUDRepository, SearchByUuid {
}
