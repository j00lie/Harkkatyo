package com.example.ht;

public class Person {
    private int age;
    private float height;
    private static Person person = new Person();
    public Person(){
    }
    public static Person getInstance(){
        return person;
    }
    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
