package cabare.service;


import cabare.repository.OrderCounterRepository;
import cabare.entity.model.Cabare;
import cabare.entity.model.OrderCounter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderCounterService {

  @Autowired
  private OrderCounterRepository orderCounterRepository;

  @Transactional
  public Long nextOrderNumber(Cabare cabare) {
    Optional<OrderCounter> counterOptional = orderCounterRepository.findByCabare(cabare);
    OrderCounter orderCounter;
    if (counterOptional.isPresent()) {
      orderCounter = counterOptional.get();
    } else {
      orderCounter = new OrderCounter();
      orderCounter.setCabare(cabare);
    }

    long next = orderCounter.next();
    orderCounterRepository.save(orderCounter);
    return next;
  }
}
