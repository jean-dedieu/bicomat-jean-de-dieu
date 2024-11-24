package com.bicomat.repository;

import java.util.List;

public interface Repository<T> {
    T save(T entity);
    T findById(Long id);
    List<T> findAll();
    void delete(Long id);
}