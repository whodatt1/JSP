package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import action.Action;
import action.product.ProductInsertAction;
import action.product.ProductListAction;
import action.product.ProductModifyAction;
import action.product.ProductRemoveAction;
import action.product.ProudctDetailAction;

public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(ProductController.class);
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); // view에서 자바로 전송되는 데이터의 인코딩
		res.setCharacterEncoding("utf-8"); // 자바에서 view로 전송되는 데이터의 인코딩
		res.setContentType("text/html; charset=utf-8"); // 화면출력을 담당하는 브라우저에 전달하는 문자코드
		
		String uri = req.getRequestURI();
//		log.info(">>> uri > {}", uri);
		String context = req.getContextPath();
//		log.info(">>> context > {}", context);
		String path = uri.substring(context.length());
		log.info(">>> ProductController > path > {}", path);
		
		Action action = null;
		String destPage = null;
		
		if(path.equals("/product/register.pd")) {
			destPage = "/product/register.jsp";
			
		} else if(path.equals("/product/insert.pd")) {
			action = new ProductInsertAction();
			action.execute(req, res);
			destPage = "list.pd";
			
		} else if(path.equals("/product/list.pd")) {
			action = new ProductListAction();
			action.execute(req, res);
			destPage = "/product/list.jsp";
			
		} else if(path.equals("/product/detail.pd") || path.equals("/product/modify.pd")) {
			action = new ProudctDetailAction();
			action.execute(req, res);
			
			destPage = path.substring(path.lastIndexOf("/")+1,
					path.lastIndexOf("/")+2).equals("d") ? 	
					"/product/detail.jsp" : "/product/modify.jsp";
			
		} else if(path.equals("/product/update.pd")) {
			action = new ProductModifyAction();
			action.execute(req, res);
			destPage ="detail.pd?pno="+req.getParameter("pno");
			
		} else if(path.equals("/product/remove.pd")) {
			action = new ProductRemoveAction();
			action.execute(req, res);
			destPage = "list.pd";
		}
		RequestDispatcher rdp = req.getRequestDispatcher(destPage);
		rdp.forward(req, res);
	}
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		log.info(">>> doGet() > {}",req.getRequestURI());
//		service(req, resp);
//	}
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		log.info(">>> doPost() > {}",req.getRequestURI());
//		service(req, resp);
//	}
}
