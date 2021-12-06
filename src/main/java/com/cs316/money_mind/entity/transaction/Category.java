package com.cs316.money_mind.entity.transaction;

import com.cs316.money_mind.entity.Audit;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

/**
 * Categories
 *
 * @author Sainjargal Ishdorj
 **/

@Entity
@Table(name = "CATEGORIES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Category extends Audit {

    @Id
    @SequenceGenerator(name = "categorySeq", sequenceName = "CATEGORY_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "categorySeq")
    @Column(name = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "NAME")
    private String name;

}
