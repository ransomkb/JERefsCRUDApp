/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ransomkb
 */
@Entity
@Table(name = "JERefs")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "JERefs.findAll", query = "SELECT j FROM JERefs j"),
    @NamedQuery(name = "JERefs.findById", query = "SELECT j FROM JERefs j WHERE j.id = :id")
})
public class JERefs implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "kana")
    private byte[] kana;
    @Lob
    @Column(name = "kanji")
    private byte[] kanji;
    @Lob
    @Column(name = "senses")
    private byte[] senses;

    public JERefs()
    {
    }

    public JERefs(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public byte[] getKana()
    {
        return kana;
    }

    public void setKana(byte[] kana)
    {
        this.kana = kana;
    }

    public byte[] getKanji()
    {
        return kanji;
    }

    public void setKanji(byte[] kanji)
    {
        this.kanji = kanji;
    }

    public byte[] getSenses()
    {
        return senses;
    }

    public void setSenses(byte[] senses)
    {
        this.senses = senses;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JERefs))
        {
            return false;
        }
        JERefs other = (JERefs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jpa.entities.JERefs[ id=" + id + " ]";
    }
    
}
