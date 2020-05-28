package com.limingda.spring.springbootsecurity.spring.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Role_Resource_Relation")
public class RoleResourceRelation {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;
    private Long roleId;
    private Long resourceId;

}
