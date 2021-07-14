package com.danilorocha.padraodaoajax.Controller;

import com.danilorocha.padraodaoajax.DAO.ErroDaoException;
import com.danilorocha.padraodaoajax.DAO.PessoaDaoClasseBanco;
import com.danilorocha.padraodaoajax.DAO.PessoaDaoInterface;
import com.danilorocha.padraodaoajax.Models.Pessoa;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Danilo Rocha
 */
public class Inserir extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String nome = request.getParameter("nome");
            int idade = Integer.parseInt(request.getParameter("idade"));

            Pessoa p = new Pessoa();
            p.setIdade(idade);
            p.setNome(nome);

            PessoaDaoInterface dao = null;
            try {

                dao = new PessoaDaoClasseBanco();
                dao.criaPessoa(p);
                out.print("Cadastrado com sucesso!");

            } catch (ErroDaoException ex) {
                out.print("Erro ao tentar cadastrar");
            } finally {
                try {
                    dao.sair();
                } catch (ErroDaoException ex) {
                    out.print("<erro> Erro ao tentar sair </erro>");
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
