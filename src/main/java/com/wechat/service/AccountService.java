package com.wechat.service;

import com.wechat.model.Account;

public interface AccountService {
	
	public Account getAccountInfo();
	
	public int updateAccountById(Account account);
}
