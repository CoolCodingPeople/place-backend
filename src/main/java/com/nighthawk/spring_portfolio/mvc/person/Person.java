package com.nighthawk.spring_portfolio.mvc.person;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import static javax.persistence.FetchType.EAGER;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/*
Person is a POJO, Plain Old Java Object.
First set of annotations add functionality to POJO
--- @Setter @Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
The last annotation connect to database
--- @Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDef(name="json", typeClass = JsonType.class)
public class Person {

    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // email, password, roles are key attributes to login and authentication
    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    @Email
    private String email;

    @NotEmpty
    private String password;

    // @NonNull, etc placed in params of constructor: "@NonNull @Size(min = 2, max = 30, message = "Name (2 to 30 chars)") String name"
    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Type(type="json")
    @Column(columnDefinition = "jsonb")
    private Map<String,Map<String, Object>> stats = new HashMap<>(); 
    

    // Constructor used when building object from an API
    public Person(String email, String password, String name, Date dob) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.dob = dob;
    }

    // A custom getter to return age from dob attribute
    public int getAge() {
        if (this.dob != null) {
            LocalDate birthDay = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return Period.between(birthDay, LocalDate.now()).getYears(); }
        return -1;
    }

    // Initialize static test data 
    public static Person[] init() {

        // basics of class construction
        Person p1 = new Person();
        p1.setName("Orlando Carcamo");
        p1.setEmail("orlando@gmail.com");
        p1.setPassword("123Orlando!");
        // adding Note to notes collection
        try {  // All data that converts formats could fail
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("12-08-2006");
            p1.setDob(d);
        } catch (Exception e) {
            // no actions as dob default is good enough
        }

        Person p2 = new Person();
        p2.setName("Shreyas Sarurkar");
        p2.setEmail("shreyas@gmail.com");
        p2.setPassword("123LexB!");
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("01-01-2000");
            p2.setDob(d);
        } catch (Exception e) {
        }

        Person p3 = new Person();
        p3.setName("Mati Danish");
        p3.setEmail("mati@gmail.com");
        p3.setPassword("123Mati!");
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("02-02-2000");
            p3.setDob(d);
        } catch (Exception e) {
        }

        Person p4 = new Person();
        p4.setName("Shivansh Goel");
        p4.setEmail("shivansh@gmail.com");
        p4.setPassword("123Shivansh!");
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("03-03-2000");
            p4.setDob(d);
        } catch (Exception e) {
        }

        Person p5 = new Person();
        p5.setName("Akshat Parikh");
        p5.setEmail("akshat@gmail.com");
        p5.setPassword("123Akshat!");
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("04-04-2000");
            p5.setDob(d);
        } catch (Exception e) {
        }

        // Array definition and data initialization
        Person persons[] = {p1, p2, p3, p4, p5};
        return(persons);
    }

    public static void main(String[] args) {
        // obtain Person from initializer
        Person persons[] = init();

        // iterate using "enhanced for loop"
        for( Person person : persons) {
            System.out.println(person);  // print object
        }
    }

}