package org.example.oenskelisten.Interface;

import org.example.oenskelisten.Model.User;

import java.util.List;

// Da vi vil få problemer med at begge repositories skal bruge dette,
// bruger vi dette som base interface
public interface IRepository<T> {
    List<T> getAll();
    T getById(int id);
    boolean edit(T newObject);
    void add(T newObject);
    void deleteById(int id);
}
