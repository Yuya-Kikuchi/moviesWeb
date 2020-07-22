package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.constants.MoviesConstants;
import com.example.demo.entity.User;
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
	 * <dl>直下のURL遷移時に行う処理。
	 * @param model モデル
	 * @return 遷移先URL(ログイン画面）
	 */
	@GetMapping
	public String index(Model model) {
		// c.画面に表示するデータを渡す
		model.addAttribute("sysName", MoviesConstants.sysName);
		return "login";
	}
	
	/**
	 * <dd>ログイン成功時の処理
	 * @param model モデル
	 * @return 画面遷移先
	 */
	@GetMapping("/success")
	public String success(Model model) {
		System.out.println("ログイン成功");
		return "menu";
	}

	/**
	 * <dd>新規ユーザー登録処理
	 * @param name ユーザー名
	 * @param email メールアドレス
	 * @param pass パスワード
	 * @return 遷移先
	 */
	@GetMapping("/add")
	public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email,
			@RequestParam String pass) {
		User usr = new User();
		usr.setName(name);
		usr.setEmail(email);
		usr.setPassWord(pass);
		userRepository.save(usr);
		return "saved";
	}

	@GetMapping("/all")
	public String getAllUsers(Model model) {

		List<User> users = (List<User>) userRepository.findAll();
		model.addAttribute("users", users);
		return "user";
	}
}
