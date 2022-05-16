package com.htpe.bean;

import java.util.Date;

public class CsrFileResource {
    private Integer id;

    private String resourceName;

    private String localPath;

    private String datauserno;

    private Date datadate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath == null ? null : localPath.trim();
    }

    public String getDatauserno() {
        return datauserno;
    }

    public void setDatauserno(String datauserno) {
        this.datauserno = datauserno == null ? null : datauserno.trim();
    }

    public Date getDatadate() {
        return datadate;
    }

    public void setDatadate(Date datadate) {
        this.datadate = datadate;
    }
}