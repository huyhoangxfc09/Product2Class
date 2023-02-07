package Service;

import Model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements ICrud<Category> {
    static List<Category> listCategory;

    public CategoryServiceImpl() {
        listCategory = new ArrayList<>();
        listCategory.add(new Category(1, "Nokia"));
        listCategory.add(new Category(2, "Apple"));
        listCategory.add(new Category(3, "SamSung"));
    }
    @Override
    public List<Category> findAll() {
        return listCategory;
    }

    @Override
    public void save(Category category) {
        listCategory.add(category);
    }

    @Override
    public void update(int index, Category category) {
        listCategory.set(index,category);
    }

    @Override
    public void delete(Category category) {
        listCategory.remove(category);
    }

    @Override
    public Category findById(int id) {
        Category category = null;
        for (Category c :
                listCategory) {
            if (c.getId() == id) {
                category = c;
                break;
            }
        }
        return category;
    }
}
