package com.example.demo.security;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.constants.ComSqlConstants;
import com.example.demo.entity.LoginUser;

/**
 * <dd>Spring セキュリティー用のユーザー情報クラス。
 * <dl>DBアクセスを行うクラス。
 * <dl>トランザクション処理、関連機能を部品としてまとめる。
 * @author yuya
 */
@Repository
public class DemoUserDetailsDao {

    /** <dd>エンティティを管理する奴。キャストして使う。 */
    @Autowired
    EntityManager em;

    /**
     * <DD>ユーザー情報検索処理
     * <DL>ユーザーテーブルから指定されたEmailを持つユーザーを検索する。
     * @param strEmail Eメールあどれす
     * @return ユーザー情報のエンティティ
     */
    public LoginUser findUser(String strEmail) {
        //↓使用するSQLの設定。 setParameterで引数の値を代入できるようにNamedParameterを利用
        String strQuery = ComSqlConstants.C_SQL_LOGIN_FIND_USER;
        //↓EntityManagerで取得された結果はオブジェクトとなるので、User型のエンティティへキャストが必要となる
        System.out.println(strQuery + "を実行する。");
        System.out.println(ComSqlConstants.C_PARAM_VAL001 + "→" + strEmail);
        return (LoginUser) em.createNativeQuery(strQuery, LoginUser.class)
                .setParameter(ComSqlConstants.C_PARAM_VAL001, strEmail).getSingleResult();
    }
}
