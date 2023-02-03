package com.wuriyanto.example.application.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.wuriyanto.example.application.entity.AbstractJpaEntity;

import jakarta.persistence.EntityManager;

import java.io.Serializable;

@NoRepositoryBean
public class GenericRepository<T extends AbstractJpaEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements IGenericRepository<T, ID> {

    protected Class<T> entity;

    private final EntityManager entityManager;
    private final JpaEntityInformation<T, ?> entityInformation;

    public GenericRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
    }

    @Override
    public boolean isDeleted(T model) {
        return false;
    }

    @Override
    public void remove(T model) {

    }
}
