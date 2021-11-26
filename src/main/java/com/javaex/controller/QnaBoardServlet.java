package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.QnaBoardDao;
import com.javaex.dao.QnaBoardDaoImpl;
import com.javaex.util.WebUtil;
import com.javaex.vo.MemberVo;
import com.javaex.vo.QnaBoardVo;

@WebServlet("/qna")
public class QnaBoardServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");
		System.out.println("qna:" + actionName);
		
		if ("list".equals(actionName)) {
			// 리스트 가져오기
			
			String typ = request.getParameter("t");
			//String kwd = request.getParameter("kwd");
			String pag = request.getParameter("p");
			
			String type = "전체문의";
			if(typ != null && !typ.equals("")) {
				type = typ;
			}
		
			int page = 1;
			if(pag != null && !pag.equals("")) {
				page = Integer.parseInt(pag);
			}
			
			QnaBoardDao dao = new QnaBoardDaoImpl();
			List<QnaBoardVo> list = dao.getList(type, page);
			int count = dao.getBoardCount(type);
			System.out.println(count);
			System.out.println(list.toString());

			// 리스트 화면에 보내기
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			WebUtil.forward(request, response, "/WEB-INF/views/qnaboard/list.jsp");
		
		} else if ("read".equals(actionName)) {
			// 게시물 가져오기
			int no = Integer.parseInt(request.getParameter("qnaNo"));
			QnaBoardDao dao = new QnaBoardDaoImpl();
			//dao.updateHit(no);
			QnaBoardVo qnaboardVo = dao.getBoard(no);
			
			System.out.println(qnaboardVo.toString());

			// 게시물 화면에 보내기
			request.setAttribute("QnaboardVo", qnaboardVo);
			WebUtil.forward(request, response, "/WEB-INF/views/qnaboard/view.jsp");
			
		} else if ("modifyform".equals(actionName)) {
			// 게시물 가져오기
			int no = Integer.parseInt(request.getParameter("qnaNo"));
			QnaBoardDao dao = new QnaBoardDaoImpl();
			QnaBoardVo qnaboardVo = dao.getBoard(no);

			// 게시물 화면에 보내기
			request.setAttribute("QnaboardVo", qnaboardVo);
			WebUtil.forward(request, response, "/WEB-INF/views/qnaboard/modify.jsp");
		} else if ("modify".equals(actionName)) {
			// 게시물 가져오기
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int qnano = Integer.parseInt(request.getParameter("qnano"));
			
			QnaBoardVo vo = new QnaBoardVo(qnano, title, content);
			QnaBoardDao dao = new QnaBoardDaoImpl();
			
			dao.update(vo);
			
			WebUtil.redirect(request, response, "/mysiteB/qna?a=list");
		} else if ("writeform".equals(actionName)) {
			
			WebUtil.forward(request, response, "/WEB-INF/views/qnaboard/write.jsp");
		} else if ("memwrite".equals(actionName)) {
			
			MemberVo authUser = getAuthUser(request);
			int memNo = authUser.getMemNo();
//			int memNo;
//			if(authUser.getMemNo() != 0 ) {
//				memNo = authUser.getMemNo();
//			} else {
//				memNo =0;
//			}
			
//			if(authUser == null) {
//				memNo = 0;
//				String nickname = request.getParameter("nickname");
//				String password = request.getParameter("pass");
//				
//		
//			} else {
//				memNo = authUser.getMemNo();
//				String nickname= null;
//				String password = null;
//				
//				System.out.println("memNo : ["+memNo+"]");
//			}
//						
//			int memNo = Integer.parseInt(request.getParameter("memNo"));
			
			
			
			
			
			//String nickname = request.getParameter("nickname");
			//String password = request.getParameter("password");
			String type = request.getParameter("type");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			
			System.out.println("memNo : ["+memNo+"]");
			System.out.println("title : ["+title+"]");
			System.out.println("content : ["+content+"]");
			

			QnaBoardVo vo = new QnaBoardVo(memNo, title, type, content);
			QnaBoardDao dao = new QnaBoardDaoImpl();
			dao.insert(vo);
			System.out.println(vo.toString());
			
			
			
			
			WebUtil.redirect(request, response, "/mysiteB/qna?a=list");

			
			
		} else if ("delete".equals(actionName)) {
			int qnano = Integer.parseInt(request.getParameter("qnano"));

			QnaBoardDao dao = new QnaBoardDaoImpl();
			dao.delete(qnano);

			WebUtil.redirect(request, response, "/mysiteB/qna?a=list");
			
		} else {
			WebUtil.redirect(request, response, "/mysiteB/qna?a=list");
		}
			
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// 로그인 되어 있는 정보를 가져온다.
	protected MemberVo getAuthUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVo authUser = (MemberVo) session.getAttribute("authUser");

		return authUser;
	}
	
}
