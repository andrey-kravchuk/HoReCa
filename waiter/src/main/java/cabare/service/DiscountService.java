package cabare.service;

import cabare.data.DiscountRepository;
import cabare.entity.model.Discount;
import cabare.exception.DiscountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

  @Autowired
  private DiscountRepository discountRepository;


  public Discount findById(Long discountId) {
    return discountRepository.findById(discountId)
        .orElseThrow(() -> new DiscountNotFoundException());
  }
}
