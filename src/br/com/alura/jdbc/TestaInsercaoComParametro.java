package br.com.alura.jdbc;

import java.sql.*;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        try(Connection connection = connectionFactory.recuperarConexao()) {
            connection.setAutoCommit(false);

            try (PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (NOME, DESCRICAO) " +
                    "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {

                adicionarVariavel(stm, "SmartTV", "45 polegas");
                adicionarVariavel(stm, "Radio", "Radio de bateria");

                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ROLLBACK EXECUTADO");
                connection.rollback();
            }
        }
    }

    private static void adicionarVariavel(PreparedStatement stm, String nome, String descricao) throws SQLException {
        stm.setString(1, nome);
        stm.setString(2, descricao);

        stm.execute();

        try(ResultSet rst = stm.getGeneratedKeys()){
            while (rst.next()){
                Integer id = rst.getInt(1);
                System.out.println("O id criado foi: " + id);
            }
        }

    }
}
