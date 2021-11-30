package com.cs316.money_mind.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Audit
 *
 * @author Sainjargal Ishdorj
 **/


@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Audit {

    @CreatedBy
    @Column(name = "CREATED_BY", updatable = false)
    @JsonIgnore
    private String createdBy;

    @LastModifiedBy
    @Column(name = "MODIFIED_BY")
    @JsonIgnore
    private String modifiedBy;

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss.s")
    @JsonIgnore
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "MODIFIED_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss.s")
    @JsonIgnore
    private LocalDateTime modifiedDate;

    @Column(name = "CREATED_CID", updatable = false)
    @JsonIgnore
    private String createdCid;

    @Column(name = "MODIFIED_CID")
    @JsonIgnore
    private String modifiedCid;

}