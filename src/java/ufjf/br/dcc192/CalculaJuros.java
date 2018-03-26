/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.br.dcc192;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pedro
 */
@WebServlet(name = "CalculaJuros", urlPatterns = {"/juros.html"})
public class CalculaJuros extends HttpServlet {

    private double valorI = 1000.0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LocalDate current = LocalDate.now();
        DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("MMMM/YYYY");
        int qntdMeses;
        if (request.getParameter("meses") != null && !"".equals(request.getParameter("meses"))) {
            qntdMeses = Integer.parseInt(request.getParameter("meses"));
        } else {
            qntdMeses = 12;
        }
        double taxaJuros;
        if (request.getParameter("taxa") != null && !"".equals(request.getParameter("taxa"))) {
            taxaJuros = Double.parseDouble(request.getParameter("taxa")) / 100;
        } else {
            taxaJuros = 1.0 / 100.0;
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Juros Simples</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form>");
        out.println("<label> Meses: <input name='meses' /></label></br>");
        out.println("<label> Taxa de Juros: <input name='taxa' /></label></br>");
        out.println("<input type='submit' />");
        out.println("<input type='reset' />");
        out.println("</form></br>");
        out.println("<table border= '1'>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th> Mês </th> ");
        out.println("<th>" + ((taxaJuros * 100) - 0.5) + "% </th> ");
        out.println("<th> " + (taxaJuros * 100) + "% </th> ");
        out.println("<th> " + ((taxaJuros * 100) + 0.5) + "% </th> ");
        out.println("</thead>");
        
        for (int i = 1; i <= qntdMeses; i++) {
            LocalDate mes = current.plusMonths(i);
            double m1, m2, m3 = 0;
            m1 = valorI * (1 + (i * (taxaJuros * 0.5)));
            m2 = valorI * (1 + (i * taxaJuros));
            m3 = valorI * (1 + (i * (taxaJuros * 1.5)));

            out.println("<tr>");
            out.println("<td>" + mes.format(formatacao) + "</td>");
            out.println("<td>" + m1 + "</td>");
            out.println("<td>" + m2 + "</td>");
            out.println("<td>" + m3 + "</td>");
            out.println("</tr>");

        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
