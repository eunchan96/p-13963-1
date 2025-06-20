package com.back.domain.wiseSaying.wiseSaying.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
public class WiseSaying {
    int id;
    String content;
    String author;
}
