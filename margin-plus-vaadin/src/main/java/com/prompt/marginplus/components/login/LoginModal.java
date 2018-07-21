package com.prompt.marginplus.components.login;

import com.prompt.marginplus.Labels;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;

import javax.annotation.PostConstruct;

@SpringComponent
public class LoginModal extends Dialog{

    @PostConstruct
    public void init() {

        FormLayout formLayout = new FormLayout();
        TextField username = new TextField(Labels.USERNAME);
        username.setRequired(true);
        username.setAutofocus(true);

        PasswordField passwordField = new PasswordField(Labels.PASSWORD);
        passwordField.setRequired(true);

        Button loginButton = new Button(Labels.LOGIN);
        loginButton.getElement().getThemeList().add("primary");

        formLayout.add(username);
        formLayout.add(passwordField);
        formLayout.add(loginButton);

        this.add(formLayout);
    }
}
