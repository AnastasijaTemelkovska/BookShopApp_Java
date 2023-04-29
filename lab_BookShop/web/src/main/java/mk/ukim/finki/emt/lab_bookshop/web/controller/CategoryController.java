package mk.ukim.finki.emt.lab_bookshop.web.controller;

import mk.ukim.finki.emt.lab_bookshop.model.enumerations.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class CategoryController {

    @GetMapping("/categories")
    public String getAllCategories(Model model) {
        List<Category> categories = Arrays.asList(Category.values());
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "categories");
        return "master-template";
    }
}
