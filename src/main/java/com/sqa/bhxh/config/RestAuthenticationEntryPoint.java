//package com.sqa.bhxh.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sqa.bhxh.helper.ApiResponse;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStream;
//
//@Component
//public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{
//
//	@Override
//	public void commence(HttpServletRequest request, HttpServletResponse response,
//						 AuthenticationException authException) throws IOException {
//		// TODO Auto-generated method stub
//		ApiResponse res = new ApiResponse(401, "Unauthorised");
//		res.setErrors(authException.getMessage());
//		res.setStatus(false);
//		OutputStream out = response.getOutputStream();
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.writeValue(out, res);
//		out.flush();
//	}
//
//}