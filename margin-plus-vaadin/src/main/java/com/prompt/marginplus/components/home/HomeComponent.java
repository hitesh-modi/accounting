package com.prompt.marginplus.components.home;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Route("")
@Theme(Lumo.class)
@SpringComponent
public class HomeComponent extends Div{

    @Resource(name="appHeader")
    private AppHeader appHeader;

    @Resource(name="body")
    private Body body;

    @PostConstruct
    public void init() {
        add(appHeader);
        add(body);
    }

}
