package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

/**
 * <dd>動画情報のドメイン（Bean）クラス
 * <dl>Moviesテーブルの各種情報を管理するための型
 * @author yuya
 */
@Entity
public class Movies {

	@Id
	@NonNull
	private Integer mov_id;
	private Integer upd_use_id;
	private String mov_name;
	private String thimbnail_name;
	private String mov_title;
	private Byte show_flg;
	private Date upload_date;
	private Date change_date;

	/**
	 * @return 動画ID
	 */
	public Integer getMov_id() {
		return mov_id;
	}

	/**
	 * @param mov_id 動画ID
	 */
	public void setMov_id(Integer mov_id) {
		this.mov_id = mov_id;
	}

	/**
	 * @return 投稿ユーザーID
	 */
	public Integer getUpd_use_id() {
		return upd_use_id;
	}

	/**
	 * @param upd_use_id 投稿ユーザーID
	 */
	public void setUpd_use_id(Integer upd_use_id) {
		this.upd_use_id = upd_use_id;
	}

	/**
	 * @return 動画パス名
	 */
	public String getMov_name() {
		return mov_name;
	}

	/**
	 * @param mov_name 動画パス名
	 */
	public void setMov_name(String mov_name) {
		this.mov_name = mov_name;
	}

	/**
	 * @return　サムネイルパス
	 */
	public String getThimbnail_name() {
		return thimbnail_name;
	}

	/**
	 * @param thimbnail_name サムネイルパス
	 */
	public void setThimbnail_name(String thimbnail_name) {
		this.thimbnail_name = thimbnail_name;
	}

	/**
	 * @return 動画タイトル
	 */
	public String getMov_title() {
		return mov_title;
	}

	/**
	 * @param mov_title 動画タイトル
	 */
	public void setMov_title(String mov_title) {
		this.mov_title = mov_title;
	}

	/**
	 * @return 公開範囲
	 */
	public Byte getShow_flg() {
		return show_flg;
	}

	/**
	 * @param show_flg 公開範囲
	 */
	public void setShow_flg(Byte show_flg) {
		this.show_flg = show_flg;
	}

	/**
	 * @return 投稿日
	 */
	public Date getUpload_date() {
		return upload_date;
	}

	/**
	 * @param upload_date 投稿日
	 */
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}

	/**
	 * @return 更新日
	 */
	public Date getChange_date() {
		return change_date;
	}

	/**
	 * @param change_date 更新日
	 */
	public void setChange_date(Date change_date) {
		this.change_date = change_date;
	}

}
