package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Delete
 */
@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		
		
		PrintWriter pw;
		response.setContentType("application/json");
		pw = response.getWriter();
		
		Integer sl_no = Integer.parseInt(request.getParameter("sl_no"));
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/grey_goose";
			String user = "root";
			String pass ="Ishita8376.";
			
			Connection con = DriverManager.getConnection(url, user, pass);
			String query = "delete from winter_internship where sl_no=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, sl_no);
			
			int result=pstmt.executeUpdate(); 
			
			if(result>0)    
            {    
            pw.println("Invoice successfully removed...");    
            }    
                
        }
		catch(Exception e)
		{    
            e.printStackTrace();
        }       
			
		 pw.close();
	}

}
