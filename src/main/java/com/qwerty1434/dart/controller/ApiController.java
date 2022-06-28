package com.qwerty1434.dart.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qwerty1434.dart.dao.CorpCode;
import com.qwerty1434.dart.dto.CorpCodeDto;
import com.qwerty1434.dart.dto.UserDto;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/Api")
public class ApiController {
	
	@Autowired
	private CorpCode corpcode;

	@GetMapping("Disclosure") // 공시검색
	public ResponseEntity<?> disclosure(String corp_name) throws IOException, SQLException{
		// corp_name을 회사고유번호로 변경
		
		String corp_code = Code_To_Name(corp_name);
		// api 호출
		String urlstr = "https://opendart.fss.or.kr/api/list.json?crtfc_key=48320d3539dc2b7ee4fad5437667d93177b8027e&bgn_de=20200117&end_de=20200117&corp_cls=Y&page_no=1&page_count=10&corp_code="+corp_code;
		StringBuffer result = Get_Api_Data(urlstr);
		if(result != null) {
			return new ResponseEntity<String>(result.toString(),HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("FAIL",HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("BalanceSheet") // 단일회사 전체 재무제표
	public ResponseEntity<?> BalanceSheet(String corp_name,String bsns_year,String reprt_code, String fs_div) throws IOException, SQLException{
		
		// corp_name을 회사고유번호로 변경		
		String corp_code = Code_To_Name(corp_name);
		// api 호출
		String urlstr = String.format("https://opendart.fss.or.kr/api/fnlttSinglAcnt.json?crtfc_key=48320d3539dc2b7ee4fad5437667d93177b8027e&bsns_year=%s&reprt_code=%s&corp_code=%s&fs_div=%s",bsns_year,reprt_code,corp_code,fs_div);
		StringBuffer result = Get_Api_Data(urlstr);
		
		if(result != null) {
			return new ResponseEntity<String>(result.toString(),HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("FAIL",HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("ShareDisclosure") // 지분공시 종합정보 (대량보유 상황보고, 임원 및 주요주주 소유보고)
	public ResponseEntity<?> ShareDisclosure(String corp_name) throws IOException, SQLException{
		String corp_code = Code_To_Name(corp_name);
		String urlstr1 = String.format("https://opendart.fss.or.kr/api/majorstock.json?crtfc_key=48320d3539dc2b7ee4fad5437667d93177b8027e&corp_code=%s",corp_code);
		String urlstr2 = String.format("https://opendart.fss.or.kr/api/elestock.json?crtfc_key=48320d3539dc2b7ee4fad5437667d93177b8027e&corp_code=%s",corp_code);
		StringBuffer result = new StringBuffer();
		result.append(Get_Api_Data(urlstr1));
		result.append(Get_Api_Data(urlstr2));
		if(result != null) {
			return new ResponseEntity<String>(result.toString(),HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("FAIL",HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("KeyPointsReport")
	public ResponseEntity<?> KeyPointsReport(String corp_name, String bgn_de, String end_de) throws IOException, SQLException{
		String corp_code = Code_To_Name(corp_name);
		
		List<String> url_list = new ArrayList<String>();
		url_list.add("dfOcr");
		url_list.add("bsnSp");
		url_list.add("ctrcvsBgrq");
		url_list.add("dsRsOcr");
		url_list.add("piicDecsn");
		url_list.add("fricDecsn");
		url_list.add("pifricDecsn");
		url_list.add("crDecsn");
		url_list.add("bnkMngtPcbg");
		url_list.add("lwstLg");

		url_list.add("ovLstDecsn");
		url_list.add("ovDlstDecsn");
		url_list.add("ovLst");
		url_list.add("ovDlst");
		url_list.add("cvbdIsDecsn");
		url_list.add("bdwtIsDecsn");
		url_list.add("exbdIsDecsn");
		url_list.add("bnkMngtPcsp");
		url_list.add("wdCocobdIsDecsn");
		url_list.add("astInhtrfEtcPtbkOpt");

		url_list.add("otcprStkInvscrTrfDecsn");
		url_list.add("tgastTrfDecsn");
		url_list.add("tgastInhDecsn");
		url_list.add("otcprStkInvscrInhDecsn");
		url_list.add("bsnTrfDecsn");
		url_list.add("bsnInhDecsn");
		url_list.add("tsstkAqTrctrCcDecsn");
		url_list.add("tsstkAqTrctrCnsDecsn");
		url_list.add("tsstkDpDecsn");
		url_list.add("tsstkAqDecsn");
		
		url_list.add("stkExtrDecsn");
		url_list.add("cmpDvmgDecsn");
		url_list.add("cmpDvDecsn");
		url_list.add("cmpMgDecsn");
		url_list.add("stkrtbdInhDecsn");
		url_list.add("stkrtbdTrfDecsn");
		
		StringBuffer result = new StringBuffer();
		for (String url_name : url_list) {
			String urlstr = String.format("https://opendart.fss.or.kr/api/%s.json?crtfc_key=48320d3539dc2b7ee4fad5437667d93177b8027e&corp_code=%s&bgn_de=%s&end_de=%s",url_name,corp_code,bgn_de,end_de);
			result.append(Get_Api_Data(urlstr));			
		}
		
		if(result != null) {
			return new ResponseEntity<String>(result.toString(),HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("FAIL",HttpStatus.NO_CONTENT);
		}

	}
	
	public String Code_To_Name(String corp_name) throws IOException, SQLException {
		List<CorpCodeDto> result = corpcode.search(corp_name);
		return result.get(0).getCorp_code();
	}
	
	public StringBuffer Get_Api_Data(String urlstr) throws IOException {
		URL url = new URL(urlstr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		
		StringBuffer result = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		
		String line;
		while((line = br.readLine()) != null) {
			result.append(line+"\n");
		}
		br.close();
		conn.disconnect();
		
		// json형태로 파싱해서 status가 000(정상)일때만 넘기기
		ObjectMapper mapper = new ObjectMapper();		
		JsonNode node = mapper.readTree(result.toString());
		if(node.get("status").asText().equals("000")) {
			return result;
		}else {
			System.out.println("에러: "+result.toString());
			return new StringBuffer();
		}
		
	}
}
