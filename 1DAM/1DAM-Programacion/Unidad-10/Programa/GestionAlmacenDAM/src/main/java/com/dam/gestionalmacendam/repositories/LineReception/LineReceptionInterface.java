package com.dam.gestionalmacendam.repositories.LineReception;

import com.dam.gestionalmacendam.models.LineReception;
import com.dam.gestionalmacendam.repositories.CRUDRepository;

public interface LineReceptionInterface extends CRUDRepository<LineReception, String>, SerachByReceptionsBelong<LineReception, String> {
}
