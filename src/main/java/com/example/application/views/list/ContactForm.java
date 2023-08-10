package com.example.application.views.list;


import com.example.application.data.entity.Company;
import com.example.application.data.entity.Status;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

// FormLayoutは画面幅に応じてフォームフィールドを1・2列に表示する（レスポンシブ）
public class ContactForm extends FormLayout {
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    EmailField email = new EmailField("Email");

    // プルダウン形式のコンポーネント
    ComboBox<Status> status = new ComboBox<>("Status");
    ComboBox<Company> company = new ComboBox<>("Company");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public ContactForm(List<Company> companies, List<Status> statuses) {
        // フォームコンポーネントの名前を設定
        addClassName("contact-form");

        // プルダウンの中身を設定する（Company型でもらって、中身を取り出す？仕様ぽい）
        company.setItems(companies);
        company.setItemLabelGenerator(Company::getName);
        // プルダウンの中身を設定する
        status.setItems(statuses);
        status.setItemLabelGenerator(Status::getName);

        add(firstName,
                lastName,
                email,
                company,
                status,
                createButtonsLayout());
    }

    // 横方向にボタンを配置するメソッド
    // ボタンの中身を設定
    private HorizontalLayout createButtonsLayout() {
        // ボタンのデザインを設定（ここはドキュメント参照、色とかかわる）
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        // 押した時の処理を設定
        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        // ３津のボタンを横方向に配置する
        return new HorizontalLayout(save, delete, close);
    }


}
