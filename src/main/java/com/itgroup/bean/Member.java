package com.itgroup.bean;

public class Member {
    private String id ;
    private String name ;
    private String password ;
    private String gender ;
    private String birth ;
    private String marriage ;
    private int salary ;
    private String address ;
    private String manager ;

    // getter, setter, toString(), 생성자 구현

    public Member() {
    }

    public Member(String id, String name, String password, String gender, String birth, String marriage, int salary, String address, String manager) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.birth = birth;
        this.marriage = marriage;
        this.salary = salary;
        this.address = address;
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", birth='" + birth + '\'' +
                ", marriage='" + marriage + '\'' +
                ", salary=" + salary +
                ", address='" + address + '\'' +
                ", manager='" + manager + '\'' +
                '}';
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
