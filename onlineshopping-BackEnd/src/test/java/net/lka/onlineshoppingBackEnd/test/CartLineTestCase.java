package net.lka.onlineshoppingBackEnd.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.lka.onlineshoppingBackEnd.dao.CartLineDAO;
import net.lka.onlineshoppingBackEnd.dao.ProductDAO;
import net.lka.onlineshoppingBackEnd.dao.UserDAO;
import net.lka.onlineshoppingBackEnd.dto.Cart;
import net.lka.onlineshoppingBackEnd.dto.CartLine;
import net.lka.onlineshoppingBackEnd.dto.Product;
import net.lka.onlineshoppingBackEnd.dto.User;

public class CartLineTestCase {
	
private static AnnotationConfigApplicationContext context;
	
	
	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	
	private Product product = null;
	private User user = null;
	private Cart cart = null;
	private CartLine cartLine = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.lka.onlineshoppingBackEnd");
		context.refresh();
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
	
	@Test
	public void testAddCartLine() {
		
		// fetch the user and then cart of that user
		User user = userDAO.getByEmail("rj@gmail.com");		
		Cart cart = user.getCart();
		
		// fetch the product 
		Product product = productDAO.get(1);
		
		// Create a new CartLine
		cartLine = new CartLine();

		cartLine.setProductCount(cartLine.getProductCount()+1);
		cartLine.setBuyingPrice(product.getUnitPrice());
			
		cartLine.setTotal(product.getUnitPrice() * cartLine.getProductCount());
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		
		cartLine.setProduct(product);
		
		assertEquals("Failed to add the CartLine!",true, cartLineDAO.add(cartLine));
		
		//update the cart
		cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
		cart.setCartLines(cart.getCartLines()+1);
		assertEquals("Failed to update the CartLine!",true, cartLineDAO.updateCart(cart));
	}
	
	
	
//	@Test
//	public void testUpdateCartLine() {
//
//		// fetch the user and then cart of that user
//		User user = userDAO.getByEmail("absr@gmail.com");		
//		Cart cart = user.getCart();
//				
//		cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), 2);
//		
//		cartLine.setProductCount(cartLine.getProductCount() + 1);
//				
//		double oldTotal = cartLine.getTotal();
//				
//		cartLine.setTotal(cartLine.getProduct().getUnitPrice() * cartLine.getProductCount());
//		
//		cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() - oldTotal));
//		
//		assertEquals("Failed to update the CartLine!",true, cartLineDAO.update(cartLine));	
//
//		
//	}
	
	

}
