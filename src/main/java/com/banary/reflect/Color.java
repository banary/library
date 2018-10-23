package com.banary.reflect;

public enum Color {

    red("#00FFFF"),
    WHITE("#000000"),
    BLACK("#FFFFFF");

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    Color(String key) {
        this.key = key;
    }
}
