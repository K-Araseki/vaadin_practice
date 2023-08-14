package com.example.application.views;

import com.example.application.views.list.ListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {
    // AppLyoutはナビバーとか使える？　例）addToNavbar()

    public MainLayout(){
        createHeader();
        createDrawer();
    }

    private void createHeader(){
        // HTMLのH1と同じ
        H1 logo = new H1("Vaadin CRM");
        // class名を定義してCSSファイルを充てるのではなく、
        // 代わりにLumoUtilityを用いることで事前に定義されたCSSを扱うことができる　※詳細は公式ドキュメント
        // 今回はフォントサイズとマージン（余白）を設定
        logo.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM);

        // ヘッダーとして、トグルとlogoを横方向に配置
        var header = new HorizontalLayout(new DrawerToggle(), logo );

        // ヘッダーを縦軸に沿って中央に配置（よくわからんから使うとき調べる）
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        // ヘッダーに余白を設定
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header);
    }

    private void createDrawer() {
        addToDrawer(new VerticalLayout(
                new RouterLink("List", ListView.class),
                new RouterLink("Dashboard", DashboardView.class)
        ));
    }

}
