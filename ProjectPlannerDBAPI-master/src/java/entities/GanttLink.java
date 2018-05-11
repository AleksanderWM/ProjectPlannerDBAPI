/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aleks
 */
@Entity
@Table(name = "ganttLink")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GanttLink.findAll", query = "SELECT g FROM GanttLink g")
    , @NamedQuery(name = "GanttLink.findById", query = "SELECT g FROM GanttLink g WHERE g.id = :id")
    , @NamedQuery(name = "GanttLink.findBySource", query = "SELECT g FROM GanttLink g WHERE g.source = :source")
    , @NamedQuery(name = "GanttLink.findByTarget", query = "SELECT g FROM GanttLink g WHERE g.target = :target")
    , @NamedQuery(name = "GanttLink.findByType", query = "SELECT g FROM GanttLink g WHERE g.type = :type")})
public class GanttLink implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "source")
    private Integer source;
    @Column(name = "target")
    private Integer target;
    @Size(max = 45)
    @Column(name = "type")
    private String type;

    public GanttLink() {
    }

    public GanttLink(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GanttLink)) {
            return false;
        }
        GanttLink other = (GanttLink) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.GanttLink[ id=" + id + " ]";
    }
    
}
