package cabare.service.impl;

import cabare.entity.model.OrderItem;
import cabare.exceptions.DishNotSpecifiedException;
import cabare.repository.OrderItemRepository;
import cabare.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

  @Autowired
  private OrderItemRepository orderItemRepository;

  @Override
  public OrderItem findByDishId(Long dishId) {
    if (dishId == null) {
      throw new DishNotSpecifiedException();
    }
    return orderItemRepository.findByDish_Id(dishId);
  }
}
