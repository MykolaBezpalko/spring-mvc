package com.mbeza.springmvc.domain;

public interface DomainObject {
    Integer getId();
    void setId(Integer id);
    Integer getVersion();

    void setVersion(Integer version);
}
