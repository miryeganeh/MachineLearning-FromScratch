package com.company;

public interface DS <E> {
    public void insert(E data);
    public boolean delete(String value);
    public void print();
    public boolean search(String value);
    public E getByValue(String value);
    public String toString();
}
