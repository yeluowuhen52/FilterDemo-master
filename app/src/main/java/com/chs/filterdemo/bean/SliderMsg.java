package com.chs.filterdemo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 侧栏数据传递
 *
 * @author Jiang
 * @date 2019-06-26
 */
public class SliderMsg implements Serializable {
    //数据类型
    private int type;
    private List<Contact> contacts;

    public SliderMsg(int type, List<Contact> contacts) {
        this.type = type;
        this.contacts = contacts;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Contact> getContacts() {
        if (contacts == null) {
            return new ArrayList<>();
        }
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
