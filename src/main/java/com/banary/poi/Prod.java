package com.banary.poi;

/**
 * @Description
 * @Author eden
 * @Date 2018/5/3 下午5:15
 */
public class Prod {

    private String no;
    private String name;
    private String legal;

    public Prod(String name, String legal) {
        this.name = name;
        this.legal = legal;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }
}
