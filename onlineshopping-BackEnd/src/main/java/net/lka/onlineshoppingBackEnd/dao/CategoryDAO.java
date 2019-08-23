package net.lka.onlineshoppingBackEnd.dao;

import java.util.List;

import net.lka.onlineshoppingBackEnd.dto.Category;

public interface CategoryDAO {

	List<Category> list();

	Category get(int id);
}
