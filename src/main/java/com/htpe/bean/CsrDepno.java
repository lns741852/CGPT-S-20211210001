package com.htpe.bean;

import java.util.Date;

public class CsrDepno {
    private Integer id;

    private String depno;

    private String depname;

    private String depnamech;

    private String depfrom;

    private String isdelete;

    private String datauserno;

    private String datausername;

    private Date datadate;

    private String moduserno;

    private String modusername;

    private Date moddate;

    private String type;

    private String defaultdesinfection;
    
    private CsrPotDepno csrPotDepnos;
    
    private CsrPrinter csrPrinter;
     

	public CsrPotDepno getCsrPotDepno() {
		return csrPotDepnos;
	}

	public void setCsrPotDepno(CsrPotDepno csrPotDepno) {
		this.csrPotDepnos = csrPotDepno;
	}

	public CsrPrinter getCsrPrinter() {
		return csrPrinter;
	}

	public void setCsrPrinter(CsrPrinter csrPrinter) {
		this.csrPrinter = csrPrinter;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepno() {
        return depno;
    }

    public void setDepno(String depno) {
        this.depno = depno == null ? null : depno.trim();
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname == null ? null : depname.trim();
    }

    public String getDepnamech() {
        return depnamech;
    }

    public void setDepnamech(String depnamech) {
        this.depnamech = depnamech == null ? null : depnamech.trim();
    }

    public String getDepfrom() {
        return depfrom;
    }

    public void setDepfrom(String depfrom) {
        this.depfrom = depfrom == null ? null : depfrom.trim();
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDefaultdesinfection() {
        return defaultdesinfection;
    }

    public void setDefaultdesinfection(String defaultdesinfection) {
        this.defaultdesinfection = defaultdesinfection == null ? null : defaultdesinfection.trim();
    }
}