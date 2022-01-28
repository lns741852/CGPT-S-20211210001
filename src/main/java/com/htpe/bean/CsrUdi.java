package com.htpe.bean;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

public class CsrUdi {
    private Integer id;

    @NotEmpty(message = "代碼不能為空")
    private String code;

    @NotEmpty(message = "中文名稱不能為空")
    private String cname;

    private String ename;

    private String desc;

    private String picpath1;

    private String picpath2;

    private String picpath3;

    private String filepath1;

    private String filepath2;

    private String filepath3;

    private String publicType;

    private String updType;

    private String delType;

    private String datauser;

    private Date datadate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getPicpath1() {
        return picpath1;
    }

    public void setPicpath1(String picpath1) {
        this.picpath1 = picpath1 == null ? null : picpath1.trim();
    }

    public String getPicpath2() {
        return picpath2;
    }

    public void setPicpath2(String picpath2) {
        this.picpath2 = picpath2 == null ? null : picpath2.trim();
    }

    public String getPicpath3() {
        return picpath3;
    }

    public void setPicpath3(String picpath3) {
        this.picpath3 = picpath3 == null ? null : picpath3.trim();
    }

    public String getFilepath1() {
        return filepath1;
    }

    public void setFilepath1(String filepath1) {
        this.filepath1 = filepath1 == null ? null : filepath1.trim();
    }

    public String getFilepath2() {
        return filepath2;
    }

    public void setFilepath2(String filepath2) {
        this.filepath2 = filepath2 == null ? null : filepath2.trim();
    }

    public String getFilepath3() {
        return filepath3;
    }

    public void setFilepath3(String filepath3) {
        this.filepath3 = filepath3 == null ? null : filepath3.trim();
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