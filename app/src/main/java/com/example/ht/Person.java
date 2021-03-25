package com.example.ht;

public class Person {
    int age;
    float height;

    public Person(int age, float height){
        this.age = age;
        this.height = height;
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
