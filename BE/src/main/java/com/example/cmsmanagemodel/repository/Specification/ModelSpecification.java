package com.example.cmsmanagemodel.repository.Specification;

import com.example.cmsmanagemodel.entity.Model;
import com.example.cmsmanagemodel.entity.Provider;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ModelSpecification {
    public static Specification<Model> build(String keyword, String providerId ) {
        return hasKeyword(keyword)
                .and(hasProviderId(providerId));
    }

    public static Specification<Model> hasProviderId(String providerId){
        return (root, query, cb) -> {
            if (providerId == null){
                return null;
            }
            Join<Model, Provider> join = root.join("provider");
            String pattern = "%" + providerId.toLowerCase() + "%";
            return cb.like(cb.lower(join.get("id")), pattern);
        };
    }

    public static Specification<Model> hasKeyword(String keyword){
        return (root, query, cb) -> {
            if (keyword == null){
                return null;
            }
            String pattern = "%" + keyword.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("modelCode")), pattern),
                    cb.like(cb.lower(root.get("displayName")), pattern)
            );
        };
    }
}
