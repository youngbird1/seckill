package com.github.lyrric.test;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {

    private Integer page;

    private Integer size;

    private Integer number;

    private List<T> content;

}
