package com.quach.shop_giay.database;

import java.util.ArrayList;

public interface DAOInterface<T> {
    public ArrayList<T> getAll();

    public T getId(T t);

    public int insert(T t);

    public int insertAll(ArrayList<T> arr);

    public int delete(T t);

    public int deleteAll(ArrayList<T> arr);

    public int update(T t);
}
