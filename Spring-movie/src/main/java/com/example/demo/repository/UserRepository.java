package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.User;

/**
 * <dd>リポジトリクラス
 * <dl>SQLの実行とか行う
 * @author yuya
 *
 */
public interface UserRepository extends CrudRepository<User, Integer> {

}
