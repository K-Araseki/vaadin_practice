package com.example.application.views.list;

import com.example.application.data.entity.Contact;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import java.util.Collections;

@PageTitle("list")
@Route(value = "")
public class ListView extends VerticalLayout { //垂直方向のレイアウト

    // 表領域の準備
    Grid<Contact> grid = new Grid<>(Contact.class);
    // テキスト領域の準備
    TextField filterText = new TextField();
    // フォーム用クラスのインスタンス
    ContactForm form;

    public ListView(){
        // コンポーネント（1番大きい）の名前
        addClassName("list-view");
        // 画面サイズに対して最大化
        setSizeFull();
        // 表の内容の設定
        configureGrid();
        //
        configureForm();
        // コンポーネントの配置
        add(getToolbar(), getContent());
    }

    // 表コンポーネントの設定
    private void configureGrid(){
        // コンポーネントの名前
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        // カラムの設定
        grid.setColumns("firstName", "lastName", "email");
        // カラムの追加
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        // カラムの追加
        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        // カラム幅を自動設定
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void configureForm() {
        form = new ContactForm(Collections.emptyList(), Collections.emptyList());
        form.setWidth("25em");
    }



    // ツールバーの設定
    private HorizontalLayout getToolbar(){
        // テキスト領域の透かし文字
        filterText.setPlaceholder("Filter by name...");
        // なんかボタンの配置
        filterText.setClearButtonVisible(true);
        // ここわからん
        // Configure the search field to fire value-change events only when the user stops typing.
        // This way you avoid unnecessary database calls, but the listener is still fired without the user leaving the focus from the field.
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        // ボタンの配置
        Button addContactButton = new Button("Add contact");

        // 横方向に配置
        var toolbar = new HorizontalLayout(filterText, addContactButton);
        // コンポーネントの名前
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private Component getContent(){
        // 横並びに表とフォームを配置
        HorizontalLayout content = new HorizontalLayout(grid, form);
        // 横並びのコンポーネント幅の割合を指定
        // boot-strapのグリッドシステムみたいな感じ
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

//    public ListView() {
//        setSpacing(false);
//
//        Image img = new Image("images/empty-plant.png", "placeholder plant");
//        img.setWidth("200px");
//        add(img);
//
//        H2 header = new H2("This place intentionally left empty");
//        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
//        add(header);
//        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));
//
//        setSizeFull();
//        setJustifyContentMode(JustifyContentMode.CENTER);
//        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
//        getStyle().set("text-align", "center");
//    }

}
