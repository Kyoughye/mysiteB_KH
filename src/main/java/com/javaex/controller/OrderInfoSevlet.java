package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.output.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.OrderInfoDao;
import com.javaex.dao.OrderInfoDaoImpl;
import com.javaex.util.WebUtil;
import com.javaex.vo.*;

@WebServlet("/orderInfo")
public class OrderInfoSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final int LISTCOUNT = 5;
	private static final String ATTACHES_DIR = "D:/javaStudy/mysite/WebContent/images";
	private static final int LIMIT_SIZE_BYTES = 5 * 1024 * 1024;
	private static final String CHARSET = "utf-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");
		System.out.println("board:" + actionName);
		String pageNum = request.getParameter("pageNum");
		System.out.println("pageNum:" + pageNum);
		if ("list".equals(actionName)) {

			// 리스트 화면에 보내기
			requestOrderInfoList(request);

			WebUtil.forward(request, response, "/WEB-INF/views/member/orderlistform.jsp");
		} else if ("read".equals(actionName)) {
			// 게시물 가져오기

			int orderNo = Integer.parseInt(request.getParameter("orderNo"));
			OrderInfoDao dao = new OrderInfoDaoImpl();
			List<OrderInfoVo> detailList =  dao.getDetail(orderNo);

			// 게시물 화면에 보내기
			request.setAttribute("orderInfoVo", detailList);
			WebUtil.forward(request, response, "/WEB-INF/views/board/read.jsp");
		} else if ("delete".equals(actionName)) {
			// 게시물 가져오기
			int orderNo = Integer.parseInt(request.getParameter("orderNo"));
			OrderInfoDao dao = new OrderInfoDaoImpl();
			dao.delete(orderNo);

			
			WebUtil.forward(request, response, "/WEB-INF/views/board/modifyform.jsp");
		} else if ("insert".equals(actionName)) {
			// 게시물 가져오기
			int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
			int memNo = Integer.parseInt(request.getParameter("memNo"));

			OrderInfoVo orderInfoVo = new OrderInfoVo(totalPrice, memNo);
			OrderInfoDao dao = new OrderInfoDaoImpl();

			dao.insert(orderInfoVo);

			WebUtil.redirect(request, response, "/mysite/board?a=list");
		
		

		} else {
			requestOrderInfoList(request);
			WebUtil.redirect(request, response, "/WEB-INF/views/member/orderConfirmation.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// 로그인 되어 있는 정보를 가져온다.
	//protected UserVo getAuthUser(HttpServletRequest request) {
		//HttpSession session = request.getSession();
		//UserVo authUser = (UserVo) session.getAttribute("authUser");

		//return authUser;
	//}

	// 페이징 처리한 게시물들을 받아옴
	public void requestOrderInfoList(HttpServletRequest request) {
		OrderInfoDao dao = OrderInfoDaoImpl.getInstance();
		List<OrderInfoVo> boardlist = new ArrayList<OrderInfoVo>();

		int pageNum = 1;
		int limit = LISTCOUNT;
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));}

		int memNo = Integer.parseInt(request.getParameter("memNo"));
		String searchDate1 = request.getParameter("searchDate1");
		String searchDate2 = request.getParameter("searchDate2");
		
		int total_record = dao.getListCount(searchDate1 , searchDate2 , memNo);
		boardlist = dao.getList(pageNum, limit, memNo , searchDate1, searchDate2 );

		int total_page;

		if (total_record % limit == 0) {
			total_page = total_record / limit;
			Math.floor(total_page);
		} else {
			total_page = total_record / limit;
			Math.floor(total_page);
			total_page = total_page + 1;
		}

		request.setAttribute("list", boardlist);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("total_page", total_page);
		request.setAttribute("total_record", total_record);
		
		

	}

	

}
