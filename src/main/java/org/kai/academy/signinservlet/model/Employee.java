package org.kai.academy.signinservlet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name="employeeNumber", nullable=false, unique=true)
    private int employeeNumber;

    @Column(name="lastName")
    private String lastName;

    @Column(name="firstName")
    private String firstName;

    @Column(name="extension")
    private String extension;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="officeCode")
    private String officeCode;

    @Column(name="reportsTo")
    private Integer reportsTo;

    @Column(name="jobTitle")
    private String jobTitle;


}
