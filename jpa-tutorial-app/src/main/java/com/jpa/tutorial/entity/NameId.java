package com.jpa.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NameId implements Serializable {
    String courseName;
    int id;
}
