package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <dd>プロジェクト エントリ クラス
 * <dl>Springアプリケーション起動を行うクラス
 * @author yuya
 */
@SpringBootApplication
public class SpringMovieApplication {

	/**
	 * <dd>動画投稿閲覧デモアプリケーション起動
	 * <dl>動画投稿閲覧デモアプリケーションを起動する。
	 * @param args 実行パラメータ
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringMovieApplication.class, args);
	}

}
