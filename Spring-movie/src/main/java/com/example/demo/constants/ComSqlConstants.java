package com.example.demo.constants;

/**
 * <dd>SQLのコンスタントクラス
 * <dl>使用しているSQLをここにまとめる。（MyBatisみたいになる。）
 * @author yuya
 */
public class ComSqlConstants {
    /** <DD>登録ユーザー検索SQL */
    public static final String C_SQL_LOGIN_FIND_USER = "SELECT * FROM user WHERE email = :val001";

    /** <dd>SQL置換パラメータ001 */
    public static final String C_PARAM_VAL001 = "val001";
}
