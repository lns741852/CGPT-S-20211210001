package com.htpe.bean;

import java.util.List;

public class CsrReqdetail {
    private Integer rdId;

    private String setno;
    
    private String setname;

    private Integer setcount;

    private Integer reqId;

    private Integer realcount;

    private Integer exchangecount;

    private Integer sn;

    private String type;

    private String allocate;
    
    private CsrSetdata3m setnoName;
    
    private List<CsrBarcode> barcodeName;
    
   
    public List<CsrBarcode> getBarcodeName() {
		return barcodeName;
	}

	public void setBarcodeName(List<CsrBarcode> barcodeName) {
		this.barcodeName = barcodeName;
	}

	public CsrSetdata3m getSetnoName() {
		return setnoName;
	}

	public void setSetnoName(CsrSetdata3m setnoName) {
		this.setnoName = setnoName;
	}

	public Integer getRdId() {
        return rdId;
    }

    public String getSetname() {
		return setname;
	}

	public void setSetname(String setname) {
		this.setname = setname;
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