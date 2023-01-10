package com.drodriguez.es.alumnosdam.services;

import java.io.IOException;
import java.nio.file.Path;

public interface StorageService<T> {
    boolean save(Path path, T item);
    T load(Path Path) throws IOException;
}
