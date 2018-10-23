package com.banary.reflect;

public class SubPerson extends Person {

    public Long subId;

    private String subName;

    private SubPerson() {
    }

    public SubPerson(Long subId, String subName) {
        this.subId = subId;
        this.subName = subName;
    }

}
