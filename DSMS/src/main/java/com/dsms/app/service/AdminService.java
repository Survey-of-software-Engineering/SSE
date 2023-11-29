package com.dsms.app.service;

import com.dsms.app.entity.Category;
import com.dsms.app.entity.CouponCode;
import com.dsms.app.entity.Department;
import com.dsms.app.entity.Item;
import com.dsms.app.models.CreateCategory;
import com.dsms.app.models.CreateItem;
import com.dsms.app.repository.CategoryRepository;
import com.dsms.app.repository.CouponCodeRepository;
import com.dsms.app.repository.DepartmentRepository;
import com.dsms.app.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

        Department tempDepartment = departmentRepository.getDepartmentByDepartmentName(department.getDepartmentName());
        if(tempDepartment == null) {
            return departmentRepository.save(department);
        }
        return tempDepartment;
    }

    public Category addCategory(CreateCategory category) {

        Department department = getDepartmentById(category.getDepartmentId());
        Category new_category = categoryRepository.save(category.getCategory());
        List<Category> categories = department.getCategories();
        if(categories == null) {
            categories = Arrays.asList(new_category);
        } else {
            categories.add(new_category);
        }
        department.setCategories(categories);
        departmentRepository.save(department);
        return new_category;
    }

    public Item addItem(CreateItem item) {

        Department department = getDepartmentById(item.getDepartmentId());
        Category category = getCategoryById(item.getCategoryId());
        System.out.println(category);
        Item new_item = itemRepository.save(item.getItem());
        List<Item> items = category.getItems();
        if(items == null) {
            items = Arrays.asList(new_item);
        } else {
            items.add(new_item);
        }
        category.setItems(items);
        categoryRepository.save(category);
        Optional<Category> category1 = department.getCategories().stream().filter(cat -> cat.getId().equals(category.getId())).findFirst();
        if(category1.isPresent()) {
            category1.get().setItems(items);
        }
        departmentRepository.save(department);
        return new_item;
    }

    public CouponCode addCoupon(CouponCode couponCode) {

        return couponCodeRepository.save(couponCode);
    }

    public List<Department> getDepartments() {

        return departmentRepository.getAllDepartments();
    }

    public List<Category> getCategories() {

        return categoryRepository.getAllCategories();
    }

    public List<Item> getItems() {

        return itemRepository.getAllItems();
    }

    public Department getDepartmentById(String id) {
        return departmentRepository.getDepartmentById(id);
    }

    public Category getCategoryById(String id) {
        return categoryRepository.getCategoryById(id);
    }

    public Item getItemById(String id) {
        return itemRepository.getItemByItemId(id);
    }
}