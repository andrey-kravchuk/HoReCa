package cabare.service;


import cabare.entity.model.OrderCounter;
import cabare.repository.OrderCounterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderCounterService {

  private OrderCounter orderCounter;
  @Autowired
  private OrderCounterRepository orderCounterRepository;

  public Long nextOrderNumber() {
    if (orderCounter == null) {
      orderCounter = orderCounterRepository.findById(1L)
          .orElseGet(() -> orderCounterRepository.save(new OrderCounter()));
    }
    return orderCounterRepository.save(orderCounter.next()).getCounter();
  }
}
