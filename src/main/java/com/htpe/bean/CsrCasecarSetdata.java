package com.htpe.bean;

public class CsrCasecarSetdata {
    private Integer id;

    private String casecarId;

    private String setnoId;
    
    private CsrCasecar casecar;
    
    private CsrSetdata3m csrSetdata3m;

    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCasecarId() {
        return casecarId;
    }

    public void setCasecarId(String casecarId) {
        this.casecarId = casecarId == null ? null : casecarId.trim();
    }

    public String getSetnoId() {
        return setnoId;
    }

    public void setSetnoId(String setnoId) {
        this.setnoId = setnoId == null ? null : setnoId.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }    

	public CsrCasecar getCasecar() {
		return casecar;
	}

	public void setCasecar(CsrCasecar casecar) {
		this.casecar = casecar;
	}

	public CsrSetdata3m getCsrSetdata3m() {
		return csrSetdata3m;
	}

	public void setCsrSetdata3m(CsrSetdata3m csrSetdata3m) {
		this.csrSetdata3m = csrSetdata3m;
	}


    
    
}