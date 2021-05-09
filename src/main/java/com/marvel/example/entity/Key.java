package com.marvel.example.entity;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class Key implements Serializable {

    @Column(name = "characters_id")
    private int charId;

    @Column(name = "comics_id")
    private int comId;
}
