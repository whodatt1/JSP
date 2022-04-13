package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.ProductVO;
import service.ProductService;
import service.ProductServiceImple;

@WebServlet("/prodCtrl/*")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	private final ProductService psv;
	private RequestDispatcher rdp;
	private int isUp;

	public ProductController() {
		psv = new ProductServiceImple();		
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		
		switch (path) {
		case "register":				
			rdp = req.getRequestDispatcher("/product/register.jsp");
			rdp.forward(req, res);
			break;
		case "insert":
			isUp = psv.register(new ProductVO(req.getParameter("pname"),
					Integer.parseInt(req.getParameter("price")), 
					req.getParameter("madeBy"),
					req.getParameter("writer"),
					req.getParameter("category"),
					req.getParameter("description"),
					null));
			log.info(">>> REGISTER > {}", isUp > 0 ? "Success" : "Fail");
			
			rdp = req.getRequestDispatcher("/prodCtrl/list");
			rdp.forward(req, res);
			break;
		case "list":
			req.setAttribute("list", psv.getList());
			
			rdp = req.getRequestDispatcher("/product/list.jsp");
			rdp.forward(req, res);
			break;
		case "detail":
			req.setAttribute("pvo", psv.getDetail(Long.parseLong(req.getParameter("pno"))));
			
			rdp = req.getRequestDispatcher("/product/detail.jsp");
			rdp.forward(req, res);
			break;
		case "modify":
	
			req.setAttribute("pvo", psv.getDetail(Long.parseLong(req.getParameter("pno"))));
			
			rdp = req.getRequestDispatcher("/product/modify.jsp");
			rdp.forward(req, res);
			break;
		case "update":
			isUp = psv.modify(new ProductVO(Long.parseLong(req.getParameter("pno")),
					req.getParameter("pname"), 
					Integer.parseInt(req.getParameter("price")),
					req.getParameter("madeBy"),
					req.getParameter("category"),
					req.getParameter("description"),
					null));
			log.info(">>> MODIFY > {}", (isUp > 0 ? "Success" : "Fail"));
			rdp = req.getRequestDispatcher("/prodCtrl/detail?pno="+ req.getParameter("pno"));
			rdp.forward(req, res);
			break;
		case "remove":
			isUp = psv.remove(Long.parseLong(req.getParameter("pno")));
			log.info(">>> REMOVE > {}", isUp > 0 ? "Success" : "Fail");
			rdp = req.getRequestDispatcher("/prodCtrl/list");
			rdp.forward(req, res);
			break;
			
		default:
			break;
		}
	}
	
	

}
