package com.htpe.bean;

public class CsrAccountAuth {
    private Integer id;

    private CsrAccount csrAccount;
    
    private CsrAuth csrAuth;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public CsrAccount getCsrAccount() {
		return csrAccount;
	}

	public void setCsrAccount(CsrAccount csrAccount) {
		this.csrAccount = csrAccount;
	}

	public CsrAuth getCsrAuth() {
		return csrAuth;
	}

	public void setCsrAuth(CsrAuth csrAuth) {
		this.csrAuth = csrAuth;
	}


	

    

}