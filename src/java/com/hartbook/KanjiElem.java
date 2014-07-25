/*
 * Not using this
 */

package com.hartbook;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Embeddable;

/**
 *
 * @author ransomkb
 */
@Embeddable
public class KanjiElem implements Serializable
{
    private String kanji;
    
    private List<String> kebs;
    private List<String> keInfs;
    private List<String> kePris;

    public KanjiElem()
    {
        kebs = null;
        keInfs = null;
        kePris = null;
    }

    public String getKanji()
    {
        kanji = listToString(kebs);
        return kanji;
    }

    public void setKanji(String kanji)
    {
        this.kanji = kanji;
    }

    public List<String> getKebs()
    {
        return kebs;
    }

    public void setKebs(List<String> kebs)
    {
        this.kebs = kebs;
    }

    public List<String> getKeInfs()
    {
        return keInfs;
    }

    public void setKeInfs(List<String> keInfs)
    {
        this.keInfs = keInfs;
    }

    public List<String> getKePris()
    {
        return kePris;
    }

    public void setKePris(List<String> kePris)
    {
        this.kePris = kePris;
    }
    
    // Creates a CSV of a list of either the reading(s) or the meaning(s) of a Kanji Entry
    private String listToString(List<String> list)
    {
        // Using this aggregate stream as Iterator should only be used for removing
        String listCat = list.stream().collect(Collectors.joining("|"));
       
        return listCat;
    }
    
}
