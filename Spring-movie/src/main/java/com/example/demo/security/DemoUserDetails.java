package com.example.demo.security;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;

/**
 * <dd>Spring セキュリティー用のユーザー情報クラス
 * <dl>トランザクション処理、関連機能を部品としてまとめる。
 * @author yuya
 */
public class DemoUserDetails extends User {

	/***/
	private static final long serialVersionUID = 1L;

	private final com.example.demo.entity.User userInfo;

	public DemoUserDetails(com.example.demo.entity.User demoUserInfo, String password) {
		super(demoUserInfo.getId(), password, Collections.emptyList());
		this.userInfo = demoUserInfo;
	}

	public com.example.demo.entity.User getDemoUser() {
		return userInfo;
	}
}
