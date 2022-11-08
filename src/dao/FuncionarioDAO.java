package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Funcionario;

public class FuncionarioDAO {

    private static ArrayList<Funcionario> lista = new ArrayList<Funcionario>();

    public ArrayList<Funcionario> buscarTodos() throws ClassNotFoundException {
        String sql = "SELECT * FROM funcionario";

        // Responsável em guardar o resultado
        ResultSet resultado = null;

        ArrayList<Funcionario> lista = new ArrayList<Funcionario>();

        Connection conn = FabricaConexao.getConnection();

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            resultado = ps.executeQuery();

            while (resultado.next()) {
                //Antes a gente estava imprimindo.
                // Agora estamos guardando no arrayList
                Funcionario a = new Funcionario();
                a.setId_funcionario(resultado.getInt("id_funcionario"));
                a.setNome(resultado.getString("nome"));
                a.setRg(resultado.getString("rg"));
                a.setCpf(resultado.getString("cpf"));
                a.setTelefone(resultado.getString("telefone"));
                a.setEndereco(resultado.getString("endereco"));
                a.setData_nascimento(resultado.getString("data_nascimento"));
                a.setEmail(resultado.getString("email"));
                lista.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FabricaConexao.fecharConexao(conn);

        return lista;
    }

    public Funcionario getById(Integer id) throws ClassNotFoundException {
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        
        String sql = "SELECT * FROM funcionario WHERE id=?";
        ResultSet resultado = null;
        Funcionario funcionario = null;
        try (Connection conn = FabricaConexao.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            resultado = ps.executeQuery();
            resultado.next();
            
            Funcionario a = new Funcionario();
            a.setId_funcionario(resultado.getInt("id_funcionario"));
            a.setNome(resultado.getString("nome"));
            a.setRg(resultado.getString("rg"));
            a.setCpf(resultado.getString("cpf"));
            a.setTelefone(resultado.getString("telefone"));
            a.setEndereco(resultado.getString("endereco"));
            a.setData_nascimento(resultado.getString("data_nascimento"));
            a.setEmail(resultado.getString("email"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaConexao.fecharConexao(resultado);
        }
        return funcionario;
    }
    
    public boolean create(Funcionario funcionario) throws ClassNotFoundException {

        try {
            String comando = "INSERT INTO funcionario (nome, rg, cpf, telefone, endereco, data_nascimento, email) VALUES"
                    + " (?, ?, ?, ?, ?, ?, ?)";

            Connection conn = FabricaConexao.getConnection();
            //revisor DE  SQL
            PreparedStatement ps = conn.prepareStatement(comando);
            // substituindo as ?
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getRg());
            ps.setString(3, funcionario.getCpf());
            ps.setString(4, funcionario.getTelefone());
            ps.setString(5, funcionario.getEndereco());
            ps.setString(6, funcionario.getData_nascimento());
            ps.setString(7, funcionario.getEmail());

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
    
    public boolean update(Funcionario funcionario) throws ClassNotFoundException {

        String sql = "UPDATE funcionario SET nome = ?, rg = ?, cpf = ?, telefone = ?, endereco = ?, data_nascimento = ?, email = ?"
                + "WHERE funcionario.id_funcionario = ?";

        try {
            Connection conn = FabricaConexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getRg());
            ps.setString(3, funcionario.getCpf());
            ps.setString(4, funcionario.getTelefone());
            ps.setString(5, funcionario.getEndereco());
            ps.setString(6, funcionario.getData_nascimento());
            ps.setString(7, funcionario.getEmail());
            ps.setInt(8, funcionario.getId_funcionario());

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
        String sql = "DELETE FROM funcionario WHERE funcionario.id_funcionario = ?";
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
