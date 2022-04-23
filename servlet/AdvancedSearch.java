package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.pojo.Pojo;

/**
 * Servlet implementation class AdvancedSearch
 */
@WebServlet("/asearch")
public class AdvancedSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvancedSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		// TODO Auto-generated method stub
		PrintWriter pw;
		response.setContentType("application/json");
		pw = response.getWriter();
		
		ArrayList<Pojo> Invoices = new ArrayList<Pojo>(); 

		Integer sl_no;
		String business_code;
		// String business_name;
		Integer cust_number;
		// String name_customer;
		String clear_date;
		String buisness_year;
		String doc_id;
		String posting_date;
		String document_create_date;

		String due_in_date;
		String invoice_currency;
		String document_type;
		Integer posting_id;

		double total_open_amount;
		String baseline_create_date;
		String cust_payment_terms;
		Integer invoice_id;
		
		cust_number = Integer.parseInt(request.getParameter("cust_number"));
		doc_id = request.getParameter("doc_id");
		invoice_id =  Integer.parseInt(request.getParameter("invoice_id"));
		buisness_year = request.getParameter("buisness_year");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/grey_goose";
			String user = "root";
			String pass = "Ishita8376.";

			Connection con = DriverManager.getConnection(url, user, pass);
			String query = "select * from winter_internship WHERE cust_number = ? AND doc_id = ? AND invoice_id = ? AND buisness_year = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, cust_number);
			pstmt.setString(2, doc_id);
			pstmt.setInt(3, invoice_id);
			pstmt.setString(4, buisness_year);
			
		
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Pojo s = new Pojo();

				sl_no = rs.getInt("sl_no");
				business_code = rs.getString("business_code");
				// business_name = rs.getString("business_name");
				cust_number = rs.getInt("cust_number");
				// name_customer = rs.getString("name_customer");
				clear_date = rs.getString("clear_date");
				buisness_year = rs.getString("buisness_year");
				doc_id = rs.getString("doc_id");
				posting_date = rs.getString("posting_date");
				document_create_date = rs.getString("document_create_date");

				due_in_date = rs.getString("due_in_date");
				invoice_currency = rs.getString("invoice_currency");
				document_type = rs.getString("document_type");
				posting_id = rs.getInt("posting_id");

				total_open_amount = rs.getDouble("total_open_amount");
				baseline_create_date = rs.getString("baseline_create_date");
				cust_payment_terms = rs.getString("cust_payment_terms");
				invoice_id = rs.getInt("invoice_id");

				s.setSl_no(sl_no);
				s.setBusiness_code(business_code);
				// s.setBusiness_name(business_name);
				s.setCust_number(cust_number);
				// s.setName_customer(name_customer);
				s.setClear_date(clear_date);
				s.setBuisness_year(buisness_year);
				s.setDoc_id(doc_id);
				s.setPosting_date(posting_date);
				s.setDocument_create_date(document_create_date);
				// s.setDocument_create_date1(document_create_date1);
				s.setDue_in_date(due_in_date);
				s.setInvoice_currency(invoice_currency);
				s.setDocument_type(document_type);
				s.setPosting_id(posting_id);
				// s.setArea_business(area_business);
				s.setTotal_open_amount(total_open_amount);
				s.setBaseline_create_date(baseline_create_date);
				s.setCust_payment_terms(cust_payment_terms);
				s.setInvoice_id(invoice_id);

				Invoices.add(s);

			}
			Gson gson = new Gson();
			
			String respData = gson.toJson(Invoices);
			
			response.getWriter().print(respData);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		pw.close();

		for (Pojo pojodisplay : Invoices) {
			System.out.println(pojodisplay.toString());
		}
	}

}
