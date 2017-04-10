package com.order.Model;

import java.util.List;
import java.util.Set;

public interface OrderDAO_interface {
	public void insert(OrderVO orderVO);
	public void update(OrderVO orderVO);
	public void delete(Integer orderNum);
	public OrderVO findByPrimaryKey(Integer orderNum);
	public List<OrderVO> getAll();
}
