package com.example.school.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.school.dto.School;

@Mapper
public interface SchoolDao {
	public int maxId() throws Exception;
	
	public void insertData(School school) throws Exception;
	
	public int getDataCount(String searchKey, String searchValue) throws Exception;
	
	public List<School> getLists(String searchKey, String searchValue, int start, int end) throws Exception;
	
	public School getReadData(int id) throws Exception;
	
	public void updateData(School school) throws Exception;
	
	public void deleteData(int id) throws Exception;
}
