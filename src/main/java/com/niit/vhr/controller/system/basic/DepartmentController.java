package com.niit.vhr.controller.system.basic;

import com.niit.vhr.model.Department;
import com.niit.vhr.model.RespBean;
import com.niit.vhr.service.system.basic.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : zhayh
 * @date : 2020-5-9 21:05
 * @description :
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public RespBean getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return RespBean.ok("", departments);
    }

    @PostMapping("/")
    public RespBean addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
        if(department.getResult() == 1) {
            return RespBean.ok("添加成功", department);
        }
        return RespBean.error("添加失败！");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDepartment(@PathVariable Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentService.deleteDepartmentById(department);
        if(department.getResult() == -2) {
            return RespBean.error("该部门下有子部门，删除失败");
        } else if(department.getResult() == -1) {
            return RespBean.error("该部门下有员工，删除失败");
        } else if(department.getResult() == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
