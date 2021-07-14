package com.danilorocha.padraodaoajax.DAO;

import com.danilorocha.padraodaoajax.Models.Pessoa;
import java.util.List;

/**
 *
 * @author Danilo Rocha
 */
public interface PessoaDaoInterface {
    public void criaPessoa(Pessoa p) throws ErroDaoException;
    public List<Pessoa> pegaPessoas()throws ErroDaoException;
    public Pessoa pegaPessoa(int codigo) throws ErroDaoException;
    public void deletarPessoa(int codigo) throws ErroDaoException;
    public void deletarPessoa(Pessoa p) throws ErroDaoException;
    public void editarPessoa(Pessoa p) throws ErroDaoException;
    public void sair() throws ErroDaoException;
    
}
