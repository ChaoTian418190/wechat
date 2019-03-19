package com.wechat.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wechat.model.Account;

@Mapper
public interface AccountDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
    
    public Account selectByAccount(String account);
}