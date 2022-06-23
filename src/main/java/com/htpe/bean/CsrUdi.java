package com.htpe.bean;

import java.util.Date;

public class CsrUdi {
    private Integer id;

    private String udi;

    private String status;

    private Integer price;

    private Integer typeId;

    private Integer packageId;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;
     
    private CsrUdiType udiType;
    
    private CsrSetdataSeq csrSetdataSeq;
       
    

    public CsrSetdataSeq getCsrSetdataSeq() {
		return csrSetdataSeq;
	}

	public void setCsrSetdataSeq(CsrSetdataSeq csrSetdataSeq) {
		this.csrSetdataSeq = csrSetdataSeq;
	}

	public CsrUdiType getUdiType() {
		return udiType;
	}

	public void setUdiType(CsrUdiType udiType) {
		this.udiType = udiType;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUdi() {
        return udi;
    }

    public void setUdi(String udi) {
        this.udi = udi == null ? null : udi.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}