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
public class SenseElem implements Serializable
{
    private boolean bPos;

    private String pos;
    private String meaning;

    private List<String> posList;
    private List<String> antonymList;
    private List<String> fieldList;
    private List<String> dialectList;
    private List<String> exampleList;
    private List<String> meaningList;
    
    public SenseElem()
    {
        bPos = false;
        pos = "";
        posList = null;
        antonymList = null;
        fieldList = null;
        dialectList = null;
        exampleList = null;
        meaningList = null;
    }

    public boolean isbPos()
    {
        return bPos;
    }

    public void setbPos(boolean bPos)
    {
        this.bPos = bPos;
    }

    public String getMeaning()
    {
        meaning = listToString(meaningList);
        return meaning;
    }

    public void setMeaning(String meaning)
    {
        this.meaning = meaning;
    }

    public String getPos()
    {
        return pos;
    }

    public void setPos(String pos)
    {
        this.pos = pos;
    }
    
    public List<String> getPosList()
    {
        return posList;
    }

    public void setPosList(List<String> posList)
    {
        this.posList = posList;
    }

    public List<String> getAntonymList()
    {
        return antonymList;
    }

    public void setAntonymList(List<String> antonymList)
    {
        this.antonymList = antonymList;
    }

    public List<String> getFieldList()
    {
        return fieldList;
    }

    public void setFieldList(List<String> fieldList)
    {
        this.fieldList = fieldList;
    }

    public List<String> getDialectList()
    {
        return dialectList;
    }

    public void setDialectList(List<String> dialectList)
    {
        this.dialectList = dialectList;
    }

    public List<String> getExampleList()
    {
        return exampleList;
    }

    public void setExampleList(List<String> exampleList)
    {
        this.exampleList = exampleList;
    }

    public List<String> getMeaningList()
    {
        return meaningList;
    }

    public void setMeaningList(List<String> meaningList)
    {
        this.meaningList = meaningList;
    }

    // Creates a CSV of a list of either the reading(s) or the meaning(s) of a Kanji Entry
    private String listToString(List<String> list)
    {
        // Using this aggregate stream as Iterator should only be used for removing
        String listCat = list.stream().collect(Collectors.joining("|"));
       
        return listCat;
    }
    
}
