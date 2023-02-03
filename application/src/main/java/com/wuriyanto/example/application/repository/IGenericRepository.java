package com.wuriyanto.example.application.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface IGenericRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    void delete(T deleted);

    Optional<T> findById(ID id);

    List<T> findAll();

    <S extends T> S save(S model);

    List<T> findAll(Sort orders);

    Page<T> findAll(Pageable pageable);


    /**
     * Custom method
     *
     */

    boolean isDeleted(T model);

    void remove(T model);

}
