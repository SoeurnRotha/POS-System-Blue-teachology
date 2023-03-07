package Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class UserTable implements Serializable {

    @PrimaryKey(autoGenerate = true)

    private int userId;


    @ColumnInfo
    String frist_name;

    @ColumnInfo
    String last_name;

    @ColumnInfo
    String username;


    @ColumnInfo
    String password;

    @ColumnInfo
    String userImage;


    @ColumnInfo
    String gender;


    @ColumnInfo
    String brith_of_date;

    @ColumnInfo
    String date_create_user;


    @ColumnInfo
    boolean admin;


    @ColumnInfo
    boolean manager;

    @ColumnInfo
    boolean cashier;

    @ColumnInfo
    boolean insert;

    @ColumnInfo
    boolean update;

    @ColumnInfo
    boolean delete;


    @ColumnInfo
    boolean view;

    @ColumnInfo
    boolean allow;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFrist_name() {
        return frist_name;
    }

    public void setFrist_name(String frist_name) {
        this.frist_name = frist_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBrith_of_date() {
        return brith_of_date;
    }

    public void setBrith_of_date(String brith_of_date) {
        this.brith_of_date = brith_of_date;
    }

    public String getDate_create_user() {
        return date_create_user;
    }

    public void setDate_create_user(String date_create_user) {
        this.date_create_user = date_create_user;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public boolean isCashier() {
        return cashier;
    }

    public void setCashier(boolean cashier) {
        this.cashier = cashier;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }
}
