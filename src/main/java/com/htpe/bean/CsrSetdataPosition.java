package com.htpe.bean;

import java.util.Date;

public class CsrSetdataPosition {
    private Integer id;

    private String code;

    private String name;

    private String publicType;

    private String updType;

    private String delType;

    private String datauser;

    private Date datadate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPublicType() {
        return publicType;
    }

    public void setPublicType(String publicType) {
        this.publicType = publicType == null ? null : publicType.trim();
    }

    public String getUpdType() {
        return updType;
    }

    public void setUpdType(String updType) {
        this.updType = updType == null ? null : updType.trim();
    }

    public String getDelType() {
        return delType;
    }

    public void setDelType(String delType) {
        this.delType = delType == null ? null : delType.trim();
    }

    public String getDatauser() {
        return datauser;
    }

    public void setDatauser(String datauser) {
        this.datauser = datauser == null ? null : datauser.trim();
    }

    public Date getDatadate() {
        return datadate;
    }

    public void setDatadate(Date datadate) {
        this.datadate = datadate;
    }
}