package com.myhome.contractor.upload;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/contractor/upload-lessor.do")
public class UploadLessor extends HttpServlet{

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//http://localhost:8090/Myhome_project/contractor/upload-lessor.do
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/contractor/upload-lessor.jsp");
		dispatcher.forward(request, response);
		
	}
}