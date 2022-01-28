package com.htpe.bean;

public class CsrProcessPot {
    private Integer id;

    private String disinfection;

    private String processno;

    private String isselect;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisinfection() {
        return disinfection;
    }

    public void setDisinfection(String disinfection) {
        this.disinfection = disinfection == null ? null : disinfection.trim();
    }

    public String getProcessno() {
        return processno;
    }

    public void setProcessno(String processno) {
        this.processno = processno == null ? null : processno.trim();
    }

    public String getIsselect() {
        return isselect;
    }

    public void setIsselect(String isselect) {
        this.isselect = isselect == null ? null : isselect.trim();
    }
}