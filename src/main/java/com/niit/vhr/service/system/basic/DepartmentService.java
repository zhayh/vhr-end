package com.niit.vhr.service.system.basic;

import com.niit.vhr.mapper.DepartmentMapper;
import com.niit.vhr.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : zhayh
 * @date : 2020-5-9 21:07
 * @description :
 */
@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentByParentId(-1);
    }

    public void addDepartment(Department department) {
        department.setEnabled(true);
        departmentMapper.addDepartment(department);
    }

    public void deleteDepartmentById(Department department) {
        department.setEnabled(true);
        departmentMapper.deleteDepartment(department);
    }
}
