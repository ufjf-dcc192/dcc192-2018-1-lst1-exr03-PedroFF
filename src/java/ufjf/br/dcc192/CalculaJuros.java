/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.br.dcc192;

import java.io.IOException;
import java.io.PrintWriter;
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

    private double valorI = 1000;
    private double valorF = 1000 * (1 + (12 * 0.01));
    private List<String> meses = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        meses.add("Janeiro");
        meses.add("Fevereiro");
        meses.add("Março");
        meses.add("Abril");
        meses.add("Maio");
        meses.add("Junho");
        meses.add("Julho");
        meses.add("Agosto");
        meses.add("Setembro");
        meses.add("Outubro");
        meses.add("Novembro");
        meses.add("Dezembro");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Juros Simples</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<table border='1'>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th> Mês </th> ");
        out.println("<th> 0.5% </th> ");
        out.println("<th> 1% </th> ");
        out.println("<th> 1.5% </th> ");
        out.println("</thead>");

        int n = 1;
        for (int i = 0; i < 12; i++) {
            double m1, m2, m3 = 0;
            m1 = valorI * (1 + (n * 0.005));
            m2 = valorI * (1 + (n * 0.01));
            m3 = valorI * (1 + (n * 0.015));

            out.println("<tr>");
            out.println("<td>" + meses.get(i) + "</td>");
            out.println("<td>" + m1 + "</td>");
            out.println("<td>" + m2 + "</td>");
            out.println("<td>" + m3 + "</td>");
            out.println("</tr>");
            
            n++;
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

}
