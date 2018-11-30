package com.example.demoM.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestMapper {
	String getNameById(@Param("id")int id);
}
