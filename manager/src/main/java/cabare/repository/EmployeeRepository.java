package cabare.repository;

import cabare.entity.model.Cabare;
import cabare.entity.model.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Optional<Employee> findById(Long employeeId);

  Optional<Employee> findByEmail(String email);

  List<Employee> getAllByCabare(Cabare cabare);

}
