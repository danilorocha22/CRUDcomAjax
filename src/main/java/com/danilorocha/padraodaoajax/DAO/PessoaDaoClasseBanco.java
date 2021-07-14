package com.danilorocha.padraodaoajax.DAO;

import com.danilorocha.padraodaoajax.Models.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo Rocha
 */
public class PessoaDaoClasseBanco implements PessoaDaoInterface{

    Connection con;
    public PessoaDaoClasseBanco() throws ErroDaoException {
        FabricaConexao fabrica=new FabricaConexao();
        con=fabrica.pegaConexao();
    }
    
    @Override
    public void criaPessoa(Pessoa p) throws ErroDaoException {
        try {
            PreparedStatement ps=con.prepareStatement("insert into Pessoa values(null,?,?)");
            ps.setString(1,p.getNome());
            ps.setInt(2, p.getIdade());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            throw new ErroDaoException(ex);
        }
    }

    @Override
    public List<Pessoa> pegaPessoas() throws ErroDaoException {
        try {
            ArrayList<Pessoa> grupo=new ArrayList<>();
            PreparedStatement ps=con.prepareStatement("select * from Pessoa");
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()) {
                Pessoa p=new Pessoa();
                p.setCodigo(rs.getInt("codigo"));
                p.setIdade(rs.getInt("idade"));
                p.setNome(rs.getString("nome"));
                grupo.add(p);
            }
            return grupo;
        } catch (SQLException ex) {
            throw new ErroDaoException(ex);
        }
    }
    
    @Override
    public void sair() throws ErroDaoException {
        try {
            con.close();
        } catch (SQLException ex) {
            throw new ErroDaoException("Erro ao sair",ex);
        }
    }

    @Override
    public Pessoa pegaPessoa(int codigo) throws ErroDaoException {
        try {
            PreparedStatement ps=con.prepareStatement("select * from Pessoa where codigo=?");
            ps.setInt(1, codigo);
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()) {
                Pessoa p=new Pessoa();
                p.setCodigo(rs.getInt("codigo"));
                p.setIdade(rs.getInt("idade"));
                p.setNome(rs.getString("nome"));
                return p;
            }
            return null;
        } catch (SQLException ex) {
            throw new ErroDaoException(ex);
        }
    }

    @Override
    public void deletarPessoa(int codigo) throws ErroDaoException {
        try {
            PreparedStatement ps=con.prepareStatement("delete from Pessoa where codigo=?");
            ps.setInt(1, codigo);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            throw new ErroDaoException(ex);
        }
    }

    @Override
    public void deletarPessoa(Pessoa p) throws ErroDaoException {
        deletarPessoa(p.getCodigo());
    }

    @Override
    public void editarPessoa(Pessoa p) throws ErroDaoException {
        try {
            PreparedStatement ps=con.prepareStatement("update Pessoa set nome=?, idade=? where Pessoa.codigo=?");
            ps.setString(1, p.getNome());
            ps.setInt(2, p.getIdade());
            ps.setInt(3, p.getCodigo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ErroDaoException(ex);
        }
    }    
}
