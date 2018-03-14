package cabare.service;


import cabare.entity.model.OrderCounter;
import cabare.data.OrderCounterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderCounterService {

  private OrderCounter orderCounter;
  @Autowired
  private OrderCounterRepository orderCounterRepository;

  @Transactional
  public Long nextOrderNumber() {
    if (orderCounter == null) {
      orderCounter = orderCounterRepository.findById(1L)
          .orElseGet(() -> new OrderCounter());
    }
    return orderCounterRepository.save(orderCounter.next()).getCounter();
  }
}
