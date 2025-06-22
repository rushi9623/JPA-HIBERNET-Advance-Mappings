package com.luv2code.demo.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {
    // define fileds

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    // constructoors
    public Review() {

    }
    public Review(String comment){
        this.comment = comment;
    }

    // geters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    // to string

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }

    // annotate
}
