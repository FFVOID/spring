package com.example.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.dao.SchoolDao;
import com.example.school.dto.School;

@Service
public class SchoolServiceImpl implements SchoolService{
	
	@Autowired
	private SchoolDao schoolMapper;
	@Override
	public int maxId() throws Exception {
		return schoolMapper.maxId();
	}

	@Override
	public void insertData(School school) throws Exception {
		schoolMapper.insertData(school);
	}

	@Override
	public int getDataCount(String searchKey, String searchValue) throws Exception {
		return schoolMapper.getDataCount(searchKey, searchValue);
	}

	@Override
	public List<School> getLists(String searchKey, String searchValue, int start, int end) throws Exception {
		return schoolMapper.getLists(searchKey, searchValue, start, end);
	}

	@Override
	public School getReadData(int id) throws Exception {
		return schoolMapper.getReadData(id);
	}

	@Override
	public void updateData(School school) throws Exception {
		schoolMapper.updateData(school);
	}

	@Override
	public void deleteData(int id) throws Exception {
		schoolMapper.deleteData(id);
	}

}
