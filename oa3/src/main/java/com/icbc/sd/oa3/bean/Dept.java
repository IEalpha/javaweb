package com.icbc.sd.oa3.bean;

import java.util.Objects;

public class Dept {

    private String deptNo;
    private String dname;
    private String location;

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "dept{" +
                "deptNo='" + deptNo + '\'' +
                ", dname='" + dname + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public Dept() {

    }

    public Dept(String deptNo, String dname, String location) {
        this.deptNo = deptNo;
        this.dname = dname;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dept dept = (Dept) o;
        return Objects.equals(deptNo, dept.deptNo) && Objects.equals(dname, dept.dname) && Objects.equals(location, dept.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptNo, dname, location);
    }
}
