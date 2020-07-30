package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginUser;

/**
 * <dd>Spring SequrityにあるUserサービスの実装クラス。
 * <dl>SQLを実行するDAOクラスを使用して色んな事を行う。
 * <dl>データソースの引数にすることでDB認証に使用できる。
 * @author yuya
 */
@Service
public class DemoUserDetailsService implements UserDetailsService {

    /** <dd>ユーザーテーブルアクセスクラス */
    @Autowired
    private DemoUserDetailsDao userDao;

    @Override
    public UserDetails loadUserByUsername(String strEmail) throws UsernameNotFoundException {

        LoginUser eLoginUser = userDao.findUser(strEmail);
        if (eLoginUser == null) {
            throw new UsernameNotFoundException("User" + strEmail + "was not found in the database");
        }
        //↓権限のリスト
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        //AdminやUserなどが存在するが、今回は利用しないのでUSERのみを仮で設定
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        //↓権限を利用する場合は、DB上で権限テーブル、ユーザ権限テーブルを作成し管理が必要
        grantList.add(authority);

        //rawDataのパスワードは渡すことができないので、暗号化
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("ユーザーは" + eLoginUser.getName() + "でパスワードは" + eLoginUser.getPassWord());
        //UserDetailsはインタフェースなのでUserクラスのコンストラクタで生成したユーザオブジェクトをキャスト
        UserDetails userDetails = (UserDetails) new User(eLoginUser.getName(), encoder.encode(eLoginUser.getPassWord()),
                grantList);
        //↑ユーザーの検索自体はメールアドレスで行うが、実際に認証情報に渡すのはユーザー名（画面表示させるため）
        return userDetails;
    }
}
