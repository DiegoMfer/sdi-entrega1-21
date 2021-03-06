package com.sdi21.socialnetwork.entities;

import javax.persistence.*;

@Entity
public class FriendRequest {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name ="sender")
    private User sender;
    @ManyToOne
    @JoinColumn(name ="receiver")
    private User receiver;

    @Enumerated(EnumType.STRING)
    private State state;

    public enum State {
        ACCEPTED,
        PENDING
    }

    public FriendRequest() {

    }

    public FriendRequest (User sender, User receiver) {
        super();
        this.sender = sender;
        this.receiver = receiver;
        this.state = State.PENDING;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isAccepted() { return this.state == State.ACCEPTED; }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", state=" + state +
                '}';
    }
}
