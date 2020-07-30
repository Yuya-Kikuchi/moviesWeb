package com.example.demo.form;

import com.sun.istack.NotNull;

/**
 * @author yuya 
 *
 */
public class SignUpForm {
    /** <dd>エンティティ：名前 */
    @NotNull
    private String name;

    /** <dd>エンティティ：メールアドレス  */
    @NotNull
    private String email;

    /** <dd>エンティティ：パスワード */
    @NotNull
    private String passWord;

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
     * @return　メールアドレス
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
     * @return　パスワード
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * @param passWord ぱｳﾜｰﾄﾞ
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


}
