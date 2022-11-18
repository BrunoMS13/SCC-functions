package scc.dataclasses;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;

public class Auction {

    private String id;
    private String title;
    private String ownerId;
    private String description;
    private String imageId;
    private float minPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date endingTime;
    private Map<String, Bid> bids;
    private Map<String, Question> questions;
    private String status;
    private String winnerId;

    public Auction() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
            this.status = "OPEN";
            this.bids = new Hashtable<>();
            this.questions = new Hashtable<>();
        }
    }
    public Auction(AuctionDAO auctionDAO) {
        this.id = auctionDAO.getId();
        this.ownerId = auctionDAO.getOwnerId();
        this.title = auctionDAO.getTitle();
        this.description = auctionDAO.getDescription();
        this.endingTime = auctionDAO.getEndingTime();
        this.status = auctionDAO.getStatus();
        this.minPrice = auctionDAO.getMinPrice();
        this.imageId = auctionDAO.getImageId();
        this.winnerId = auctionDAO.getWinnerId();
        this.bids = auctionDAO.getBids();
        this.questions = auctionDAO.getQuestions();
    }
    public Auction(String title, String description, String imageId, String ownerId, Date endTime, float minPrice) {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
            this.status = "OPEN";
            this.bids = new HashMap<>();
            this.questions = new HashMap<>();
        }
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
        this.imageId = imageId;
        this.minPrice = minPrice;
        this.endingTime = endTime;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    public String getOwnerId() {
        return this.ownerId;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {return this.title;}
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    public String getImageId() {
        return this.imageId;
    }
    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }
    public float getMinPrice() {
        return this.minPrice;
    }
    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
    }
    public Date getEndingTime() {
        return this.endingTime;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }
    public void addBid(Bid bid) {bids.put(bid.getBidId(), bid);}
    public void addQuestion(Question question) {questions.put(question.getId(), question);}

    public Map<String, Bid> getBids() {return this.bids;}
    public Map<String, Question> getQuestions() {return this.questions;}
    @Override
    public String toString() {
        return "Auction [id=" + this.id + " ownerId=" + this.ownerId + " status=" + this.status + " winnerId=" + this.winnerId + " description=" + this.description + " imageId=" + this.imageId + " minPrice=" + this.minPrice + " endingDate=" + this.endingTime.toString() + "]";
    }

}