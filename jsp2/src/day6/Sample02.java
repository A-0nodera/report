package day6;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Sample02
 */
@WebServlet("/day6/Sample02")
public class Sample02 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sample02() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        out.println(output("Get"));

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        out.println(output("Post"));

    }
    public StringBuffer output(String type){
        StringBuffer sb = new StringBuffer();

        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Sample02</title>");
        sb.append("</head>");
        sb.append("<body>");

        sb.append("<p>呼び出し方法：" + type + "送信</p>");

        sb.append("<a href='Sample02'>リンク</a>");

        sb.append("<form action='Sample02' method='get'>");
        sb.append("<input type='submit' value='GET送信' />");
        sb.append("</form>");

        sb.append("<form action='Sample02' method='post'>");
        sb.append("<input type='submit' value='POST送信' />");
        sb.append("</form>");

        sb.append("</body>");
        sb.append("</html>");

        return (sb);
    }
}
