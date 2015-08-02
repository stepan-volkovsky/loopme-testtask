package com.loopme.testtask.dao;

import java.util.List;

import com.loopme.testtask.pojo.AdRequest;
import com.loopme.testtask.pojo.Creative;

public interface CreativesDao {

    public List<Creative> getCreatives(AdRequest adRequest);

}
