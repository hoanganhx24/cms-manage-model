package com.example.cmsmanagemodel.repository;

import com.example.cmsmanagemodel.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, String>, JpaSpecificationExecutor<Provider> {
}
