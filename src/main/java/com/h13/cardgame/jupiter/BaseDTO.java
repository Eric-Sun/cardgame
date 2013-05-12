package com.h13.cardgame.jupiter;

public class BaseDTO {
    private long cid;
    private long uid;
    private String status = "0";
    private String code = "";

    public BaseDTO(long uid, long cid, String status, String code) {
        this.uid = uid;
        this.cid = cid;
        this.status = status;
        this.code = code;
    }
    public BaseDTO(long uid, long cid, String status) {
        this.uid = uid;
        this.cid = cid;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
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
