package com.chs.filterdemo;

/**
 * @author Jiang
 * @date 2019-06-25
 */
public class TestBean {
    private String name;

    public String getName() {
        return name == null ? "" : name;
    }

    public TestBean(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
