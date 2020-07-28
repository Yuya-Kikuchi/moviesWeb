package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <dd>プロジェクト エントリ クラス
 * <dl>Springアプリケーション起動を行うクラス
 * @author yuya
 */
@SpringBootApplication
@ComponentScan("com.example.demo") //DI登録
@EntityScan("com.example.demo.entity") //BeanとしてDI登録
@EnableJpaRepositories("com.example.demo.repository") //JpaRepositoryをONにする奴。指定されたパッケージ内を検索し、@Repositoryを付けたクラスをBeanとして登録。
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
