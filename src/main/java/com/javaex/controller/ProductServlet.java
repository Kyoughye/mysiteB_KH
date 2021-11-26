package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.ProductDao;
import com.javaex.dao.ProductDaoImpl;
import com.javaex.util.WebUtil;
import com.javaex.vo.ProductVo;

@WebServlet("/product")
public class ProductServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
			
		System.out.println("ProductServlet.doGet() 호출");
		request.setCharacterEncoding("utf-8");

		String actionName = request.getParameter("a");

		System.out.println("a -> " + actionName);

		// 등록하기
		if ("insertProduct".equals(actionName)) {
			String proName = request.getParameter("proName");
			String proCateg = request.getParameter("proCateg");
			int proStock = Integer.parseInt(request.getParameter("proStock"));
			int proPrice = Integer.parseInt(request.getParameter("proPrice"));
			String proDesc = request.getParameter("proDesc");
			int proOnSale = Integer.parseInt(request.getParameter("proOnSale"));
			String proFileName = request.getParameter("proFileName");
			int proHit = Integer.parseInt(request.getParameter("proHit"));

			ProductVo vo = new ProductVo(proName, proCateg, proStock, proPrice, proDesc, proOnSale, proFileName,
					proHit);
			ProductDao dao = new ProductDaoImpl();
			//dao.insert(vo);
			WebUtil.forward(request, response, "/WEB-INF/views/product/-.jsp");
		}
		// 조회, 검색
		else if ("productList".equals(actionName)) {

			String pG = request.getParameter("page");
			String sF = request.getParameter("searchFrom");
			String kwD = request.getParameter("kwd");
			String oB = request.getParameter("orderBy");
			int count;
			
			int page = 1;
			if (pG != null && !pG.equals("")) {
				page = Integer.parseInt(pG);
			}

			String searchFrom = "";
			if (sF != null && !sF.equals("")) {
				searchFrom = sF;
			}

			String kwd = "";
			if (kwD != null && !kwD.equals("")) {
				kwd = kwD;
			}

			String orderBy = "";
			if (oB != null && !oB.equals("")) {
				orderBy = oB;
			}

			ProductDao dao = new ProductDaoImpl();
			ArrayList<ProductVo> list;
			if (kwd.equals("")){
				list = dao.getList();
				count = dao.getListCount();
				
			} else {
				list = dao.getList(page, searchFrom, kwd, orderBy);
				count = dao.getListCount(searchFrom, kwd);
			}
			
			System.out.println(list.toString());

			// 리스트 화면에 보내기
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			WebUtil.forward(request, response, "/WEB-INF/views/product/category.jsp");

		} else if ("insertform".equals(actionName)) {
			WebUtil.forward(request, response, "/WEB-INF/views/product/insertform.jsp");
		}
	}
		
}
