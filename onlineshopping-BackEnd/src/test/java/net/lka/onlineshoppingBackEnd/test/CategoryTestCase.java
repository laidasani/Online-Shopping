package net.lka.onlineshoppingBackEnd.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.lka.onlineshoppingBackEnd.dao.CategoryDAO;
import net.lka.onlineshoppingBackEnd.dto.Category;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;
	
	
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("net.lka.onlineshoppingBackEnd");
		context.refresh();
		
		categoryDAO=(CategoryDAO)context.getBean("categoryDAO");
	}
	
	/*
	 * @Test 
	 * public void testAddCategory() { 
	 * category=new Category();
	 * category.setName("Television");
	 * category.setDescription("This is some descitpion for Television");
	 * category.setImageURL("ABC.jpg");
	 * 
	 * assertEquals("Successfully added category",true,categoryDAO.add(category)); 
	 * }
	 */
	
	/*
	 * @Test
	 * public void testGetCategory() 
	 * { 
	 * Category category=categoryDAO.get(1);
	 * assertEquals("Successfully fetched single category from table","Television",category.getName()); 
	 * }
	 */
	
	/*
	 * @Test
	 * public void testUpdateCategory() 
	 * { 
	 * Category category=categoryDAO.get(1);
	 * category.setName("TV");
	 * assertEquals("Successfully updated single category from table",true,categoryDAO.update(category)); 
	 * }
	 */
	
	
	/*
	 * @Test 
	 * public void testDeleteCategory() 
	 * { 
	 * Category category=categoryDAO.get(1);
	 * assertEquals("Successfully deleted single category from table",true,categoryDAO.delete(category)); 
	 * }
	 */
	
	/*
	 * @Test 
	 * public void testListCategory() 
	 * {
	 * assertEquals("Successfully fetched list of categories from table",2,categoryDAO.list().size()); 
	 * }
	 */
	
	@Test
	public void testCRUDCategory()
	{
		//add operation
		 category=new Category();
		 category.setName("Television");
		 category.setDescription("This is some descitpion for Television");
		 category.setImageURL("ABC.jpg");
		 
		 assertEquals("Successfully added category",true,categoryDAO.add(category)); 

		 category=new Category();

		 category.setName("Laptop");
		 category.setDescription("This is some descitpion for laptop");
		 category.setImageURL("DEF.jpg");
		 
		 assertEquals("Successfully added category",true,categoryDAO.add(category)); 
		 
		 //fetching and updating the category
		 Category category=categoryDAO.get(1);
		 category.setName("TV");
		 assertEquals("Successfully updated single category from table",true,categoryDAO.update(category)); 
		 
		 //delete the category
		 assertEquals("Successfully deleted single category from table",true,categoryDAO.delete(category)); 
		 
		 //list of category
		 assertEquals("Successfully fetched list of categories from table",1,categoryDAO.list().size()); 
	}
}
