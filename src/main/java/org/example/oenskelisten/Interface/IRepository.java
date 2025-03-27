package org.example.oenskelisten.Interface;

import java.util.List;

public interface IRepository<T> {
    List<T> getAll();
    T getById(int id);
}
