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

    private double valorI = 1000.0;
    private List<String> meses = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Juros Simples</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form method='post'>");
        out.println("<label> Meses: <input name='meses' /></label></br>");
        out.println("<label> Taxa de Juros: <input name='taxa' /></label></br>");
        out.println("<input type='submit' />");
        out.println("<input type='reset' />");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        out.println("<form method='post'>");
        out.println("<table border='1'>");

        int qntdMeses;
        if (request.getParameter("meses") != null && !"".equals(request.getParameter("meses"))) {
            qntdMeses = Integer.parseInt(request.getParameter("meses"));
        } else {
            qntdMeses = 12;
        }
        double taxaJuros;
        if (request.getParameter("taxa") != null && !"".equals(request.getParameter("taxa"))) {
            taxaJuros = Double.parseDouble(request.getParameter("taxa"));
        } else {
            taxaJuros = 1 / 100;
        }

        if (qntdMeses == 12 && taxaJuros == 0) {
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th> Mês </th> ");
            out.println("<th> 0.5% </th> ");
            out.println("<th> 1% </th> ");
            out.println("<th> 1.5% </th> ");
            out.println("</thead>");
            int n = 1;
            for (int i = 0; i < meses.size(); i++) {
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
        } else if (qntdMeses == 12 && taxaJuros != 0) {
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th> Mês </th> ");
            out.println("<th>" + (taxaJuros - 0.5) + "%</th> ");
            out.println("<th>" + taxaJuros + "%</th> ");
            out.println("<th>" + (taxaJuros + 0.5) + "%</th> ");
            out.println("</thead>");
            int n = 1;
            for (int i = 0; i < meses.size(); i++) {
                double m1, m2, m3 = 0;
                m1 = valorI * (1 + (n * ((taxaJuros / 100) - (0.5 / 100))));
                m2 = valorI * (1 + (n * (taxaJuros / 100)));
                m3 = valorI * (1 + (n * (((taxaJuros / 100) + (0.5 / 100)))));

                out.println("<tr>");
                out.println("<td>" + meses.get(i) + "</td>");
                out.println("<td>" + m1 + "</td>");
                out.println("<td>" + m2 + "</td>");
                out.println("<td>" + m3 + "</td>");
                out.println("</tr>");

                n++;
                

            }
        } else if (qntdMeses != 12 && taxaJuros == 0) {
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th> Mês </th> ");
            out.println("<th> 0.5% </th> ");
            out.println("<th> 1% </th> ");
            out.println("<th> 1.5% </th> ");
            out.println("</thead>");
            int n = 1;
            int m = 0;
            for (int i = 0; i < qntdMeses; i++) {
                double m1, m2, m3 = 0;
                m1 = valorI * (1 + (n * 0.005));
                m2 = valorI * (1 + (n * 0.01));
                m3 = valorI * (1 + (n * 0.015));

                out.println("<tr>");
                out.println("<td>" + meses.get(m) + "</td>");
                out.println("<td>" + m1 + "</td>");
                out.println("<td>" + m2 + "</td>");
                out.println("<td>" + m3 + "</td>");
                out.println("</tr>");

                n++;
                m++;
                if(m==12)
                    m=0;     
            }
        } else if (qntdMeses != 12 && taxaJuros != 0) {
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th> Mês </th> ");
            out.println("<th>" + (taxaJuros - 0.5) + "%</th> ");
            out.println("<th>" + taxaJuros + "%</th> ");
            out.println("<th>" + (taxaJuros + 0.5) + "%</th> ");
            out.println("</thead>");
            int n = 1;
            int m = 0;
            for (int i = 0; i < qntdMeses; i++) {
                double m1, m2, m3 = 0;
                m1 = valorI * (1 + (n * ((taxaJuros / 100) - (0.5 / 100))));
                m2 = valorI * (1 + (n * (taxaJuros / 100)));
                m3 = valorI * (1 + (n * (((taxaJuros / 100) + (0.5 / 100)))));

                out.println("<tr>");
                out.println("<td>" + meses.get(m) + "</td>");
                out.println("<td>" + m1 + "</td>");
                out.println("<td>" + m2 + "</td>");
                out.println("<td>" + m3 + "</td>");
                out.println("</tr>");

                n++;
                m++;
                if(m==12)
                    m=0; 
            }
            out.println("</table>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
