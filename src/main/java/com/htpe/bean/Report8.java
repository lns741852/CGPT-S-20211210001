package com.htpe.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Report8 {

	@Excel(name = "鍋別")
    private String potno;
	
	@Excel(name = "鍋次", width = 10)
    private Integer potsn;
	
	@Excel(name = "進鍋時間", format="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date machanicDurationStart;
	
	@Excel(name = "出鍋時間", format="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date machanicDurationEnd;
	
	@Excel(name = "滅菌物品數量")
    private Integer num;

	
	@Excel(name = "生物指示劑")
    private String rbiResult;
	
	@Excel(name = "第四第五化學指示劑")
    private String class5Result;
	
	@Excel(name = "抽真空")
    private String vacuumResult;
	
	@Excel(name = "植入物")
    private String implant;
	
	@Excel(name = "滅菌類別")
    private String potType;
	
	@Excel(name = "包外化學指示劑")
    private String externalIndicatorResult;
	
	@Excel(name = "包內化學指示劑")
    private String internalInicatorResult;
	
	@Excel(name = "最高壓力", groupName = "滅菌壓力", width = 10)
    private String machanicPressure;
	
	@Excel(name = "最高溫度", groupName = "滅菌溫度", width = 10)
    private String machanicTemperature;
	
	@Excel(name = "最低壓力" , groupName = "滅菌壓力", width = 10)
    private String machanicPressure1;
	
	@Excel(name = "最低溫度", groupName = "滅菌溫度", width = 10)
    private String machanicTemperature1;
	
	@Excel(name = "記錄人員", width = 20)
    private String indiusername;
	
	@Excel(name = "滅菌時間(小時)")
    private String rbitime;
	
	@Excel(name = "測漏數值")
    private String leakvalue;
	
	@Excel(name = "失敗")
    private String indicatornote;
	

    

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * @return the potno
	 */
	public String getPotno() {
		return potno;
	}

	/**
	 * @param potno the potno to set
	 */
	public void setPotno(String potno) {
		this.potno = potno==null? null:potno.trim();
	}

	/**
	 * @return the potsn
	 */
	public Integer getPotsn() {
		return potsn;
	}

	/**
	 * @param potsn the potsn to set
	 */
	public void setPotsn(Integer potsn) {
		this.potsn = potsn;
	}


	/**
	 * @return the rbiResult
	 */
	public String getRbiResult() {
		return rbiResult;
	}

	/**
	 * @param rbiResult the rbiResult to set
	 */
	public void setRbiResult(String rbiResult) {
		this.rbiResult = rbiResult==null? null:rbiResult.trim();
	}

	/**
	 * @return the class5Result
	 */
	public String getClass5Result() {
		return class5Result;
	}

	/**
	 * @param class5Result the class5Result to set
	 */
	public void setClass5Result(String class5Result) {
		this.class5Result = class5Result==null? null:class5Result.trim();
	}

	/**
	 * @return the vacuumResult
	 */
	public String getVacuumResult() {
		return vacuumResult;
	}

	/**
	 * @param vacuumResult the vacuumResult to set
	 */
	public void setVacuumResult(String vacuumResult) {
		this.vacuumResult = vacuumResult==null? null:vacuumResult.trim();
	}

	/**
	 * @return the implant
	 */
	public String getImplant() {
		return implant;
	}

	/**
	 * @param implant the implant to set
	 */
	public void setImplant(String implant) {
		this.implant = implant==null? null:implant.trim();
	}

	/**
	 * @return the machanicDurationStart
	 */
	public Date getMachanicDurationStart() {
		return machanicDurationStart;
	}

	/**
	 * @param machanicDurationStart the machanicDurationStart to set
	 */
	public void setMachanicDurationStart(Date machanicDurationStart) {
		this.machanicDurationStart = machanicDurationStart==null? null:machanicDurationStart;
	}

	/**
	 * @return the machanicDurationEnd
	 */
	public Date getMachanicDurationEnd() {
		return machanicDurationEnd;
	}

	/**
	 * @param machanicDurationEnd the machanicDurationEnd to set
	 */
	public void setMachanicDurationEnd(Date machanicDurationEnd) {
		this.machanicDurationEnd = machanicDurationEnd==null? null:machanicDurationEnd;
	}

	/**
	 * @return the potType
	 */
	public String getPotType() {
		return potType;
	}

	/**
	 * @param potType the potType to set
	 */
	public void setPotType(String potType) {
		
			switch (potType.trim()) {
			case "A":
				this.potType = "AOR";
				break;
			case "B":
				this.potType = "BOR";
				break;
			case "C":
				this.potType = "GOR";
				break;
			case "D":
				this.potType = "CSR";
				break;
			case "E":
				this.potType = "病房";
				break;
			case "F":
				this.potType = "布類";
				break;
			case "G":
				this.potType = "臉盆";
				break;
			case "H":
				this.potType = "皮消";
				break;
			case "I":
				this.potType = "刷子";
				break;
			case "J":
				this.potType = "燈把";
				break;
			case "K":
				this.potType = "其他";
				break;
			default :
				this.potType = potType==null? null:potType.trim();

		}
	
	}

	/**
	 * @return the externalIndicatorResult
	 */
	public String getExternalIndicatorResult() {
		return externalIndicatorResult;
	}

	/**
	 * @param externalIndicatorResult the externalIndicatorResult to set
	 */
	public void setExternalIndicatorResult(String externalIndicatorResult) {
		this.externalIndicatorResult = externalIndicatorResult==null? null:externalIndicatorResult.trim();
	}

	/**
	 * @return the internalInicatorResult
	 */
	public String getInternalInicatorResult() {
		return internalInicatorResult;
	}

	/**
	 * @param internalInicatorResult the internalInicatorResult to set
	 */
	public void setInternalInicatorResult(String internalInicatorResult) {
		this.internalInicatorResult = internalInicatorResult==null? null:internalInicatorResult.trim();
	}

	/**
	 * @return the machanicPressure
	 */
	public String getMachanicPressure() {
		return machanicPressure;
	}

	/**
	 * @param machanicPressure the machanicPressure to set
	 */
	public void setMachanicPressure(String machanicPressure) {
		this.machanicPressure = machanicPressure==null? null:machanicPressure.trim();
	}

	/**
	 * @return the machanicTemperature
	 */
	public String getMachanicTemperature() {
		return machanicTemperature;
	}

	/**
	 * @param machanicTemperature the machanicTemperature to set
	 */
	public void setMachanicTemperature(String machanicTemperature) {
		this.machanicTemperature = machanicTemperature==null? null:machanicTemperature.trim();
	}

	/**
	 * @return the machanicPressure1
	 */
	public String getMachanicPressure1() {
		return machanicPressure1;
	}

	/**
	 * @param machanicPressure1 the machanicPressure1 to set
	 */
	public void setMachanicPressure1(String machanicPressure1) {
		this.machanicPressure1 = machanicPressure1==null? null:machanicPressure1.trim();
	}

	/**
	 * @return the machanicTemperature1
	 */
	public String getMachanicTemperature1() {
		return machanicTemperature1;
	}

	/**
	 * @param machanicTemperature1 the machanicTemperature1 to set
	 */
	public void setMachanicTemperature1(String machanicTemperature1) {
		this.machanicTemperature1 = machanicTemperature1==null? null:machanicTemperature1.trim();
	}

	/**
	 * @return the indiusername
	 */
	public String getIndiusername() {
		return indiusername;
	}

	/**
	 * @param indiusername the indiusername to set
	 */
	public void setIndiusername(String indiusername) {
		this.indiusername = indiusername==null? null:indiusername.trim();
	}

	/**
	 * @return the rbitime
	 */
	public String getRbitime() {
		return rbitime;
	}

	/**
	 * @param rbitime the rbitime to set
	 */
	public void setRbitime(String rbitime) {		
			switch (rbitime) {
			case "2400":
				this.rbitime = "0.4";
				break;
			case "1250":
				this.rbitime = "0.25";
				break;
	
			default :
				this.rbitime = rbitime;
			}
	}

	/**
	 * @return the leakvalue
	 */
	public String getleakvalue() {
		return leakvalue;
	}

	/**
	 * @param leakvalue the leakvalue to set
	 */
	public void setleakvalue(String leakvalue) {
		this.leakvalue = leakvalue==null? null:leakvalue.trim();
	}

	/**
	 * @return the indicatornote
	 */
	public String getIndicatornote() {
		return indicatornote;
	}

	/**
	 * @param indicatornote the indicatornote to set
	 */
	public void setIndicatornote(String indicatornote) {
		this.indicatornote = indicatornote==null? null:indicatornote.trim();
	}
    
    
    
    

}
