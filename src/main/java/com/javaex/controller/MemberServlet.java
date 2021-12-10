package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.MemberDao;
import com.javaex.dao.MemberDaoImpl;
import com.javaex.dao.QnaBoardDao;
import com.javaex.dao.QnaBoardDaoImpl;
import com.javaex.util.WebUtil;
import com.javaex.vo.MemberVo;
import com.javaex.vo.OrderInfoVo;
import com.javaex.vo.QnaAnswerVo;
import com.javaex.vo.QnaBoardVo;


@WebServlet("/user")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("UserServlet.doGet() 호출");
		request.setCharacterEncoding("utf-8");
		
		String actionName = request.getParameter("a");
		
		System.out.println("a->"+actionName);
		
		//회원가입폼
		if("joinform".equals(actionName)) {
			WebUtil.forward(request, response, "/WEB-INF/views/member/joinform.jsp");
			
		}else if ("join".equals(actionName)) {
			System.out.println("actionName => " + actionName);
			
			String memname = request.getParameter("memName");
			String memid = request.getParameter("memId");
			String mempass = request.getParameter("memPass");
			String memphone = request.getParameter("memPhone");
			String mempostc = request.getParameter("memPostc");
			String memdoro = request.getParameter("memDoro");
			String memjibun = request.getParameter("memJibun");
			String memadd = request.getParameter("memAdd");
			
			
//			System.out.println("memname->"+memname);
//			System.out.println("memid->"+memid);
//			System.out.println("mempass->"+mempass);
//			System.out.println("memphone->"+memphone);
//			System.out.println("memadd->"+memadd);
			
			
			MemberVo vo = new MemberVo(memname,memphone,mempostc,memdoro,memjibun,memadd,memid,mempass);
			//System.out.println("email->"+email);
//			System.out.println("memname->"+memname);
//			System.out.println("memid->"+memid);
//			System.out.println("mempass->"+mempass);
//			System.out.println("memphone->"+memphone);
//			System.out.println("memadd->"+memadd);
//			
			MemberDao dao = new MemberDaoImpl();
			dao.insert(vo);
			System.out.println("회원가입성공"+ vo);
			WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
		} else if ("loginform".equals(actionName)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/member/loginform.jsp");
			rd.forward(request, response);
			
		} else if ("login".equals(actionName)) {
			String memid = request.getParameter("memId");
			String mempass = request.getParameter("memPass");
			
			MemberDao dao = new MemberDaoImpl();
			MemberVo vo = dao.getMember(memid, mempass);
			
			System.out.println(vo);
			
			  if(vo==null ) {
	                System.out.println("실패");
	                response.sendRedirect("/mysiteB/user?a=loginform&result=fail");
	            } else if(vo.getMemCk().equals("0")) {
	                System.out.println("탈퇴한 회원");
	                response.sendRedirect("/mysiteB/user?a=loginform&result=out");
	            } else {
	                System.out.println("성공");
	                HttpSession session = request.getSession(true);
	                session.setAttribute("authUser", vo);
	                
	                response.sendRedirect("/mysiteB/main");
	                return;         
	            }
				
			
		
			
		} else if ("logout".equals(actionName)) {
			
			System.out.println("로그아웃");
			
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			
			WebUtil.redirect(request, response, "/mysiteB/main");
			
		
		} else if ("admin".equals(actionName)) {
			WebUtil.forward(request, response, "/WEB-INF/views/admin/adminMain.jsp");
			
			
		
		} else if ("mypage".equals(actionName)) {
			
			MemberVo authUser = getAuthUser(request);
			int no = authUser.getMemNo();

			MemberDao dao = new MemberDaoImpl();
			
			ArrayList<OrderInfoVo> list = dao.getList(no);

			System.out.println("no : " + no);

			// 리스트 화면에 보내기
			request.setAttribute("list", list);
			
			
			WebUtil.forward(request, response, "/WEB-INF/views/member/mypageform.jsp");
			
		} else if ("mypae".equals(actionName)) {
			
			MemberVo authUser = getAuthUser(request);
			int no = authUser.getMemNo();

			MemberDao dao = new MemberDaoImpl();
			ArrayList<OrderInfoVo> list = dao.getList(no);
			
			QnaBoardDao qdao = new QnaBoardDaoImpl();
			int count = qdao.getNoAnswerCount();
			System.out.println(count);
			
			ArrayList<QnaBoardVo> qblist = qdao.getNoAnswerL();
			
			request.setAttribute("qblist", qblist);
			request.setAttribute("list", list);
			request.setAttribute("noanswercount", count);
			
			
			
			WebUtil.forward(request, response, "/WEB-INF/views/manager/manage.jsp");	
			
			
		} else if ("orderlist".equals(actionName)) {
			
			MemberVo authUser = getAuthUser(request);
			int no = Integer.parseInt(request.getParameter("memNo"));
			MemberDao dao = new MemberDaoImpl();	
			ArrayList<OrderInfoVo> list = dao.getList(no);
			System.out.println("no : " + no);

			// 리스트 화면에 보내기
			request.setAttribute("list", list);	
			WebUtil.forward(request, response, "/WEB-INF/views/member/orderlistform.jsp");
			
		}else if ("modifyform".equals(actionName)) {
//			
//			HttpSession session = request.getSession();
//			MemberVo authUser = (MemberVo)session.getAttribute("authUser");
//			String no = authUser.getMemId();
//			
//			MemberDao dao = new MemberDaoImpl();
//			MemberVo vo = dao.getMember(no);
//			System.out.println(vo.toString());
//			
//			request.setAttribute("vo", vo);
			WebUtil.forward(request, response, "/WEB-INF/views/member/modifyform.jsp");
			
		}	 else if("modify".equals(actionName)) {
            
            System.out.println("actionName => " + actionName);
            
            String memName = request.getParameter("memName");
            String memPhone = request.getParameter("memPhone");
            
            String memPostc = request.getParameter("memPostc");
            String memDoro = request.getParameter("memDoro");
            String memJibun = request.getParameter("memJibun");
            
            
            String memAdd = request.getParameter("memAdd");
            String memPass = request.getParameter("memPass");
            
            MemberVo vo = new MemberVo();
            vo.setMemName(memName);
            vo.setMemPhone(memPhone);
            
            vo.setMemPostc(memPostc);
            vo.setMemDoro(memDoro);
            vo.setMemJibun(memJibun);
            
            
            vo.setMemAdd(memAdd);
            vo.setMemPass(memPass);
            
            
            HttpSession session = request.getSession();
            MemberVo authUser = (MemberVo)session.getAttribute("authUser");
            
            int no = authUser.getMemNo();
            vo.setMemNo(no);
            
            MemberDao dao = new MemberDaoImpl();
            dao.update(vo);
            
            authUser.setMemName(memName);
            
            WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp"); 
            
		}else if ("deleteform".equals(actionName)) {
    			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/member/deleteform.jsp");
    			rd.forward(request, response);	
		
			
		}else if ("delete".equals(actionName)) {
			String memid = request.getParameter("memId");
			String mempass = request.getParameter("memPass");
			
			MemberDao dao = new MemberDaoImpl();
			MemberVo vo = dao.getMember(memid, mempass);
			
			System.out.println(vo);
			
			  if(vo==null ) {
	                System.out.println("실패");
	                response.sendRedirect("/mysiteB/user?a=deleteform&result=fail");
	              } else {
	                System.out.println("탈퇴 성공");
	                HttpSession session = request.getSession(true);
	                
	                MemberVo authUser = (MemberVo)session.getAttribute("authUser");
	                
	                int no = authUser.getMemNo();
	                vo.setMemNo(no);
	                dao.delete(vo);
	                session.removeAttribute("authUser");
	    			session.invalidate();
    
	                response.sendRedirect("/mysiteB/main");
	                return;         
	            }
		
			
		}
		else if ("wishform".equals(actionName)) {
			WebUtil.forward(request, response, "/WEB-INF/views/member/wishform.jsp");
			
		}else if ("coupon".equals(actionName)) {
			WebUtil.forward(request, response, "/WEB-INF/views/member/coupon.jsp");
			
		}else if ("readmyq".equals(actionName)) {
			HttpSession session = request.getSession();
			MemberVo authUser = (MemberVo)session.getAttribute("authUser");
            int no = authUser.getMemNo();
            
            QnaBoardDao dao = new QnaBoardDaoImpl();
            //page를 받아오게(1대신에)
            ArrayList<QnaBoardVo> qlist = dao.getMyQList(no, 1);
			
            request.setAttribute("qlist", qlist);
			
			
			
			WebUtil.forward(request, response, "/WEB-INF/views/member/myqna.jsp");
			
		}else {
//		 System.out.println("actionName => " + actionName);
//         
//         String memName = request.getParameter("memName");
//         String memPhone = request.getParameter("memPhone");
//         String memAdd = request.getParameter("memAdd");
//         String memPass = request.getParameter("memPass");
//         
//         MemberVo vo = new MemberVo();
//         vo.setMemName(memName);
//         vo.setMemPhone(memPhone);
//         vo.setMemAdd(memAdd);
//         vo.setMemPass(memPass);
//         
//         
//         HttpSession session = request.getSession();
//         MemberVo authUser = (MemberVo)session.getAttribute("authUser");
//         
//         int no = authUser.getMemNo();
//         vo.setMemNo(no);
//         
//         MemberDao dao = new MemberDaoImpl();
//         dao.update(vo);
//         
//         authUser.setMemName(memName);
//         
         WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");   
			
		} 
			
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	// 로그인 되어 있는 정보를 가져온다
	protected MemberVo getAuthUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVo authUser = (MemberVo) session.getAttribute("authUser");

		return authUser;
	}
	

}
