package com.htpe.bean;

import java.util.Date;
import java.util.List;

public class CsrSetdataSeq {
    private Integer id;

    private String setno;

    private String setnamech;

    private String seq;

    private String publicType;

    private String updType;

    private String delType;

    private String datauser;

    private Date datadate;
    
    private List<CsrSetdataSeqUdi> seqUdi;  
    

    public List<CsrSetdataSeqUdi> getSeqUdi() {
		return seqUdi;
	}

	public void setSeqUdi(List<CsrSetdataSeqUdi> seqUdi) {
		this.seqUdi = seqUdi;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSetno() {
        return setno;
    }

    public void setSetno(String setno) {
        this.setno = setno == null ? null : setno.trim();
    }

    public String getSetnamech() {
        return setnamech;
    }

    public void setSetnamech(String setnamech) {
        this.setnamech = setnamech == null ? null : setnamech.trim();
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq == null ? null : seq.trim();
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