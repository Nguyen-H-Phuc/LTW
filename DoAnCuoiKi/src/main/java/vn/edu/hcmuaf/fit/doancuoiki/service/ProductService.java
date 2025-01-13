package vn.edu.hcmuaf.fit.doancuoiki.service;

import vn.edu.hcmuaf.fit.doancuoiki.dao.ProductDao;
import vn.edu.hcmuaf.fit.doancuoiki.model.Product;

import java.util.List;

public class ProductService implements IProductService {

    private ProductDao productDAO = new ProductDao();
    @Override
    public List<Product> findAll() {

        return productDAO.findALl();
    }

    @Override
    public Product findById(int id) {
        return productDAO.findById(id).stream().findFirst().get();
    }
}
