/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "ganttTask")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GanttTask.findAll", query = "SELECT g FROM GanttTask g")
    , @NamedQuery(name = "GanttTask.findById", query = "SELECT g FROM GanttTask g WHERE g.id = :id")
    , @NamedQuery(name = "GanttTask.findByStartDate", query = "SELECT g FROM GanttTask g WHERE g.startDate = :startDate")
    , @NamedQuery(name = "GanttTask.findByText", query = "SELECT g FROM GanttTask g WHERE g.text = :text")
    , @NamedQuery(name = "GanttTask.findByProgress", query = "SELECT g FROM GanttTask g WHERE g.progress = :progress")
    , @NamedQuery(name = "GanttTask.findByDuration", query = "SELECT g FROM GanttTask g WHERE g.duration = :duration")
    , @NamedQuery(name = "GanttTask.findByParent", query = "SELECT g FROM GanttTask g WHERE g.parent = :parent")})
public class GanttTask implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 45)
    @Column(name = "start_date")
    private String startDate;
    @Size(max = 450)
    @Column(name = "text")
    private String text;
    @Column(name = "progress")
    private BigInteger progress;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "parent")
    private Integer parent;

    public GanttTask() {
    }

    public GanttTask(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigInteger getProgress() {
        return progress;
    }

    public void setProgress(BigInteger progress) {
        this.progress = progress;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
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
        if (!(object instanceof GanttTask)) {
            return false;
        }
        GanttTask other = (GanttTask) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.GanttTask[ id=" + id + " ]";
    }
    
}
