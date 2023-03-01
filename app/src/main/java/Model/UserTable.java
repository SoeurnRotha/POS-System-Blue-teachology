package Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserTable {

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


    public UserTable(String frist_name, String last_name, String username, String password, String gender, String brith_of_date, String date_create_user, boolean admin, boolean manager, boolean cashier, boolean insert, boolean update, boolean delete, boolean view, boolean allow) {
        this.frist_name = frist_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.brith_of_date = brith_of_date;
        this.date_create_user = date_create_user;
        this.admin = admin;
        this.manager = manager;
        this.cashier = cashier;
        this.insert = insert;
        this.update = update;
        this.delete = delete;
        this.view = view;
        this.allow = allow;
    }

    @Override
    public String toString() {
        return "UserTable{" +
                "userId=" + userId +
                ", frist_name='" + frist_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", brith_of_date='" + brith_of_date + '\'' +
                ", date_create_user='" + date_create_user + '\'' +
                ", admin=" + admin +
                ", manager=" + manager +
                ", cashier=" + cashier +
                ", insert=" + insert +
                ", update=" + update +
                ", delete=" + delete +
                ", view=" + view +
                ", allow=" + allow +
                '}';
    }

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
