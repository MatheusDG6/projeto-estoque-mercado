/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mathe
 */

public class ProdutoDAO {
    public void cadastrarProduto(ProdutoBean produto) {
        
        // create - insert
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            
            stmt = conn.prepareStatement("INSERT INTO produtos (nome, preco, quantidade) VALUES (?, ?, ?)");
            stmt.setString(1, produto.getNome()); 
            stmt.setDouble(2, produto.getPreco());
            stmt.setDouble(3, produto.getQuantidade());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // read - select
    public List<ProdutoBean> ler () {
            List<ProdutoBean> produtos = new ArrayList();
            
            try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            stmt = conn.prepareStatement("SELECT * FROM produtos");
            
            rs = stmt.executeQuery();
            while(rs.next()) {
                ProdutoBean produto = new ProdutoBean();
                produto.setId(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                
                produtos.add(produto);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}