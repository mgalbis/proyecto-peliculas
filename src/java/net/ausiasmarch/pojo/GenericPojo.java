/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.pojo;

/**
 *
 * @author María Galbis
 */
public interface GenericPojo<Object> {
    public Integer getId();
    public void setId(Integer id);
    public String getComment(String column);
}
