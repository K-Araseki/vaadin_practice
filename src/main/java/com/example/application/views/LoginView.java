package com.example.application.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

// 画面全体をこのクラスで管理したいから、親のレイアウトは用いない（ヘッダーが余分）
@Route("login")
@PageTitle("Login | Vaadin CRM")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    // ユーザ名とパスワードからなるフォーム
    private final LoginForm login = new LoginForm();

    public LoginView(){
        addClassName("login-view");
        setSizeFull();
        // 縦方向の中央配置
        setAlignItems(Alignment.CENTER);
        // 横方向の中央配置
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");

        add(new H1("Vaadin CRM"), login);
    }

    // クエリ パラメーターを読み取り、ログイン試行が失敗した場合にエラーを表示(なんかよくわからん、多分本番は自作のクラス・メソッドになりそう)
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent){
        // inform the user about an authentication error
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }

}
