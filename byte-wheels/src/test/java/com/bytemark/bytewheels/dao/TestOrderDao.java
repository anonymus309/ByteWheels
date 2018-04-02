package com.bytemark.bytewheels.dao;

import org.junit.Test;

public class TestOrderDao {
	OrderDao dao = new OrderDaoImpl();
	@Test
	public void testConfirmOrder() throws DaoException{
		assert(dao.confirmOrder(1)==true);
	}

}
