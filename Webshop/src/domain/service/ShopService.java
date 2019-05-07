package domain.service;

import domain.db.PersonDB;
import domain.db.PersonDBSQL;
import domain.db.ProductDB;
import domain.db.ProductDBSQL;
import domain.model.Person;
import domain.model.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class ShopService {
    private PersonDB personDB;
    private ProductDB productDB;


    public ShopService(Properties properties){
        personDB = new PersonDBSQL(properties);
        productDB = new ProductDBSQL(properties);
    }
    //-----------------------PERSON-----------------------//
    public Person getPerson(String personId) throws SQLException {
        return personDB.getPerson(personId);
    }

    public List<Person> getAllPersons(){
        return personDB.getAllPersons();
    }

    public void addPerson(Person person) throws SQLException {
        personDB.addPerson(person);
    }

    public void updatePerson(Person person){

    }

    public void deletePerson(String personId) throws SQLException {
        personDB.deletePerson(personId);
    }

    public int getNumberOfPersons(){
        return personDB.getAllPersons().size();
    }

    //-----------------PRODUCT-------------------------//

    public Product getProduct(String productid){
        return productDB.getProduct(productid);
    }

    public List<Product> getAllProducts(){
        return productDB.getAllProducts();
    }

    public void addProduct(Product product){
        productDB.addProduct(product);
    }

    public void updateProduct(Product product){

    }

    public void deleteProduct(String productid){
        productDB.deleteProduct(productid);
    }

    public int getNumberOfProducts(){
        return productDB.getAllProducts().size();
    }


}
