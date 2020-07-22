package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * <dd>ユーザーのドメイン（Bean）クラス
 * <dl>ユーザーテーブルの各種情報を管理するための型
 * @author yuya
 */
@Entity
public class User {
	/**
	 * <dd>エンティティ：ユーザー
	 * <dl>@id→このフィールドをプライマリキーとする
	 * <dl>@GeneratedValue(strategy=GenerationType.AUTO)
	 * <dl>ユニークなキーを自動で生成
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	/** <dd>エンティティ：名前 */
	private String name;
	/** <dd>エンティティ：メールアドレス  */
	private String email;
	/** <dd>エンティティ：パスワード */
	private String passWord;
	/** <dd>エンティティ：管理者フラグ*/
	private Byte admin_flg;

	/**
	 * @return id ユーザーID
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id ユーザーID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return ユーザー名
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name ユーザー名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return メールアドレス
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email メールアドレス
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return パスワード
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * @param passWord パスワード
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * @return admin_flg 管理者フラグ
	 */
	public Byte getAdmin_flg() {
		return admin_flg;
	}

	/**
	 * @param admin_flg 管理者フラグ
	 */
	public void setAdmin_flg(Byte admin_flg) {
		this.admin_flg = admin_flg;
	}

}
