package com.htpe.bean;

import javax.validation.constraints.NotEmpty;

public class CsrAccount {
    private Integer id;

    @NotEmpty(message = "員工編號不能為空")
    private String userno;

    @NotEmpty(message = "姓名不能為空")
    private String usercname;
    
    @NotEmpty(message = "密碼不能為空")
    private String userpwd;

    private String depno;

    private String systemright1;

    private String systemright2;

    private String systemright3;

    private String systemright4;

    private String systemright5;

    private String systemright6;

    private String systemright7;

    private String systemright8;

    private String systemright9;

    private String systemright10;

    private String systemright11;

    private String systemright12;

    private String systemright13;

    private String systemright14;

    private String systemright15;

    private String systemright16;

    private String systemright17;

    private String systemmanage1;

    private String systemmanage2;

    private String systemmanage3;

    private String systemmanage4;

    private String systemmanage5;

    private String systemmanage6;

    private String systemmanage7;

    private String systemmanage8;

    private String systemmanage9;

    private String systemmanage10;

    private String systemprivilege;

    private String createdatetime;

    private String iscancel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno == null ? null : userno.trim();
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


    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    public String getSystemright1() {
        return systemright1;
    }

    public void setSystemright1(String systemright1) {
        this.systemright1 = systemright1 == null ? null : systemright1.trim();
    }

    public String getSystemright2() {
        return systemright2;
    }

    public void setSystemright2(String systemright2) {
        this.systemright2 = systemright2 == null ? null : systemright2.trim();
    }

    public String getSystemright3() {
        return systemright3;
    }

    public void setSystemright3(String systemright3) {
        this.systemright3 = systemright3 == null ? null : systemright3.trim();
    }

    public String getSystemright4() {
        return systemright4;
    }

    public void setSystemright4(String systemright4) {
        this.systemright4 = systemright4 == null ? null : systemright4.trim();
    }

    public String getSystemright5() {
        return systemright5;
    }

    public void setSystemright5(String systemright5) {
        this.systemright5 = systemright5 == null ? null : systemright5.trim();
    }

    public String getSystemright6() {
        return systemright6;
    }

    public void setSystemright6(String systemright6) {
        this.systemright6 = systemright6 == null ? null : systemright6.trim();
    }

    public String getSystemright7() {
        return systemright7;
    }

    public void setSystemright7(String systemright7) {
        this.systemright7 = systemright7 == null ? null : systemright7.trim();
    }

    public String getSystemright8() {
        return systemright8;
    }

    public void setSystemright8(String systemright8) {
        this.systemright8 = systemright8 == null ? null : systemright8.trim();
    }

    public String getSystemright9() {
        return systemright9;
    }

    public void setSystemright9(String systemright9) {
        this.systemright9 = systemright9 == null ? null : systemright9.trim();
    }

    public String getSystemright10() {
        return systemright10;
    }

    public void setSystemright10(String systemright10) {
        this.systemright10 = systemright10 == null ? null : systemright10.trim();
    }

    public String getSystemright11() {
        return systemright11;
    }

    public void setSystemright11(String systemright11) {
        this.systemright11 = systemright11 == null ? null : systemright11.trim();
    }

    public String getSystemright12() {
        return systemright12;
    }

    public void setSystemright12(String systemright12) {
        this.systemright12 = systemright12 == null ? null : systemright12.trim();
    }

    public String getSystemright13() {
        return systemright13;
    }

    public void setSystemright13(String systemright13) {
        this.systemright13 = systemright13 == null ? null : systemright13.trim();
    }

    public String getSystemright14() {
        return systemright14;
    }

    public void setSystemright14(String systemright14) {
        this.systemright14 = systemright14 == null ? null : systemright14.trim();
    }

    public String getSystemright15() {
        return systemright15;
    }

    public void setSystemright15(String systemright15) {
        this.systemright15 = systemright15 == null ? null : systemright15.trim();
    }

    public String getSystemright16() {
        return systemright16;
    }

    public void setSystemright16(String systemright16) {
        this.systemright16 = systemright16 == null ? null : systemright16.trim();
    }

    public String getSystemright17() {
        return systemright17;
    }

    public void setSystemright17(String systemright17) {
        this.systemright17 = systemright17 == null ? null : systemright17.trim();
    }

    public String getSystemmanage1() {
        return systemmanage1;
    }

    public void setSystemmanage1(String systemmanage1) {
        this.systemmanage1 = systemmanage1 == null ? null : systemmanage1.trim();
    }

    public String getSystemmanage2() {
        return systemmanage2;
    }

    public void setSystemmanage2(String systemmanage2) {
        this.systemmanage2 = systemmanage2 == null ? null : systemmanage2.trim();
    }

    public String getSystemmanage3() {
        return systemmanage3;
    }

    public void setSystemmanage3(String systemmanage3) {
        this.systemmanage3 = systemmanage3 == null ? null : systemmanage3.trim();
    }

    public String getSystemmanage4() {
        return systemmanage4;
    }

    public void setSystemmanage4(String systemmanage4) {
        this.systemmanage4 = systemmanage4 == null ? null : systemmanage4.trim();
    }

    public String getSystemmanage5() {
        return systemmanage5;
    }

    public void setSystemmanage5(String systemmanage5) {
        this.systemmanage5 = systemmanage5 == null ? null : systemmanage5.trim();
    }

    public String getSystemmanage6() {
        return systemmanage6;
    }

    public void setSystemmanage6(String systemmanage6) {
        this.systemmanage6 = systemmanage6 == null ? null : systemmanage6.trim();
    }

    public String getSystemmanage7() {
        return systemmanage7;
    }

    public void setSystemmanage7(String systemmanage7) {
        this.systemmanage7 = systemmanage7 == null ? null : systemmanage7.trim();
    }

    public String getSystemmanage8() {
        return systemmanage8;
    }

    public void setSystemmanage8(String systemmanage8) {
        this.systemmanage8 = systemmanage8 == null ? null : systemmanage8.trim();
    }

    public String getSystemmanage9() {
        return systemmanage9;
    }

    public void setSystemmanage9(String systemmanage9) {
        this.systemmanage9 = systemmanage9 == null ? null : systemmanage9.trim();
    }

    public String getSystemmanage10() {
        return systemmanage10;
    }

    public void setSystemmanage10(String systemmanage10) {
        this.systemmanage10 = systemmanage10 == null ? null : systemmanage10.trim();
    }

    public String getSystemprivilege() {
        return systemprivilege;
    }

    public void setSystemprivilege(String systemprivilege) {
        this.systemprivilege = systemprivilege == null ? null : systemprivilege.trim();
    }

    public String getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(String createdatetime) {
        this.createdatetime = createdatetime;
    }

    public String getIscancel() {
        return iscancel;
    }

    public void setIscancel(String iscancel) {
        this.iscancel = iscancel == null ? null : iscancel.trim();
    }
}