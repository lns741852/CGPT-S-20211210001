package com.htpe.bean;

import java.util.Date;
import java.util.List;

public class CsrUdiType {
    private Integer id;

    private String name;

    private String nameScientific;

    private String spec;

    private String description;

    private String datauser;

    private Date datadate;
    
    private List<CsrUdiImage> csrUdiImages;
    
    

    public List<CsrUdiImage> getCsrUdiImages() {
		return csrUdiImages;
	}

	public void setCsrUdiImages(List<CsrUdiImage> csrUdiImages) {
		this.csrUdiImages = csrUdiImages;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNameScientific() {
        return nameScientific;
    }

    public void setNameScientific(String nameScientific) {
        this.nameScientific = nameScientific == null ? null : nameScientific.trim();
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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