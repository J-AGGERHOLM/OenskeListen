package org.example.oenskelisten.Interface;

public interface IWishRepository<T> {
    T getByName(String name);
    void add(T item);
    void deleteById(int id);
}
