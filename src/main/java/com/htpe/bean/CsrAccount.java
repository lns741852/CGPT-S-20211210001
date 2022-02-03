package com.htpe.bean;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class CsrAccount {
    private Integer id;

    private String userno;
    
    private String usercname;
    
    private String depno;

    private String userpwd;
    
    private String systemprivilege;

    private Date createtime;

    private String iscancel;
    
    private List<CsrAccountAuth> csrAccountAuths;
    
    

    public List<CsrAccountAuth> getCsrAccountAuths() {
		return csrAccountAuths;
	}

	public void setCsrAccountAuths(List<CsrAccountAuth> csrAccountAuths) {
		this.csrAccountAuths = csrAccountAuths;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getUsercname() {
        return usercname;
    }

    public void setUsercname(String usercname) {
        this.usercname = usercname == null ? null : usercname.trim();
    }

    public String getDepno() {
        return depno;
    }

    public void setDepno(String depno) {
        this.depno = depno == null ? null : depno.trim();
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno == null ? null : userno.trim();
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    public String getSystemprivilege() {
        return systemprivilege;
    }

    public void setSystemprivilege(String systemprivilege) {
        this.systemprivilege = systemprivilege == null ? null : systemprivilege.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getIscancel() {
        return iscancel;
    }

    public void setIscancel(String iscancel) {
        this.iscancel = iscancel == null ? null : iscancel.trim();
    }



    
    
}