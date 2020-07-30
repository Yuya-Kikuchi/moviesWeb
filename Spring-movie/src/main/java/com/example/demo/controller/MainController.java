package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.constants.ComMsgConstants;
import com.example.demo.constants.MoviesConstants;
import com.example.demo.entity.LoginUser;
import com.example.demo.form.SignUpForm;
import com.example.demo.repository.UserRepository;

/**
 * <dd>メインコントローラー
 * <dl>「/movies」URLでの遷移や処理を管理するためのクラス
 * @author yuya
 *
 */
@Controller
@RequestMapping("/movies")
public class MainController {

	/** <dd>ユーザークラスのDBアクセスクラス */
	@Autowired
	private UserRepository userRepository;

    /**
     * <dd>起動処理
     * <dl>直下のURL遷移時に行う処理。Spring Sequrityの設定により、使用されない。
     * @param model モデル
     * @return 遷移先URL(ログイン画面）
     */
    @GetMapping
    public String index(Model model) {
        //↓画面に表示するデータを渡す
        model.addAttribute("sysName", MoviesConstants.C_STR_SYSNAME);
        return "login";
    }

    /**
     * <dd>ログインの認証処理
     * <dl>ログインページでの処理。
     * @param error リクエストパス：エラー
     * @param logout リクエストパス：ログアウト
     * @param model モデル
     * @param session セッション
     * @return 遷移先URL
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, Model model, HttpSession session) {
        //↓画面に表示するデータを渡す
        model.addAttribute("showErrorMsg", false);
        model.addAttribute("showLogoutedMsg", false);
        model.addAttribute("sysName", MoviesConstants.C_STR_SYSNAME);
        //↓リクエストパスが「エラー」の場合
        if (error != null) {
            System.out.println("ログイン出来ない");
            if (session != null) {
                model.addAttribute("showErrorMsg", true);
                model.addAttribute("errorMsg", ComMsgConstants.C_MSG_ERR_LOGIN);
            }
            /* ↓ログアウト処理 */
        } else if (logout != null) {
            model.addAttribute("showLogoutedMsg", true);
        }
        return "login";
    }

    /**
     * <dd>ログイン成功時の処理
     * @param model モデル
     * @param princial ログインユーザー情報
     * @return 画面遷移先
     */
    @GetMapping("/success")
    public String success(Model model, Principal princial) {
        System.out.println(princial.getName() + "はログイン成功した");
        init(model);
        //↓ログインユーザー名の取得
        model.addAttribute("userName", princial.getName());
        return "menu";
    }

    /**
     * @param model モデル
     * @return 画面遷移先
     */
    @GetMapping("/sign_up")
    public String sign_up(Model model) {
        init(model);
        model.addAttribute("signupForm", new SignUpForm());
        return "sign_up";
    }

    /**
     * <dd>新規ユーザー登録処理
     * @param model モデル
     * @param signupForm ユーザー登録フォーム
     * @param bindingResult けか
     * @param request リクエスト
     * @return 遷移先
     */
    @PostMapping("/sign_up")
    public String addNewUser(Model model, @Validated SignUpForm signupForm, BindingResult bindingResult,
            HttpServletRequest request) {
        init(model);
        System.out.println("登録処理を行う");
        if (bindingResult.hasErrors()) {
            return "sign_up";
        }
        try {
            //↓登録処理
            LoginUser loginUser = new LoginUser();
            loginUser.setName(signupForm.getName());
            loginUser.setEmail(signupForm.getEmail());
            loginUser.setPassWord(signupForm.getPassWord());
            userRepository.save(loginUser);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("signupError", true);
            return "sign_up";
        }
        try {
            request.login(signupForm.getEmail(), signupForm.getPassWord());
        } catch (ServletException e) {
            e.printStackTrace();
        }
        //↓登録完了画面
        return "redirect:/login";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {

        List<LoginUser> users = (List<LoginUser>) userRepository.findAll();
        model.addAttribute("users", users);
        return "user";
    }

    /**
     * <DD>画面共有初期処理
     * @param model モデル
     */
    private void init(Model model) {
        model.addAttribute("sysName", MoviesConstants.C_STR_SYSNAME);
    }
}
