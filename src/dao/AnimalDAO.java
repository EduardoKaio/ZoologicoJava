package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Animal;

public class AnimalDAO {

    private static ArrayList<Animal> lista = new ArrayList<Animal>();

    public ArrayList<Animal> buscarTodos() throws ClassNotFoundException {
        String sql = "SELECT * FROM Animal";

        // Responsável em guardar o resultado
        ResultSet resultado = null;

        ArrayList<Animal> lista = new ArrayList<Animal>();

        Connection conn = FabricaConexao.getConnection();

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            resultado = ps.executeQuery();

            while (resultado.next()) {
                //Antes a gente estava imprimindo.
                // Agora estamos guardando no arrayList
                Animal a = new Animal();
                a.setId_animal(resultado.getInt("id_animal"));
                a.setNome(resultado.getString("nome"));
                a.setClassificacao(resultado.getString("classificacao"));
                a.setCaracteristica(resultado.getString("caracteristica"));
                a.setLocalizacao(resultado.getString("localizacao"));
                a.setPeso(resultado.getString("peso"));
                lista.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FabricaConexao.fecharConexao(conn);

        return lista;
    }

    public Animal getById(Integer id) throws ClassNotFoundException {
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        String sql = "SELECT * FROM Animal WHERE id=?";
        ResultSet resultado = null;
        Animal animal = null;
        try (Connection conn = FabricaConexao.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            resultado = ps.executeQuery();
            resultado.next();
            Animal a = new Animal();
            a.setId_animal(resultado.getInt("id_animal"));
            a.setNome(resultado.getString("nome"));
            a.setClassificacao(resultado.getString("classificacao"));
            a.setCaracteristica(resultado.getString("caracteristica"));
            a.setLocalizacao(resultado.getString("localizacao"));
            a.setPeso(resultado.getString("peso"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaConexao.fecharConexao(resultado);
        }
        return animal;
    }

    public boolean create(Animal animal) throws ClassNotFoundException {

        try {
            String comando = "INSERT INTO Animal (nome, classificacao, caracteristica, localizacao, peso) VALUES"
                    + " (?, ?, ?, ?, ?)";
            
            Connection conn = FabricaConexao.getConnection();
            //revisor DE  SQL
            PreparedStatement ps = conn.prepareStatement(comando);
            // substituindo as ?
            ps.setString(1, animal.getNome());
            ps.setString(2, animal.getClassificacao());
            ps.setString(3, animal.getCaracteristica());
            ps.setString(4, animal.getLocalizacao());
            ps.setString(5, animal.getPeso());

            //inserindo no banco.
            int linhasAfetadas = ps.executeUpdate();
            FabricaConexao.fecharConexao(conn);

            if (linhasAfetadas > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Animal animal) throws ClassNotFoundException {

        String sql = "UPDATE Animal SET nome = ?, classificacao = ?, caracteristica = ?, localizacao = ?, peso = ?"
                + "WHERE animal.id_animal = ?";

        try {
            Connection conn = FabricaConexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, animal.getNome());
            ps.setString(2, animal.getClassificacao());
            ps.setString(3, animal.getCaracteristica());
            ps.setString(4, animal.getLocalizacao());
            ps.setString(5, animal.getPeso());
            ps.setInt(6, animal.getId_animal());

            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Integer id) throws ClassNotFoundException {
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        String sql = "DELETE FROM Animal WHERE animal.id_animal = ?";
        try {
            Connection conn = FabricaConexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

}
