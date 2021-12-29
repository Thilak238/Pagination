package com.pagination.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pagination.entity.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

	@Query(value = "SELECT * FROM employee ORDER BY employee.name LIMIT 3",nativeQuery=true)
	List<Employee> find();
	
	@Query(value = "SELECT e FROM Employee e WHERE e.name LIKE %?1%")
	Page<Employee> findBySearch(String search,Pageable pageable);
}
