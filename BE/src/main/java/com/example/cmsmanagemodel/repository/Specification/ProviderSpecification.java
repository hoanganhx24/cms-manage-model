package com.example.cmsmanagemodel.repository.Specification;

import com.example.cmsmanagemodel.entity.Provider;
import org.springframework.data.jpa.domain.Specification;

public class ProviderSpecification {
    public static Specification<Provider> build(String keyword){
        return hasKeyword(keyword);
    }

    public static Specification<Provider> hasKeyword(String keyword){
        return (root, query, cb) -> {
            if(keyword==null || keyword.isEmpty()){
                return null;
            }
            String pattern = "%"+keyword.toLowerCase()+"%";
            return cb.or(
                    cb.like(cb.lower(root.get("name")), pattern),
                    cb.like(cb.lower(root.get("baseUrl")), pattern)
            );
        };
    }
}
