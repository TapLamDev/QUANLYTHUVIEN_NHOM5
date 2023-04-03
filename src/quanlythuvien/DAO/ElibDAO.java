/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.DAO;

import java.util.List;



public abstract class ElibDAO<E,K>{
    public abstract void insert(E entity);
    public abstract void update(E entity);
    public abstract void delete(K key);
    public abstract List<E> selectAll();
    public abstract E selectById(K key);
    protected abstract List<E> selectBySQL(String sql, Object...args);
}
