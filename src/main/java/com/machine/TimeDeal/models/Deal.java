package com.machine.TimeDeal.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table
@Entity(name = "DEAL_MASTER")
public class Deal {

    @Id
    @Column(name = "ID")
    private Long dealId;

    @Column(name = "DEAL_NAME")
    private String name;

    @Column(name = "DEAL_PRICE")
    private Double price;

    @Column(name = "MAX_LIMIT")
    private Integer maxLimit;

    @Column(name = "ITEM_COUNT")
    private Integer itemCount;

    @Column(name = "START_TIME")
    private Date startTime;

    @Column(name = "END_TIME")
    private Date endTime;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    public Long getId() {
        return dealId;
    }

    public void setId(Long id) {
        this.dealId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Integer maxLimit) {
        this.maxLimit = maxLimit;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
