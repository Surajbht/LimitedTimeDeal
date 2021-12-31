package com.machine.TimeDeal.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table
@Entity(name = "DEAL_MASTER")
public class Claim {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "DEAL_ID")
    private Long dealId;

    @Column(name = "CLAIMED_BY")
    private Long claimedBy;

    @Column(name = "CLAIMED_AT")
    private Date claimedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDealId() {
        return dealId;
    }

    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    public Long getClaimedBy() {
        return claimedBy;
    }

    public void setClaimedBy(Long claimedBy) {
        this.claimedBy = claimedBy;
    }

    public Date getClaimedAt() {
        return claimedAt;
    }

    public void setClaimedAt(Date claimedAt) {
        this.claimedAt = claimedAt;
    }
}
