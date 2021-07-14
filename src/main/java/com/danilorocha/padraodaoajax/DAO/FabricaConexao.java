package com.danilorocha.padraodaoajax.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Danilo Rocha
 */
public class FabricaConexao {
    public Connection pegaConexao() throws ErroDaoException 
    {
        Connection con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/daoPessoa","root","");
        } catch (ClassNotFoundException |SQLException ex) {
            throw new ErroDaoException("Erro ao tentar se conectar",ex);
        }
        return con;
    }
}
