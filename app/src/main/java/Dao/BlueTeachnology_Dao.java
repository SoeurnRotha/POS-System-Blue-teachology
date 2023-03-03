package Dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.TypeConverter;
import androidx.room.Update;

import com.example.bluesystemwithroomdatabase.Cart.Cart;

import java.util.List;

import Model.CartTable;
import Model.CategoryTable;
import Model.Customer;
import Model.Invoice;
import Model.LocationTable;
import Model.PaymentMethod;
import Model.ProductTable;
import Model.TestImage;
import Model.UserTable;
import Relationship.ManyToMany.CategoryWithProduct;
import Relationship.ManyToMany.LocationWithProducts.LocationWithProducts;
import Relationship.ManyToMany.LocationWithProducts.ProductWithLocation;
import Relationship.ManyToMany.OneToOne.ProductWithCarts;

@Dao
public interface BlueTeachnology_Dao {


    //user
    @Insert
    void insertUser(UserTable userTable);


    @Query("SELECT EXISTS (SELECT * from USERTABLE where username=:username)")
    boolean is_token(String username);

    @Query("SELECT EXISTS (SELECT * from USERTABLE where username=:username AND password=:password)")
    boolean login(String username , String password);

    @Query("SELECT * FROM usertable")
    List<UserTable> getAlluserInfo();


    @Delete
    void delete(UserTable userTable);


    @Query("DELETE FROM usertable WHERE userId =:uid")
    void deleteUserByid(int uid);





    @Query("UPDATE usertable SET username = :username , password=:password WHERE userId =:uid")
    void updateUserBYid(int uid ,String username,String password);







//category

    @Insert
    void insertCaterory(CategoryTable categoryTable);

    @Update
    void updateCategory(CategoryTable categoryTable);


    @Delete
    void deleteCateroty(CategoryTable categoryTable);



    @Query("UPDATE CategoryTable SET categoryname_Eng =:eng , categoryname_kh =:kh WHERE categoryID=:cid")
    void updateCategoryByid(int cid, String eng,String kh);






    @Query("DELETE FROM CategoryTable WHERE categoryID =:cid")
    void deleteByid(int cid);



    @Query("SELECT * FROM CategoryTable")
    List<CategoryTable> getAllCateroy();



    //products

    @Insert
    void insertProducts(ProductTable productTable);

    @Update
    void updateProducts(ProductTable productTable);

    @Delete
    void deleteProducts(ProductTable productTable);

    @Query("SELECT * FROM ProductTable")
    List<ProductTable> getAllProducts();



    @Query("UPDATE ProductTable SET productName_eng =:eng , productName_kh=:kh , product_qty =:qty , product_Price =:price ,product_barCode=:barcode , categoryID=:categoryId WHERE productId=:product_id")
    void updateProductByid(int product_id, String eng,String kh,int qty,double price, String barcode,int categoryId);



    @Query("DELETE FROM ProductTable WHERE productId =:productId")
    void deleteProductByid(int productId);













    //Customer


    @Insert
    void insertCustomer(Customer customer);


    @Query("DELETE FROM Customer")
    void deleteAllCustomer();



    //is name == name = false ,ex = rotha , rotha ==false
    @Query("SELECT * FROM Customer WHERE customerName =:customerName")
    List<Customer> checkCustomer(String customerName);

    @Update
    void updateCustomer(Customer customer);


    @Query("UPDATE Customer SET  customerName=:cname, customer_phone=:cphone, customer_email=:cemail, customer_gender=:cgender, customer_address=:caddress, date_time_create=:cdate WHERE customerId LIKE:cid ")
    void updatecustomerByid(int cid,String cname,String cphone,String cemail,String cgender,String caddress,String cdate);
    @Delete
    void deleteCustomer(Customer customer);

    @Query("DELETE FROM Customer WHERE customerId =:cus_id")
    void deleteCustomerByid(int cus_id);


    @Query("SELECT * FROM Customer")
    List<Customer> getAllCustomer();




    //payment method


    @Insert
    void insertPayment(PaymentMethod paymentMethod);

    @Update
    void updatePayment(PaymentMethod paymentMethod);


    @Query("SELECT * FROM PaymentMethod")
    List<PaymentMethod> getAllPayments();


    @Query("DELETE FROM PaymentMethod WHERE paymentId =:paymentId")
    void deletePayment(int paymentId);






    //cart

    @Query("SELECT * FROM CartTable")
    List<CartTable> getAllCart();


    @Insert
    void insertCart(CartTable cartTable);


    @Query("DELETE FROM CartTable WHERE cartId=:cartId ")
    void deleteCartByid(int cartId);


    //delete all record
    @Delete
    void deleteAllCart(List<CartTable> cartTableList);



    //one to one product with carts


    @Transaction
    @Query("SELECT * FROM ProductTable")
    List<ProductWithCarts> getProductWithCarts();




    /// location


    @Insert
    void insertLocaton(LocationTable locationTable);

    @Update
    void updateLocation(LocationTable locationTable);


    @Query("SELECT * FROM LocationTable")
    List<LocationTable> getAllLocation();


    @Query("DELETE FROM LocationTable WHERE location_Id =:lid")
    void deleteLocationByid(int lid);





    //invoice table


    @Insert
    void insertInvoice(Invoice invoice);

















































    //products relationship with category
    @TypeConverter
    @Query("select * from CategoryTable")
    List<CategoryWithProduct> getCategoryWithProducts();

    @TypeConverter
    @Query("select * from ProductTable")
    List<ProductTable> getProductWithCategory();


    @TypeConverter
    @Query("select * from usertable")
    List<UserTable> getUserWithCategoryAndProducts();





    //test image

    @Insert
    void insertImage(TestImage testImage);

    @Query("SELECT * FROM TestImage")
    List<TestImage> getImage();





    //many to many

    @Transaction
    @Query("SELECT * FROM LocationTable")
    List<LocationWithProducts> getLocationWithProducts();


    @Transaction
    @Query("SELECT * FROM ProductTable")
    List<ProductWithLocation> getProductWithLocation();









}
