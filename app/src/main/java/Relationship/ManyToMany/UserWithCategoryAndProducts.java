package Relationship.ManyToMany;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import Model.UserTable;

public class UserWithCategoryAndProducts {

    @Embedded
    public UserTable userTable;

    @Relation(
            parentColumn = "userId",
            entityColumn = "userId"

    )
    public List<CategoryWithProduct> categoryWithProducts;

    public UserWithCategoryAndProducts(UserTable userTable, List<CategoryWithProduct> categoryWithProducts) {
        this.userTable = userTable;
        this.categoryWithProducts = categoryWithProducts;
    }

    public UserTable getUserTable() {
        return userTable;
    }

    public void setUserTable(UserTable userTable) {
        this.userTable = userTable;
    }

    public List<CategoryWithProduct> getCategoryWithProducts() {
        return categoryWithProducts;
    }

    public void setCategoryWithProducts(List<CategoryWithProduct> categoryWithProducts) {
        this.categoryWithProducts = categoryWithProducts;
    }

    @Override
    public String toString() {
        return "UserWithCategoryAndProducts{" +
                "userTable=" + userTable +
                ", categoryWithProducts=" + categoryWithProducts +
                '}';
    }
}
