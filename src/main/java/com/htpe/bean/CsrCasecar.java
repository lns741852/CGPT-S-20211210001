package com.htpe.bean;

import java.util.Date;
import java.util.List;

public class CsrCasecar {
    private Integer casecarId;

    private String casecarno;

    private String datauserno;

    private String datausername;

    private Date datadate;

    private String moduserno;

    private String modusername;

    private Date moddate;

    private String isdelete;
    
    private List<CsrCasecarSetdata> casecarSetdatas;

    public Integer getCasecarId() {
        return casecarId;
    }

    public void setCasecarId(Integer casecarId) {
        this.casecarId = casecarId;
    }

    public String getCasecarno() {
        return casecarno;
    }

    public void setCasecarno(String casecarno) {
        this.casecarno = casecarno == null ? null : casecarno.trim();
    }

    public String getDatauserno() {
        return datauserno;
    }

    public void setDatauserno(String datauserno) {
        this.datauserno = datauserno == null ? null : datauserno.trim();
    }

    public String getDatausername() {
        return datausername;
    }

    public void setDatausername(String datausername) {
        this.datausername = datausername == null ? null : datausername.trim();
    }

    public Date getDatadate() {
        return datadate;
    }

    public void setDatadate(Date datadate) {
        this.datadate = datadate;
    }

    public String getModuserno() {
        return moduserno;
    }

    public void setModuserno(String moduserno) {
        this.moduserno = moduserno == null ? null : moduserno.trim();
    }

    public String getModusername() {
        return modusername;
    }

    public void setModusername(String modusername) {
        this.modusername = modusername == null ? null : modusername.trim();
    }

    public Date getModdate() {
        return moddate;
    }

    public void setModdate(Date moddate) {
        this.moddate = moddate;
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
    }

	public List<CsrCasecarSetdata> getCasecarSetdatas() {
		return casecarSetdatas;
	}

	public void setCasecarSetdatas(List<CsrCasecarSetdata> casecarSetdatas) {
		this.casecarSetdatas = casecarSetdatas;
	}

	@Override
	public String toString() {
		return "CsrCasecar [casecarId=" + casecarId + ", casecarno=" + casecarno + ", datauserno=" + datauserno
				+ ", datausername=" + datausername + ", datadate=" + datadate + ", moduserno=" + moduserno
				+ ", modusername=" + modusername + ", moddate=" + moddate + ", isdelete=" + isdelete
				+ ", casecarSetdatas=" + casecarSetdatas + "]";
	}
    
	
	
    
}