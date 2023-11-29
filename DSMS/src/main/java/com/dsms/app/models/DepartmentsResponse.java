package com.dsms.app.models;

import com.dsms.app.entity.Department;
import com.dsms.app.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentsResponse {

    Department department;
    List<Item> items;
}
