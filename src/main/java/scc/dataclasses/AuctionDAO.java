package scc.dataclasses;

import java.util.*;

public class AuctionDAO {
    private String _rid;
    private String _ts;
    private String id;
    private String title;
    private String ownerId;
    private String winnerId;
    private String description;
    private String imageId;
    private float minPrice;
    private Date endingTime;
    private Map<String, Bid> bids;
    private Map<String, Question> questions;

    private String status;

    public AuctionDAO() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
            this.status = "OPEN";
            this.bids = new HashMap<>();
            this.questions = new HashMap<>();
        }
    }
    public AuctionDAO(Auction a) {
        this(a.getTitle(), a.getDescription(), a.getImageId(), a.getOwnerId(), a.getEndingTime(), a.getMinPrice());
        this.id = a.getId();
        this.bids = a.getBids();
        this.questions = a.getQuestions();
    }
    public AuctionDAO(String title, String description, String imageId, String ownerId, Date endTime, float minPrice) {
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
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {return this.title;}
    public String get_rid() {
        return _rid;
    }
    public void set_rid(String _rid) {
        this._rid = _rid;
    }
    public String get_ts() {
        return _ts;
    }
    public void set_ts(String _ts) {
        this._ts = _ts;
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
    public void setWinnerId(String winnerId) {
        this.winnerId = winnerId;
    }
    public String getWinnerId() {
        return this.winnerId;
    }

    public void addBid(Bid bid) {
        bids.put(bid.getBidId(), bid);
        minPrice = bid.getBidValue();
    }
    public void addQuestion(Question question) {questions.put(question.getId(), question);}

    public Map<String, Bid> getBids() {return this.bids;}
    public Map<String, Question> getQuestions() {return this.questions;}
    @Override
    public String toString() {
        return "Auction [title=" + this.id + " ownerId=" + this.ownerId + " status=" + this.status +  " minBid=" + this.minPrice + "]";
    }

}