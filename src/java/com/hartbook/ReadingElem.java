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
public class ReadingElem implements Serializable
{
    private String kana;

    private List<String> rebs;
    private List<String> reNokanjis;
    private List<String> rePris;

    public ReadingElem()
    {
        rebs = null;
        reNokanjis = null;
        rePris = null;
    }

    public String getKana()
    {
        kana = listToString(rebs);
        return kana;
    }

    public void setKana(String kana)
    {
        this.kana = kana;
    }
    
    public List<String> getRebs()
    {
        return rebs;
    }

    public void setRebs(List<String> rebs)
    {
        this.rebs = rebs;
    }

    public List<String> getReNokanjis()
    {
        return reNokanjis;
    }

    public void setReNokanjis(List<String> reNokanjis)
    {
        this.reNokanjis = reNokanjis;
    }

    public List<String> getRePris()
    {
        return rePris;
    }

    public void setRePris(List<String> rePris)
    {
        this.rePris = rePris;
    }
    
    
    // Creates a CSV of a list of either the reading(s) or the meaning(s) of a Kanji Entry
    private String listToString(List<String> list)
    {
        // Using this aggregate stream as Iterator should only be used for removing
        String listCat = list.stream().collect(Collectors.joining("|"));
       
        return listCat;
    }
}
