//package com.htpe;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.htpe.bean.CsrAccount;
//import com.htpe.mapper.nnew.CsrAccountMapper;
//import com.htpe.mapper.nnew.DataChangeNew;
//import com.htpe.mapper.old.DataChange;
//
//
//
//@SpringBootTest
//class NewHtpeApplicationTests {
//	
//	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSSSSS" );
//	
//	@Autowired
//	DataChange dataChange;
//	
//	@Autowired
//	DataChangeNew dataChangeNew;
//	
//	@Autowired
//	CsrAccountMapper csrAccountMapper;
//	
//	
//	@Test
//	public void test() {
//		
//		CsrAccount accountByUserno = csrAccountMapper.getAccountByUserno("1111");
//		
//		System.out.println(accountByUserno);
//	}
//	
//	
//	/**
//	 * 
//	 * �L�]���J
//	 */
//
//	@Test
//	public void setDataChange() {
//		List<Map<String, Object>> listSetData = dataChange.listSetData();
//		for (Map<String, Object> setData : listSetData) {
//			 String setno = (String)setData.get("SETNO");
//			 setno = setno.trim();
//			 String setnamech = (String)setData.get("SETNAMECH");
//			 setnamech = setnamech.trim();
//			 String SETNAME = (String)setData.get("SETNAME");
//			 SETNAME= SETNAME.trim();
//
//			 String SETTYPE = (String)setData.get("SETTYPE");
//			 SETTYPE =  SETTYPE.trim();
//			 String UNIT = (String)setData.get("UNIT");
//			 UNIT = UNIT.trim();
//			 String ISDELETE = (String)setData.get("ISDELETE");
//			 ISDELETE = ISDELETE.trim();
//
//			 
//			 Integer PRICE = (Integer)setData.get("PRICE");
//			 Integer NUMBASE_CSR = (Integer)setData.get("NUMBASE_CSR");
//			 Integer NUMBASE_CSR2 = (Integer)setData.get("NUMBASE_CSR2");
//			 Integer NUMBASE_OR1 = (Integer)setData.get("NUMBASE_OR1");
//			 Integer NUMBASE_OR2 = (Integer)setData.get("NUMBASE_OR2");
//			 Integer NUMBASE_OR3 = (Integer)setData.get("NUMBASE_OR3");
//		
//			 String SPOTNO = (String)setData.get("SPOTNO");
//			 SPOTNO = SPOTNO.trim();
//			 String SPROCESS = (String)setData.get("SPROCESS");
//			 SPROCESS = SPROCESS.trim();
//			 Integer SDAY = (Integer)setData.get("SDAY");
//			 String SPRINTNO = (String)setData.get("SPRINTNO");
//			 SPRINTNO = SPRINTNO.trim();
//			 String POSITION_ID = (String)setData.get("POSITION_ID");
//			 POSITION_ID = POSITION_ID.trim();
//			 Integer NUMBASE_WR1 = (Integer)setData.get("NUMBASE_WR1");
//			 
//			Map<String, Object> map = new HashMap<String, Object>();
//			
//
//			map.put("SETNO", setno);
//			map.put("SETNAMECH", setnamech);
//			map.put("SETNAME", SETNAME);
//			map.put("SETTYPE", SETTYPE);
//			map.put("ISDELETE", ISDELETE);
//			map.put("PRICE", PRICE);
//			map.put("NUMBASE_CSR", NUMBASE_CSR);
//			map.put("NUMBASE_CSR2", NUMBASE_CSR2);			
//			map.put("NUMBASE_OR1", NUMBASE_OR1);
//			map.put("NUMBASE_OR2", NUMBASE_OR2);
//			map.put("NUMBASE_OR3", NUMBASE_OR3);
//			map.put("NUMBASE_WR1", NUMBASE_WR1);
//			map.put("SPOTNO", SPOTNO);
//			map.put("SPROCESS", SPROCESS);
//			map.put("SDAY", SDAY);
//			map.put("SPRINTNO", SPRINTNO);
//			map.put("POSITION_ID", POSITION_ID);
//			 
//			 
//			dataChangeNew.insertSetNo(map);
//			
//		}
//		
//	}
//
//	/**
//	 * 
//	 * ���񴡤J
//	 * @throws ParseException 
//	 */
//	
//	@Test
//	public void UDIDataChange() throws ParseException {
//			List<Map<String, Object>> listUDI = dataChange.listUDI();
//			for (Map<String, Object> udi : listUDI) {
//				Date d = sdf.parse("0001-01-01-00.00.00.000000");
//				String CODE = (String)udi.get("CODE");
//				CODE = CODE.trim();
//				String CNAME = (String)udi.get("CNAME");
//				CNAME = CNAME.trim();
//				String ENAME = (String)udi.get("ENAME");
//				ENAME = ENAME.trim();
//				String DESC = (String)udi.get("DESC");
//				DESC = DESC.trim();
//				String PICPATH1 = (String)udi.get("PICPATH1");
//				PICPATH1 = PICPATH1.trim();
//				String PICPATH2 = (String)udi.get("PICPATH2");
//				PICPATH2 = PICPATH2.trim();
//				String PICPATH3 = (String)udi.get("PICPATH3");
//				PICPATH3 = PICPATH3.trim();
//				String FILEPATH1 = (String)udi.get("FILEPATH1");
//				FILEPATH1 = FILEPATH1.trim();		
//				String FILEPATH2 = (String)udi.get("FILEPATH2");
//				FILEPATH2 = FILEPATH2.trim();
//				String FILEPATH3 = (String)udi.get("FILEPATH3");
//				FILEPATH3 = FILEPATH3.trim();
//				String PUBLIC_TYPE = (String)udi.get("PUBLIC_TYPE");
//				PUBLIC_TYPE = PUBLIC_TYPE.trim();
//				String UPD_TYPE = (String)udi.get("UPD_TYPE");
//				UPD_TYPE = UPD_TYPE.trim();
//				String DEL_TYPE = (String)udi.get("DEL_TYPE");
//				DEL_TYPE = DEL_TYPE.trim();
//				String MOUSER = (String)udi.get("CRUSER");
//				MOUSER = MOUSER.trim();
//				String MODATE = (String)udi.get("CRDATE");
//				if(!MODATE.equals("")) {
//					sdf.parse(MODATE);
//				}
//
//				
//
//				Map<String, Object> map1 = new HashMap<String, Object>();
//				
//				map1.put("CODE", CODE);
//				map1.put("CNAME", CNAME);
//				map1.put("ENAME", ENAME);
//				map1.put("DESC", DESC);
//				map1.put("PICPATH1", PICPATH1);
//				map1.put("PICPATH2", PICPATH2);
//				map1.put("PICPATH3", PICPATH3);
//				map1.put("FILEPATH1", FILEPATH1);			
//				map1.put("FILEPATH2", FILEPATH2);
//				map1.put("FILEPATH3", FILEPATH3);
//				map1.put("PUBLIC_TYPE", PUBLIC_TYPE);
//				map1.put("UPD_TYPE", UPD_TYPE);
//				map1.put("DEL_TYPE", DEL_TYPE);
//				map1.put("DATAUSER", MOUSER);
//				map1.put("DATADATE", d);
//				
//				dataChangeNew.insertUDI(map1);
//
//	
//			}
//	}
//	
//	/**
//	 * 
//	 * ����_�L�]�Ǹ����J
//	 */
//	
//	@Test
//	public void seqUDIChange() {
//			List<Map<String, Object>> listSeqUDI = dataChange.listSetDataSeqUDI();
//			for (Map<String, Object> seqUDI : listSeqUDI) {
//				String seqID = (String)seqUDI.get("CSR_SETDATA_SEQ_ID");
//				seqID = seqID.trim();
//				String UDIID = (String)seqUDI.get("CSR_UDI_ID");
//				UDIID = UDIID.trim();
//
//				Map<String, Object> map3 = new HashMap<String, Object>();
//				
//				map3.put("CSR_SETDATA_SEQ_ID", seqID);
//				map3.put("CSR_UDI_ID", UDIID);
//
//				dataChangeNew.insertSeqUDI(map3);
//
//	
//			}
//	}
//	
//	/**
//	 * 
//	 * �L�]�Ǹ����J
//	 * @throws ParseException 
//	 */
//	
//	
//	@Test
//	public void seqChange() throws ParseException {
//		List<Map<String, Object>> listSeq = dataChange.listSetDataSeq();
//		for (Map<String, Object> seq : listSeq) {
//			Date d = sdf.parse("0001-01-01-00.00.00.000000");
//			String SETNO = (String)seq.get("SETNO");
//			SETNO = SETNO.trim();
//			String SETNAMECH = (String)seq.get("SETNAMECH");
//			SETNAMECH = SETNAMECH.trim();
//			String SEQ = (String)seq.get("SEQ");
//			SEQ = SEQ.trim();
//			String PUBLIC_TYPE = (String)seq.get("PUBLIC_TYPE");
//			PUBLIC_TYPE = PUBLIC_TYPE.trim();
//			String UPD_TYPE = (String)seq.get("UPD_TYPE");
//			UPD_TYPE = UPD_TYPE.trim();
//			String DEL_TYPE = (String)seq.get("DEL_TYPE");
//			DEL_TYPE = DEL_TYPE.trim();
//			String CRUSER = (String)seq.get("CRUSER");
//			CRUSER = CRUSER.trim();
//			String CRDATE = (String)seq.get("CRDATE");
//			CRDATE = CRDATE.trim();
//			if(!CRDATE.equals("")) {
//				sdf.parse(CRDATE);
//			}
//
//
//
//			Map<String, Object> map3 = new HashMap<String, Object>();
//			
//			map3.put("SETNO", SETNO);
//			map3.put("SETNAMECH", SETNAMECH);
//			map3.put("SEQ", SEQ);
//			map3.put("PUBLIC_TYPE", PUBLIC_TYPE);
//			map3.put("UPD_TYPE", UPD_TYPE);
//			map3.put("DEL_TYPE", DEL_TYPE);
//			map3.put("DATAUSER", CRUSER);
//			map3.put("DATADATE", d);
//
//			dataChangeNew.insertSeq(map3);
//
//
//		}
//	}
//	
//	/**
//	 * 
//	 * �Ӯר�_�L�]���J
//	 */
//	
//	@Test
//	public void casecarSetdataChange() {		
//		List<Map<String, Object>> listCasecarSetdata = dataChange.listCasecarData();
//		for (Map<String, Object> carSetdata : listCasecarSetdata) {
//			String CASECAR_ID = (String)carSetdata.get("CASECAR_ID");
//			CASECAR_ID = CASECAR_ID.trim();
//			String SETNO_ID = (String)carSetdata.get("SETNO_ID");
//			SETNO_ID = SETNO_ID.trim();
//			Integer NUM = (Integer)carSetdata.get("NUM");
//	
//
//			Map<String, Object> map3 = new HashMap<String, Object>();
//			
//			map3.put("CASECAR_ID", CASECAR_ID);
//			map3.put("SETNO_ID", SETNO_ID);
//			map3.put("NUM", NUM);
//		
//			dataChangeNew.insertCasecarSetdata(map3);
//		}
//	}
//	
//	/**
//	 * 
//	 * �Ӯר����J
//	 */
//	
//	@Test
//	public void casercarChange() {
//		List<Map<String, Object>> listCasecar = dataChange.listCasecar();
//		for (Map<String, Object> car : listCasecar) {
//			String CASECARNO = (String)car.get("CASECARNO");
//			CASECARNO = CASECARNO.trim();
//			String DATAUSERNO = (String)car.get("DATAUSERNO");
//			DATAUSERNO = DATAUSERNO.trim();
//			String DATAUSERNAME = (String)car.get("DATAUSERNAME");
//			DATAUSERNAME = DATAUSERNAME.trim();
//			Date DATADATE = (Date)car.get("DATADATE");
//			String ISDELETE = (String)car.get("ISDELETE");
//			ISDELETE = ISDELETE.trim();
//
//	
//
//			Map<String, Object> map3 = new HashMap<String, Object>();
//			
//			map3.put("CASECARNO", CASECARNO);
//			map3.put("DATAUSERNO", DATAUSERNO);
//			map3.put("DATAUSERNAME", DATAUSERNAME);
//			map3.put("DATADATE", DATADATE);
//			map3.put("ISDELETE", ISDELETE);
//		
//			dataChangeNew.insertCasecar(map3);
//		}
//	}
//	
//	/**
//	 * 
//	 * ��N���J
//	 */
//	
//	@Test
//	public void surgicalChange() {
//		List<Map<String, Object>> listSurgical = dataChange.listSurgical();
//		for (Map<String, Object> Surgcal : listSurgical) {
//			String SURGICALNO = (String)Surgcal.get("SURGICALNO");
//			SURGICALNO = SURGICALNO.trim();
//			String SURGICALNAME = (String)Surgcal.get("SURGICALNAME");
//			SURGICALNAME = SURGICALNAME.trim();
//			String SURGICALNAMECH = (String)Surgcal.get("SURGICALNAMECH");
//			SURGICALNAMECH = SURGICALNAMECH.trim();
//			String ISDELETE = (String)Surgcal.get("ISDELETE");
//			ISDELETE = ISDELETE.trim();
//			String DATAUSERNO = (String)Surgcal.get("DATAUSERNO");
//			DATAUSERNO = DATAUSERNO.trim();
//			String DATAUSERNAME = (String)Surgcal.get("DATAUSERNAME");
//			DATAUSERNAME = DATAUSERNAME.trim();
//			Date DATADATE = (Date)Surgcal.get("DATADATE");
//
//			Map<String, Object> map3 = new HashMap<String, Object>();
//			
//			map3.put("SURGICALNO", SURGICALNO);
//			map3.put("SURGICALNAME", SURGICALNAME);
//			map3.put("SURGICALNAMECH", SURGICALNAMECH);
//			map3.put("ISDELETE", ISDELETE);
//			map3.put("DATAUSERNO", DATAUSERNO);
//			map3.put("DATAUSERNAME", DATAUSERNAME);
//			map3.put("DATADATE", DATADATE);
//
//		
//			dataChangeNew.insertSurgical(map3);
//		}
//	}
//
//	/**
//	 * 
//	 * ��N_�Ӯר����J
//	 */
//	
//	@Test
//	public void surgicalCarChange() {
//		List<Map<String, Object>> listCarSurgical = dataChange.listSurgicallCasecar();
//		for (Map<String, Object> carSurgcal : listCarSurgical) {
//			String SURGICALNO = (String)carSurgcal.get("SURGICALNO");
//			SURGICALNO = SURGICALNO.trim();
//			String CASECARNO = (String)carSurgcal.get("CASECARNO");
//			CASECARNO = CASECARNO.trim();
//
//
//			Map<String, Object> map3 = new HashMap<String, Object>();
//			
//			map3.put("SURGICALNO", SURGICALNO);
//			map3.put("CASECARNO", CASECARNO);
//		
//			dataChangeNew.insertCarSurgical(map3);
//		}
//	}
//	
//	/**
//	 * 
//	 * �������J
//	 */
//	
//	@Test
//	public void depnoChange() {
//		List<Map<String, Object>> listDepno = dataChange.listDepno();
//		for (Map<String, Object> depno : listDepno) {
//			String DEPNO = (String)depno.get("DEPNO");
//			DEPNO = DEPNO.trim();
//			String DEPNAME = (String)depno.get("DEPNAME");
//			DEPNAME = DEPNAME.trim();
//			String DEPNAMECH = (String)depno.get("DEPNAMECH");
//			DEPNAMECH = DEPNAMECH.trim();
//			String ISDELETE = (String)depno.get("ISDELETE");
//			ISDELETE = ISDELETE.trim();
//			String DATAUSERNO = (String)depno.get("DATAUSERNO");
//			DATAUSERNO = DATAUSERNO.trim();
//			String DATAUSERNAME = (String)depno.get("DATAUSERNAME");
//			DATAUSERNAME = DATAUSERNAME.trim();
//			Date DATADATE = (Date)depno.get("DATADATE");
//			String TYPE = (String)depno.get("TYPE");
//			TYPE = TYPE.trim();
//
//			
//
//
//			Map<String, Object> map3 = new HashMap<String, Object>();
//			
//			map3.put("DEPNO", DEPNO);
//			map3.put("DEPNAME", DEPNAME);
//			map3.put("DEPNAMECH", DEPNAMECH);
//			map3.put("ISDELETE", ISDELETE);
//			map3.put("DATAUSERNO", DATAUSERNO);
//			map3.put("DATAUSERNAME", DATAUSERNAME);
//			map3.put("DATADATE", DATADATE);
//			map3.put("TYPE", TYPE);
//		
//			dataChangeNew.insertDepno(map3);
//		}
//	}
//	
//	/**
//	 * 
//	 * ���r�紡�J
//	 */
//	
//	@Test
//	public void potdataChange() {
//		List<Map<String, Object>> listPotdata = dataChange.listPotdata();
//		for (Map<String, Object> potData : listPotdata) {
//			String DISINFECTION = (String)potData.get("DISINFECTION");
//			DISINFECTION = DISINFECTION.trim();
//			String POTNAME = (String)potData.get("POTNAME");
//			POTNAME = POTNAME.trim();
//			String ISDELETE = (String)potData.get("ISDELETE");
//			ISDELETE = ISDELETE.trim();
//			String DATAUSERNO = (String)potData.get("DATAUSERNO");
//			DATAUSERNO = DATAUSERNO.trim();
//			String DATAUSERNAME = (String)potData.get("DATAUSERNAME");
//			DATAUSERNAME = DATAUSERNAME.trim();
//			Date DATADATE = (Date)potData.get("DATADATE");
//
//			
//
//
//			Map<String, Object> map3 = new HashMap<String, Object>();
//			
//			map3.put("DISINFECTION", DISINFECTION);
//			map3.put("POTNAME", POTNAME);
//			map3.put("ISDELETE", ISDELETE);
//			map3.put("DATAUSERNO", DATAUSERNO);
//			map3.put("DATAUSERNAME", DATAUSERNAME);
//			map3.put("DATADATE", DATADATE);
//		
//			dataChangeNew.insertPotdata(map3);
//		}
//	}
//	
//	
//	/**
//	 * 
//	 * ���r�紡�J
//	 */
//	
//	@Test
//	public void potProcessChange() {
//		List<Map<String, Object>> listPotdataProcess = dataChange.listPotdataProcess();
//		for (Map<String, Object> potDataProcess : listPotdataProcess) {
//			String DISINFECTION = (String)potDataProcess.get("DISINFECTION");
//			DISINFECTION = DISINFECTION.trim();
//			String PROCESSNO = (String)potDataProcess.get("PROCESSNO");
//			PROCESSNO = PROCESSNO.trim();
//			
//			Map<String, Object> map3 = new HashMap<String, Object>();
//			
//			map3.put("DISINFECTION", DISINFECTION);
//			map3.put("PROCESSNO", PROCESSNO);
//		
//			dataChangeNew.insertPotdataProcess(map3);
//		}
//	}
//	
//	/**
//	 * 
//	 * ���r�]���J
//	 */
//	
//	@Test
//	public void pocessChange() {
//		List<Map<String, Object>> listProcess = dataChange.listProcess();
//		for (Map<String, Object> process : listProcess) {
//			String PROCESSNO = (String)process.get("PROCESSNO");
//			PROCESSNO = PROCESSNO.trim();
//			String PROCESSNAME = (String)process.get("PROCESSNAME");
//			PROCESSNAME = PROCESSNAME.trim();
//			Integer DAY = (Integer)process.get("DAY");
//			String DATENAME = (String)process.get("DATENAME");
//			DATENAME = DATENAME.trim();
//			String ISDELETE = (String)process.get("ISDELETE");
//			ISDELETE = ISDELETE.trim();
//			String DATAUSERNO = (String)process.get("DATAUSERNO");
//			DATAUSERNO = DATAUSERNO.trim();
//			String DATAUSERNAME = (String)process.get("DATAUSERNAME");
//			DATAUSERNAME = DATAUSERNAME.trim();
//			Date DATADATE = (Date)process.get("DATADATE");
//
//			
//
//
//			Map<String, Object> map3 = new HashMap<String, Object>();
//			
//			map3.put("PROCESSNO", PROCESSNO);
//			map3.put("PROCESSNAME", PROCESSNAME);
//			map3.put("DAY", DAY);
//			map3.put("DATENAME", DATENAME);
//			map3.put("ISDELETE", ISDELETE);
//			map3.put("DATAUSERNO", DATAUSERNO);
//			map3.put("DATAUSERNAME", DATAUSERNAME);
//			map3.put("DATADATE", DATADATE);
//		
//			dataChangeNew.insertProcess(map3);
//		}
//	}
//	
//	
//	/**
//	 * 
//	 * �x�촡�J
//	 * @throws ParseException 
//	 */
//	
//	@Test
//	public void positionChange() throws ParseException {
//		List<Map<String, Object>> listPosition = dataChange.listPosition();
//		for (Map<String, Object> position : listPosition) {
//			String CODE = (String)position.get("CODE");
//			CODE = CODE.trim();
//			String NAME = (String)position.get("NAME");
//			NAME = NAME.trim();
//			String PUBLIC_TYPE = (String)position.get("PUBLIC_TYPE");
//			PUBLIC_TYPE = PUBLIC_TYPE.trim();
//			String UPD_TYPE = (String)position.get("UPD_TYPE");
//			UPD_TYPE = UPD_TYPE.trim();
//			String DEL_TYPE = (String)position.get("DEL_TYPE");
//			DEL_TYPE = DEL_TYPE.trim();
//			String DATAUSER = (String)position.get("CRUSER");
//			DATAUSER = DATAUSER.trim();
//			String DATADATE = (String)position.get("CRDATE");
//			
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSSSSS");
//			Date date = dateFormat.parse(DATADATE);
//
//			Map<String, Object> map3 = new HashMap<String, Object>();
//			
//			map3.put("CODE", CODE);
//			map3.put("NAME", NAME);
//			map3.put("PUBLIC_TYPE", PUBLIC_TYPE);
//			map3.put("UPD_TYPE", UPD_TYPE);
//			map3.put("DEL_TYPE", DEL_TYPE);
//			map3.put("DATAUSER", DATAUSER);
//			map3.put("DATADATE", date);
//		
//			dataChangeNew.insertPosition(map3);
//		}
//	}
//	
//	/**
//	 * 
//	 * �x�촡�J
//	 * @throws ParseException 
//	 */
//	
//	@Test
//	public void potDepnoChange() throws ParseException {
//		List<Map<String, Object>> listPotDepno = dataChange.listPotDepno();
//		for (Map<String, Object> potDepno : listPotDepno) {
//			String DEPNO = (String)potDepno.get("DEPNO");
//			DEPNO = DEPNO.trim();
//			String DISINFECTION = (String)potDepno.get("DISINFECTION");
//			DISINFECTION = DISINFECTION.trim();
//			Integer POTNUM = (Integer)potDepno.get("POTNUM");
//			Integer BEGINNUM = (Integer)potDepno.get("BEGINNUM");
//			String HEAD = (String)potDepno.get("HEAD");
//			HEAD = HEAD.trim();
//
//			Map<String, Object> map3 = new HashMap<String, Object>();
//			
//			map3.put("DEPNO", DEPNO);
//			map3.put("DISINFECTION", DISINFECTION);
//			map3.put("POTNUM", POTNUM);
//			map3.put("BEGINNUM", BEGINNUM);
//			map3.put("HEAD", HEAD);
//		
//			dataChangeNew.insertPotDepno(map3);
//		}
//	}
//	
//	/**
//	 * 
//	 * �x�촡�J
//	 * @throws ParseException 
//	 */
//	
//	@Test
//	public void printerChange() throws ParseException {
//		List<Map<String, Object>> listPrinter = dataChange.listPrinter();
//		for (Map<String, Object> printer : listPrinter) {
//			String PRINTERNAME = (String)printer.get("PRINTERNAME");
//			PRINTERNAME = PRINTERNAME.trim();
//			String DEPNO = (String)printer.get("DEPNO");
//			DEPNO = DEPNO.trim();
//			String ADDRESS = (String)printer.get("ADDRESS");
//			ADDRESS = ADDRESS.trim();
//			String TYPE = (String)printer.get("TYPE");
//			TYPE = TYPE.trim();
//			String SN = (String)printer.get("SN");
//			SN = SN.trim();
//			Integer PORT = (Integer)printer.get("PORT");
//	
//	
//			Map<String, Object> map3 = new HashMap<String, Object>();
//			
//			map3.put("PRINTERNAME", PRINTERNAME);
//			map3.put("DEPNO", DEPNO);
//			map3.put("ADDRESS", ADDRESS);
//			map3.put("TYPE", TYPE);
//			map3.put("SN", SN);
//			map3.put("PORT", PORT);
//		
//			dataChangeNew.insertPrinter(map3);
//		}
//	}
//	
//	
//	
//}
