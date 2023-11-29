package com.dsms.app.service;

import com.dsms.app.entity.Category;
import com.dsms.app.entity.CouponCode;
import com.dsms.app.entity.Department;
import com.dsms.app.entity.Item;
import com.dsms.app.repository.CategoryRepository;
import com.dsms.app.repository.CouponCodeRepository;
import com.dsms.app.repository.DepartmentRepository;
import com.dsms.app.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CouponCodeRepository couponCodeRepository;

    public Department addDepartment(Department department) {

        return departmentRepository.save(department);
    }

    public Category addCategory(Category category) {

        return categoryRepository.save(category);
    }

    public Item addItem(Item item) {

        return itemRepository.save(item);
    }

    public CouponCode addCoupon(CouponCode couponCode) {

        return couponCodeRepository.save(couponCode);
    }

    public List<Department> getDepartments() {

        return departmentRepository.getAllDepartments();
    }
}