/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wgcot
 */
public class Relation <T, S> {
    private T entity1;
    private S entity2;
    private String description;

    public Relation(T entity1, S entity2, String description) {
        this.entity1 = entity1;
        this.entity2 = entity2;
        this.description = description;
    }

    public T getEntity1() {
        return entity1;
    }

    public void setEntity1(T entity1) {
        this.entity1 = entity1;
    }

    public S getEntity2() {
        return entity2;
    }

    public void setEntity2(S entity2) {
        this.entity2 = entity2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
