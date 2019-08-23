package net.lka.onlineshoppingBackEnd.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.lka.onlineshoppingBackEnd.dao.CategoryDAO;
import net.lka.onlineshoppingBackEnd.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories=new ArrayList<>();
	
	static {
		
		Category category=new Category();
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is some descitpion for Television");
		category.setImageURL("ABC.jpg");
		categories.add(category);
		
		category=new Category();
		category.setId(2);
		category.setName("Laptop");
		category.setDescription("This is some descitpion for Laptop");
		category.setImageURL("DEF.jpg");
		categories.add(category);
		
		category=new Category();
		category.setId(3);
		category.setName("Mobile");
		category.setDescription("This is some descitpion for Mobile");
		category.setImageURL("GHI.jpg");
		categories.add(category);
		
	}
	
	@Override
	public List<Category> list() {
		return categories;
	}

	@Override
	public Category get(int id) {
		for(Category category: categories)
		{
			if(category.getId()==id)
				return category;
		}
		return null;
	}

}
