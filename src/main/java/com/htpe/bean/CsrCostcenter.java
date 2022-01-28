package com.htpe.bean;

public class CsrCostcenter {
    private Integer id;

    private String centername;

    private String centerno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCentername() {
        return centername;
    }

    public void setCentername(String centername) {
        this.centername = centername == null ? null : centername.trim();
    }

    public String getCenterno() {
        return centerno;
    }

    public void setCenterno(String centerno) {
        this.centerno = centerno == null ? null : centerno.trim();
    }
}