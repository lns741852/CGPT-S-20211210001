package com.htpe.bean;

import java.util.Date;

public class CsrPoltldHistory {
    private Integer id;

    private Integer potid;

    private String barcode;

    private String depno;

    private String potno;

    private String potname;

    private Integer potsn;

    private Date potscantime;

    private Date changetime;

    private String changeuserno;

    private String changeuser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPotid() {
        return potid;
    }

    public void setPotid(Integer potid) {
        this.potid = potid;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getDepno() {
        return depno;
    }

    public void setDepno(String depno) {
        this.depno = depno == null ? null : depno.trim();
    }

    public String getPotno() {
        return potno;
    }

    public void setPotno(String potno) {
        this.potno = potno == null ? null : potno.trim();
    }

    public String getPotname() {
        return potname;
    }

    public void setPotname(String potname) {
        this.potname = potname == null ? null : potname.trim();
    }

    public Integer getPotsn() {
        return potsn;
    }

    public void setPotsn(Integer potsn) {
        this.potsn = potsn;
    }

    public Date getPotscantime() {
        return potscantime;
    }

    public void setPotscantime(Date potscantime) {
        this.potscantime = potscantime;
    }

    public Date getChangetime() {
        return changetime;
    }

    public void setChangetime(Date changetime) {
        this.changetime = changetime;
    }

    public String getChangeuserno() {
        return changeuserno;
    }

    public void setChangeuserno(String changeuserno) {
        this.changeuserno = changeuserno == null ? null : changeuserno.trim();
    }

    public String getChangeuser() {
        return changeuser;
    }

    public void setChangeuser(String changeuser) {
        this.changeuser = changeuser == null ? null : changeuser.trim();
    }
}