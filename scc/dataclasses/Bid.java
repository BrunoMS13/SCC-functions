package scc.dataclasses;

import java.util.UUID;

public class Bid {
    private String userId;
    private String auctionId;
    private String bidId;
    private float bidValue;

    public Bid() {
        if (this.bidId == null)
            this.bidId = UUID.randomUUID().toString();
    }
    public Bid(String userId, String auctionId, float bidValue) {
        if (this.bidId == null)
            this.bidId = UUID.randomUUID().toString();
        this.auctionId = auctionId;
        this.userId = userId;
        this.bidValue = bidValue;
    }
    public void setUserId(String userId) {this.userId = userId;}
    public void setBidId(String bidId) {this.bidId = bidId;}
    public void setBidValue(float bidValue) {this.bidValue = bidValue;}

    public String getAuctionId() {return this.auctionId;}
    public String getUserId() {return this.userId;}
    public String getBidId() {return this.bidId;}
    public float getBidValue() {return this.bidValue;}

    @Override
    public String toString() {
        return "Bid [Bid ID: " + getBidId() + " User ID: " + getUserId() + " Bid Value: " + getBidValue() + "]";
    }
}