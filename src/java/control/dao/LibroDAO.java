/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control.dao;

import Conexion.Mysql_BD;
import java.util.List;
import modelo.dto.LibroDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LibroDAO implements Contrato<LibroDTO>{
    
    public static final String SQL_CREATE  = "insert into tb_libro(isbn,nombre,autor,editorial,anio) values(?,?,?,?,?)";
    public static final String SQL_DELETE = "delete from tb_libro where isbn=?";
    public static final String SQL_UPDATE = "update tb_libro  set nombre=?,autor=?,editorial=?,anio=? where isbn=?";
    public static final String SQL_READ = "select * from tb_libro where isbn=?";
    public static final String SQL_READ_ALL = "select * from tb_libro ";

    public LibroDAO() {
    }

    @Override
    public boolean create(LibroDTO nuevo) {
         
        PreparedStatement pst = null;
        Connection con = Mysql_BD.getInstance().cnn;
        
        try{
            pst = con.prepareStatement(SQL_CREATE);
            pst.setLong(1, nuevo.getIsbn());
            pst.setString(2, nuevo.getNombre());
            pst.setString(3, nuevo.getAutor());
            pst.setString(4, nuevo.getEditorial());
            pst.setInt(5, nuevo.getAnio());
            pst.executeUpdate();
            return true;
        }catch(SQLException ex){
            System.out.println("Error al insertar el libro "+ ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Object item) {
        
        PreparedStatement pst = null;
        Connection con = Mysql_BD.getInstance().cnn;
        
        try {
            pst=con.prepareStatement(SQL_DELETE);
            pst.setLong(1,((LibroDTO) item).getIsbn());
            pst.executeUpdate();            
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el libro "+ ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(LibroDTO filter) {
        
        PreparedStatement pst = null;
        Connection con = Mysql_BD.getInstance().cnn;
        
        try{
            pst = con.prepareStatement(SQL_UPDATE);
            pst.setString(1, filter.getNombre());
            pst.setString(2, filter.getAutor());
            pst.setString(3, filter.getEditorial());
            pst.setInt(4, filter.getAnio());
            pst.setLong(5, filter.getIsbn());
            pst.executeUpdate();
            return true;
        }catch(SQLException ex){
            System.out.println("Error al actualizar el libro "+ ex.getMessage());
            return false;
        }
    }

    @Override
    public LibroDTO read(LibroDTO filter) {
        LibroDTO libro = new LibroDTO();
        PreparedStatement pst = null;
        Connection con = Mysql_BD.getInstance().cnn;
        
        try {
            pst = con.prepareStatement(SQL_READ);
            pst.setLong(1, filter.getIsbn());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                libro.setIsbn(rs.getLong("isbn"));
                libro.setNombre(rs.getString("nombre"));
                libro.setAutor(rs.getString("autor"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setAnio(rs.getInt("anio"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar un libro "+ ex.getMessage());
        }
        return libro;
    }

    @Override
    public List<LibroDTO> readAll() {
        
        List<LibroDTO> lista =null;
        PreparedStatement psnt = null;
        Connection con = Mysql_BD.getInstance().cnn;

        try {   
            psnt = con.prepareStatement(SQL_READ_ALL);
            ResultSet rs = psnt.executeQuery();
            lista = new ArrayList<>();
            
            while(rs.next()){
                LibroDTO libro = new LibroDTO(
                rs.getLong("isbn"),
                    rs.getString("nombre"),
                    rs.getString("autor"),
                    rs.getString("editorial"),
                    rs.getInt("anio")
                );
                lista.add(libro);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar todos los libros "+ ex.getMessage());
        }    
        return lista;
    }
}
