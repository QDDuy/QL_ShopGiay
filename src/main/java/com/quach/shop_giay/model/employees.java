package com.quach.shop_giay.model;

public class employees {
    private String idEmploye;
    private Account taikhoan;
    private String fullname;
    private String address;
    private int age;
    private String gender;
    private String phone;
    private String email;
    private double luong;

    public employees(String idEmploye, Account taikhoan, String fullname, String address, int age, String gender, String phone, String email, double luong) {
        this.idEmploye = idEmploye;
        this.taikhoan = taikhoan;
        this.fullname = fullname;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.luong = luong;
    }

    public employees() {
    }

    public String getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(String idEmploye) {
        this.idEmploye = idEmploye;
    }

    public Account getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(Account taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    @Override
    public String toString() {
        return "employees{" +
                "idEmploye='" + idEmploye + '\'' +
                ", taikhoan=" + taikhoan +
                ", fullname='" + fullname + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", luong=" + luong +
                '}';
    }
}


