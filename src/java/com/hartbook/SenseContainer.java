/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hartbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Embeddable;

/**
 *
 * @author ransomkb
 */
@Embeddable
public class SenseContainer implements Serializable
{
    private String allMeans;
    private SenseElem sElem;
    
    private List<String> posList;
    private List<String> mList;
    private List<SenseElem> sContain;

    public SenseContainer()
    {
        allMeans = "";
        sElem = null;
        posList = new ArrayList<>();
        mList = new ArrayList<>();
        sContain = new ArrayList<>();
    }

    public String getAllMeans()
    {
        allMeans = listToString(getmList());
        return allMeans;
    }

    public void setAllMeans(String allMeans)
    {
        this.allMeans = allMeans;
    }

    public List<String> getmList()
    {
        sContain.stream().forEach((s) ->
        {
            mList.add(s.getMeaning());
        });
  
        return mList;
    }

    public void setmList(List<String> mList)
    {
        this.mList = mList;
    }

    public List<SenseElem> getsContain()
    {
        return sContain;
    }

    public void setsContain(List<SenseElem> sContain)
    {
        this.sContain = sContain;
    }

    public SenseElem getsElem()
    {
        return sElem;
    }

    public void setsElem(SenseElem sElem)
    {
        this.sElem = sElem;
    }

    public List<String> getPosList()
    {
        return posList;
    }

    public void setPosList(List<String> posList)
    {
        this.posList = posList;
    }
    
    // Creates a CSV of a list of either the reading(s) or the meaning(s) of a Kanji Entry
    private String listToString(List<String> list)
    {
        // Using this aggregate stream as Iterator should only be used for removing
        String listCat = list.stream().collect(Collectors.joining("|"));
       
        return listCat;
    }
    
}
