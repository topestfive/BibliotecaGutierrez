package control.dao;

import java.util.List;


public interface Contrato <CualquierCosa>{
    
    public boolean create(CualquierCosa nuevo);
    
    public boolean delete(Object item);
    
    public boolean update(CualquierCosa filter);
    
    public CualquierCosa read(CualquierCosa filter);
    
    public List<CualquierCosa> readAll();
    
}
