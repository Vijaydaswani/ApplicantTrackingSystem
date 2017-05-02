/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CE;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "candidates_record", catalog = "cyberedge", schema = "")
@NamedQueries({
    @NamedQuery(name = "CandidatesRecord.findAll", query = "SELECT c FROM CandidatesRecord c")
    , @NamedQuery(name = "CandidatesRecord.findByCandidateId", query = "SELECT c FROM CandidatesRecord c WHERE c.candidateId = :candidateId")
    , @NamedQuery(name = "CandidatesRecord.findByName", query = "SELECT c FROM CandidatesRecord c WHERE c.name = :name")
    , @NamedQuery(name = "CandidatesRecord.findByPosition", query = "SELECT c FROM CandidatesRecord c WHERE c.position = :position")
    , @NamedQuery(name = "CandidatesRecord.findByClient", query = "SELECT c FROM CandidatesRecord c WHERE c.client = :client")
    , @NamedQuery(name = "CandidatesRecord.findByLocation", query = "SELECT c FROM CandidatesRecord c WHERE c.location = :location")
    , @NamedQuery(name = "CandidatesRecord.findByContact", query = "SELECT c FROM CandidatesRecord c WHERE c.contact = :contact")
    , @NamedQuery(name = "CandidatesRecord.findByEmail", query = "SELECT c FROM CandidatesRecord c WHERE c.email = :email")
    , @NamedQuery(name = "CandidatesRecord.findByExperience", query = "SELECT c FROM CandidatesRecord c WHERE c.experience = :experience")
    , @NamedQuery(name = "CandidatesRecord.findByRemark", query = "SELECT c FROM CandidatesRecord c WHERE c.remark = :remark")})
public class CandidatesRecord implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "candidate_id")
    private Integer candidateId;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Position")
    private String position;
    @Column(name = "Client")
    private String client;
    @Column(name = "Location")
    private String location;
    @Column(name = "Contact")
    private String contact;
    @Column(name = "Email")
    private String email;
    @Column(name = "Experience")
    private Integer experience;
    @Column(name = "Remark")
    private String remark;

    public CandidatesRecord() {
    }

    public CandidatesRecord(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public CandidatesRecord(Integer candidateId, String name, String position) {
        this.candidateId = candidateId;
        this.name = name;
        this.position = position;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        Integer oldCandidateId = this.candidateId;
        this.candidateId = candidateId;
        changeSupport.firePropertyChange("candidateId", oldCandidateId, candidateId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        String oldPosition = this.position;
        this.position = position;
        changeSupport.firePropertyChange("position", oldPosition, position);
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        String oldClient = this.client;
        this.client = client;
        changeSupport.firePropertyChange("client", oldClient, client);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        String oldLocation = this.location;
        this.location = location;
        changeSupport.firePropertyChange("location", oldLocation, location);
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        String oldContact = this.contact;
        this.contact = contact;
        changeSupport.firePropertyChange("contact", oldContact, contact);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        changeSupport.firePropertyChange("email", oldEmail, email);
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        Integer oldExperience = this.experience;
        this.experience = experience;
        changeSupport.firePropertyChange("experience", oldExperience, experience);
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        String oldRemark = this.remark;
        this.remark = remark;
        changeSupport.firePropertyChange("remark", oldRemark, remark);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (candidateId != null ? candidateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CandidatesRecord)) {
            return false;
        }
        CandidatesRecord other = (CandidatesRecord) object;
        if ((this.candidateId == null && other.candidateId != null) || (this.candidateId != null && !this.candidateId.equals(other.candidateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CE.CandidatesRecord[ candidateId=" + candidateId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
