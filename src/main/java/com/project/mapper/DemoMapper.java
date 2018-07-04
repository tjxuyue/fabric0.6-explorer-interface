package com.project.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.project.entity.Demo;

@Mapper
public interface DemoMapper extends BaseMapper<Demo> {
	public List<Demo> demo();
}
