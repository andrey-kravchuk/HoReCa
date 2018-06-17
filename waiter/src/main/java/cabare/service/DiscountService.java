package cabare.service;

import cabare.repository.DiscountRepository;
import cabare.entity.model.Cabare;
import cabare.entity.model.Discount;
import cabare.entity.model.Employee;
import cabare.exception.DiscountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

  @Autowired
  private DiscountRepository discountRepository;
  @Autowired
  private SecurityService securityService;


  public Discount findById(Long discountId) {
    Employee employee = securityService.getEmployeeFromSession();
    Cabare cabare = employee.getCabare();
    return discountRepository.findByIdAndCabare(discountId, cabare)
        .orElseThrow(() -> new DiscountNotFoundException());
  }
}
