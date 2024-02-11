package org.xomda.test;

import model.one.Customer;
import model.one.CustomerBean;
import model.two.Order;
import model.two.OrderBean;

public class MultiModel {

	public static void main(String[] args) {
		Customer customer = new CustomerBean();
		Order order = new OrderBean();
	}

}
