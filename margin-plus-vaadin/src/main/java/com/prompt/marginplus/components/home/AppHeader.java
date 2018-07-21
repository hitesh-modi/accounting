package com.prompt.marginplus.components.home;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.spring.annotation.SpringComponent;

@SpringComponent
public class AppHeader extends Div {
    public AppHeader() {
       setText("Margin Plus!");
    }
}
