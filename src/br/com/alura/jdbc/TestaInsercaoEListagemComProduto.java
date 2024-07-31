package br.com.alura.jdbc;

import br.com.alura.jdbc.dao.ProdutoDAO;
import br.com.alura.jdbc.model.Produto;

import java.sql.*;
import java.util.List;

public class TestaInsercaoEListagemComProduto {
    public static void main(String[] args) throws SQLException {

        Produto comoda = new Produto("Comoda", "Comoda Vertical");


        try(Connection connection = new ConnectionFactory().recuperarConexao()){

            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            produtoDAO.salvar(comoda);
            List<Produto> listaDeProdutos =  produtoDAO.listar();
            listaDeProdutos.stream().forEach(lp -> System.out.println(lp));
        }
    }
}
