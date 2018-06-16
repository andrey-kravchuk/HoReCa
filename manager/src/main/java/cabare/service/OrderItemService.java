package cabare.service;

import cabare.entity.model.OrderItem;

public interface OrderItemService {

  OrderItem findByDishId(Long dishId);
}
