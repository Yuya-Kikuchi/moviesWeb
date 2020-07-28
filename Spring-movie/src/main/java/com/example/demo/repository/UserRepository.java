package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.LoginUser;

/**
 * <dd>ユーザー情報のリポジトリクラス
 * <dl>SQLの実行とか行う
 * <dl>DIに登録しておくことでデータ検索が可能になる。引数には＜エンティティクラス,　IDタイプとなる＞
 * @author yuya
 */
public interface UserRepository extends JpaRepository<LoginUser, Integer> {

}
