package com.shop.Model;

import java.util.List;

public interface ShopDAO_interface {
	public void insert(ShopVO shopVO);

	public void update(ShopVO shopVO);

	public void delete(Integer shopId);

	public ShopVO findByPrimaryKey(Integer shopId);

	public List<ShopVO> getAll();
}
