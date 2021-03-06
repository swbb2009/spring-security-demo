package com.limingda.spring.springbootsecurity.spring.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class MyUser {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

}
