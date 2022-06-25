package com.qwerty1434.dart.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qwerty1434.dart.dto.UserDto;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/Api")
public class ApiController {

	@GetMapping("disclosure")
	public ResponseEntity<?> disclosure(String corp_name) throws IOException{
		// corp_name을 회사고유번호로 변경
		System.out.println(corp_name);
		
		// api 호출
		String urlstr = "https://opendart.fss.or.kr/api/list.json?crtfc_key=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx&bgn_de=20200117&end_de=20200117&corp_cls=Y&page_no=1&page_count=10";
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
		System.out.println(result.toString());
		
		if(result != null) {
			return new ResponseEntity<String>(result.toString(),HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("FAIL",HttpStatus.NO_CONTENT);
		}
	}
}
