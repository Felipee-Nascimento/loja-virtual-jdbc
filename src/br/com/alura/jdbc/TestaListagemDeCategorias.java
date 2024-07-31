package br.com.alura.jdbc;

import br.com.alura.jdbc.dao.CategoriaDAO;
import br.com.alura.jdbc.model.Categoria;
import br.com.alura.jdbc.model.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaListagemDeCategorias {

    public static void main(String[] args) throws SQLException {

        try(Connection connection = new ConnectionFactory().recuperarConexao()) {

            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
            List<Categoria> listaDeCategorias = categoriaDAO.listarComProdutos();
            listaDeCategorias.stream().forEach(ct -> {
                System.out.println(ct.getNome());
                for(Produto produto : ct.getProdutos()){
                    System.out.println(ct.getNome() + " - " + produto.getNome());
                }
            });
        }
    }
}
