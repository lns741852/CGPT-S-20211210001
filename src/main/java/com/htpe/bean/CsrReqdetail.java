package com.htpe.bean;

public class CsrReqdetail {
    private Integer rdId;

    private String setno;

    private Integer setcount;

    private Integer reqId;

    private Integer realcount;

    private Integer exchangecount;

    private Integer sn;

    private String type;

    private String allocate;

    public Integer getRdId() {
        return rdId;
    }

    public void setRdId(Integer rdId) {
        this.rdId = rdId;
    }

    public String getSetno() {
        return setno;
    }

    public void setSetno(String setno) {
        this.setno = setno == null ? null : setno.trim();
    }

    public Integer getSetcount() {
        return setcount;
    }

    public void setSetcount(Integer setcount) {
        this.setcount = setcount;
    }

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public Integer getRealcount() {
        return realcount;
    }

    public void setRealcount(Integer realcount) {
        this.realcount = realcount;
    }

    public Integer getExchangecount() {
        return exchangecount;
    }

    public void setExchangecount(Integer exchangecount) {
        this.exchangecount = exchangecount;
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAllocate() {
        return allocate;
    }

    public void setAllocate(String allocate) {
        this.allocate = allocate == null ? null : allocate.trim();
    }
}