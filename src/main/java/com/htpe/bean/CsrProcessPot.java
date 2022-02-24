package com.htpe.bean;

public class CsrProcessPot {
    private Integer id;

    private String disinfection;

    private String processno;

    private Process process;

    public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

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

}