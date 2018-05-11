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
@Table(name = "projects")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projects.findAll", query = "SELECT p FROM Projects p")
    , @NamedQuery(name = "Projects.findByProjectID", query = "SELECT p FROM Projects p WHERE p.projectID = :projectID")
    , @NamedQuery(name = "Projects.findByProjectName", query = "SELECT p FROM Projects p WHERE p.projectName = :projectName")
    , @NamedQuery(name = "Projects.findByProjectDescription", query = "SELECT p FROM Projects p WHERE p.projectDescription = :projectDescription")
    , @NamedQuery(name = "Projects.findByTotalEstimatedHours", query = "SELECT p FROM Projects p WHERE p.totalEstimatedHours = :totalEstimatedHours")
    , @NamedQuery(name = "Projects.findByTotalActualHours", query = "SELECT p FROM Projects p WHERE p.totalActualHours = :totalActualHours")
    , @NamedQuery(name = "Projects.findByProjectStartDate", query = "SELECT p FROM Projects p WHERE p.projectStartDate = :projectStartDate")
    , @NamedQuery(name = "Projects.findByProjectEndDate", query = "SELECT p FROM Projects p WHERE p.projectEndDate = :projectEndDate")})
public class Projects implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProjectID")
    private Integer projectID;
    @Size(max = 45)
    @Column(name = "projectName")
    private String projectName;
    @Size(max = 400)
    @Column(name = "projectDescription")
    private String projectDescription;
    @Column(name = "TotalEstimatedHours")
    private Integer totalEstimatedHours;
    @Column(name = "TotalActualHours")
    private Integer totalActualHours;
    @Size(max = 45)
    @Column(name = "projectStartDate")
    private String projectStartDate;
    @Size(max = 45)
    @Column(name = "projectEndDate")
    private String projectEndDate;

    public Projects() {
    }

    public Projects(Integer projectID) {
        this.projectID = projectID;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Integer getTotalEstimatedHours() {
        return totalEstimatedHours;
    }

    public void setTotalEstimatedHours(Integer totalEstimatedHours) {
        this.totalEstimatedHours = totalEstimatedHours;
    }

    public Integer getTotalActualHours() {
        return totalActualHours;
    }

    public void setTotalActualHours(Integer totalActualHours) {
        this.totalActualHours = totalActualHours;
    }

    public String getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public String getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(String projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectID != null ? projectID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projects)) {
            return false;
        }
        Projects other = (Projects) object;
        if ((this.projectID == null && other.projectID != null) || (this.projectID != null && !this.projectID.equals(other.projectID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Projects[ projectID=" + projectID + " ]";
    }
    
}
