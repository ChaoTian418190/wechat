package com.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wechat.dao.AccountDao;
import com.wechat.model.Account;
import com.wechat.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDao accountDao;
	
	public Account getAccountInfo() {
		return accountDao.selectByAccount("gh_617ae8fbf531");
	}

	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor=RuntimeException.class)
	public int updateAccountById(Account account) {
		return accountDao.updateByPrimaryKeySelective(account);
	}

}
