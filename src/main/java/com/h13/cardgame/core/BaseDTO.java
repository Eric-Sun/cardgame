package com.h13.cardgame.core;

public class BaseDTO {
    private long cid;
    private long uid;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    private String status = "0";

    public BaseDTO(long uid, long cid, String status) {
        this.uid = uid;
        this.cid = cid;
        this.status = status;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
