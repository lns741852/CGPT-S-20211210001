package com.htpe.bean;

import javax.validation.constraints.NotEmpty;

public class CsrPrinter {
    private Integer id;

    @NotEmpty(message = "名稱不能為空")
    private String printername;

    private String depno;

    @NotEmpty(message = "IP不能為空")
    private String address;

    @NotEmpty(message = "安裝檔名稱不能為空")
    private String type;

    private String sn;

    @NotEmpty(message = "埠號不能為空")
    private Integer port;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrintername() {
        return printername;
    }

    public void setPrintername(String printername) {
        this.printername = printername == null ? null : printername.trim();
    }

    public String getDepno() {
        return depno;
    }

    public void setDepno(String depno) {
        this.depno = depno == null ? null : depno.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}