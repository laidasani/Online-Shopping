package net.lka.onlineshoppingBackEnd.dao;

import java.util.List;

import net.lka.onlineshoppingBackEnd.dto.Address;
import net.lka.onlineshoppingBackEnd.dto.Cart;
import net.lka.onlineshoppingBackEnd.dto.User;

public interface UserDAO {

	// user related operation
		User getByEmail(String email);
		User get(int id);

		boolean add(User user);
		
		// adding and updating a new address
		Address getAddress(int addressId);
		boolean addAddress(Address address);
		boolean updateAddress(Address address);
		Address getBillingAddress(int userId);
		List<Address> listShippingAddresses(int userId);
}
