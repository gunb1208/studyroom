package com.myproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myproject.domain.MemberDomain;

@Mapper
public interface MemberDao {
	
	public List<MemberDomain> selectAll();
	
}
