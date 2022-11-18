package scc.dataclasses;

import java.util.UUID;

public class Question {
    private String id;
    private String user;
    private String text;
    private String reply; // includes the text of reply (null if does not exist)


    public Question() {
        if (this.id == null)
            this.id = UUID.randomUUID().toString();
    }
    public Question(String user, String reply, String text) {
        if (this.id == null)
            this.id = UUID.randomUUID().toString();
        this.user = user;
        this.reply = reply;
        this.text = text;
    }

    public void setId(String id) {this.id = id;}
    public void setUser(String user) {this.user = user;}
    public void setReply(String reply) {this.reply = reply;}
    public void setText(String text) {this.text = text;}

    public String getId() {return id;}
    public String getUser() {return this.user;}
    public String getReply() {return this.reply;}
    public String getText() {return this.text;}

    @Override
    public String toString() {
        return "Question [Question ID: " + getId() + " User ID:" + getUser() + ", Reply to question:" + getReply() + ", Text:" + getText() + "]";
    }
}