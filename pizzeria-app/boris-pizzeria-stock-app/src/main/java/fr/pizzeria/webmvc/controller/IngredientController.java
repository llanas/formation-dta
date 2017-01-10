package fr.pizzeria.webmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.pizzeria.dao.ingredient.IngredientDao;
import fr.pizzeria.model.Ingredient;

@Controller
@RequestMapping("/mvc/rest/ingredients")
public class IngredientController {
	
	@Autowired
	IngredientDao ingDao;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Ingredient> findIngredient() {
		return ingDao.getListIngredient();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveIngredient(@RequestBody Ingredient ing, HttpServletResponse resp) {
		ModelAndView mav = new ModelAndView();
		mav.addObject(ingDao.ajouter(ing));
		mav.setViewName("listIngredient");
		return mav;
	}
	
	
}
