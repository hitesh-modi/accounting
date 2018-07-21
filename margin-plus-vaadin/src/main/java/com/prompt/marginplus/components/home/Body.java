package com.prompt.marginplus.components.home;

import com.prompt.marginplus.Labels;
import com.prompt.marginplus.components.login.LoginModal;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;

@SpringComponent
public class Body extends Div{

    public Body() {

        VerticalLayout content = new VerticalLayout();
        HorizontalLayout buttonLayout = new HorizontalLayout();

        Button loginButton = new Button();
        loginButton.setText(Labels.LOGIN_BUTTON);
        loginButton.setIcon(VaadinIcon.SIGN_IN.create());

        LoginModal loginModal = new LoginModal();
        loginModal.setCloseOnEsc(true);
        loginModal.setCloseOnOutsideClick(true);
        loginButton.addClickListener(buttonClickEvent -> {
            loginModal.open();
        });


        Button registerButton = new Button();
        registerButton.setText(Labels.REGISTRATION_BUTTON);
        registerButton.setIcon(VaadinIcon.PLUS_CIRCLE.create());

        buttonLayout.add(loginButton);
        buttonLayout.add(registerButton);


        Paragraph paragraph = new Paragraph("Welcome to Margin Plus. \nAll GST Solution in one place");
        content.add(buttonLayout);
        content.add(paragraph);
        add(content);
    }
}
