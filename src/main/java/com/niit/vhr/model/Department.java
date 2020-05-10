package com.niit.vhr.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Department {
    private Integer id;

    private String name;

    private Integer parentId;

    private String depPath;

    private Boolean enabled;

    private Boolean isParent;

    private List<Department> children = new ArrayList<>();

    private int result;
}
