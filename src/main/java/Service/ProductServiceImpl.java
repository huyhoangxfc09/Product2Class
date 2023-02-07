package Service;

import Model.Category;
import Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ICrud<Product> {
    static  List<Product> listProduct;
    CategoryServiceImpl category;

    public ProductServiceImpl(CategoryServiceImpl category) {
        listProduct = new ArrayList<>();
        listProduct.add(new Product(1, "SP1", 150000, category.listCategory.get(0), 100));
        listProduct.add(new Product(2, "SP2", 250000, category.listCategory.get(1), 300));
        listProduct.add(new Product(3, "SP3", 155000, category.listCategory.get(2), 200));
        this.category = category;
    }
    @Override
    public List<Product> findAll() {
        return listProduct;
    }

    @Override
    public void save(Product product) {
        listProduct.add(product);
    }

    @Override
    public void update(int index, Product product) {
        listProduct.set(index, product);
    }

    @Override
    public void delete(Product product) {
        listProduct.remove(product);
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        for (Product p :
                listProduct) {
            if (p.getId() == id) {
                product = p;
                break;
            }
        }
        return product;
    }
}
