package com.banary.poi;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description
 * @Author eden
 * @Date 2018/5/3 下午1:49
 */
public class Work {

    private Date date;
    private String name;
    private String no;
    private String weight;
    private String process;
    private String thWeight;
    private String number;
    private String price;
    private String amount;

    @Override
    public String toString() {
        return "Work{" +
                "date=" + date +
                ", name='" + name + '\'' +
                ", no='" + no + '\'' +
                ", weight=" + weight +
                ", process='" + process + '\'' +
                ", thWeight=" + thWeight +
                ", number=" + number +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getThWeight() {
        return thWeight;
    }

    public void setThWeight(String thWeight) {
        this.thWeight = thWeight;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
