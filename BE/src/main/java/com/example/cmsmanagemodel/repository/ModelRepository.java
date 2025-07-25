package com.example.cmsmanagemodel.repository;

import com.example.cmsmanagemodel.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, String>, JpaSpecificationExecutor<Model> {
}
