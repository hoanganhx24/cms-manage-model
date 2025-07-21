package com.example.cmsmanagemodel.repository.Specification;

import com.example.cmsmanagemodel.entity.Model;
import com.example.cmsmanagemodel.entity.Provider;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ModelSpecification {
    public static Specification<Model> build(String display_name, String provider_id ) {
        return hasDisplayName(display_name)
                .and(hasProviderId(provider_id));
    }

    public static Specification<Model> hasDisplayName(String display_name){
        return (root, query, cb)
                -> display_name == null ? null
                : cb.like(cb.lower(root.get("display_name")), "%" + display_name.toLowerCase() + "%");
    }

    public static Specification<Model> hasProviderId(String provider_id){
        return (root, query, cb) -> {
            if (provider_id == null){
                return null;
            }
            Join<Model, Provider> join = root.join("provider");
            String pattern = "%" + provider_id.toLowerCase() + "%";
            return cb.like(cb.lower(join.get("id")), pattern);
        };
    }
}
