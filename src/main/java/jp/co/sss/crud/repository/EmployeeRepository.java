package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.co.sss.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	Employee findByEmpIdAndEmpPass(int empId, String empPass);
	
	List<Employee> findByEmpNameContaining(String empName);
	
	@Query("SELECT e.userName FROM Employee e WHERE e.empId=:empId")
	List<Employee> findEmpName(Integer empId);
	
}
