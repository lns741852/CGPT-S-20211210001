package com.htpe.bean;

public class CsrSetdataSeqUdi {
    private Integer id;

    private String csrSetdataSeqId;

    private String csrUdiId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCsrSetdataSeqId() {
        return csrSetdataSeqId;
    }

    public void setCsrSetdataSeqId(String csrSetdataSeqId) {
        this.csrSetdataSeqId = csrSetdataSeqId == null ? null : csrSetdataSeqId.trim();
    }

    public String getCsrUdiId() {
        return csrUdiId;
    }

    public void setCsrUdiId(String csrUdiId) {
        this.csrUdiId = csrUdiId == null ? null : csrUdiId.trim();
    }
}