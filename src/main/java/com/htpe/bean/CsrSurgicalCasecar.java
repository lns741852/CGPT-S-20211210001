package com.htpe.bean;

public class CsrSurgicalCasecar {
    private Integer id;

    private String surgicalno;

    private String casecarno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurgicalno() {
        return surgicalno;
    }

    public void setSurgicalno(String surgicalno) {
        this.surgicalno = surgicalno == null ? null : surgicalno.trim();
    }

    public String getCasecarno() {
        return casecarno;
    }

    public void setCasecarno(String casecarno) {
        this.casecarno = casecarno == null ? null : casecarno.trim();
    }
}