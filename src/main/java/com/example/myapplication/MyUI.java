package com.example.myapplication;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Audio;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        Label lHeadline = new Label("Carson \"The Interception\" Wentz");
        Button bInterwentzion = new Button("Interwentzion");
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource rCarsonWentz = new FileResource(new File(basepath + "/WEB-INF/images/CarsonWentz.jpg"));
        FileResource rCarsonWentzCrying = new FileResource(new File(basepath + "/WEB-INF/images/CarsonWentzCrying.jpg"));
        FileResource rIntercepted = new FileResource(new File(basepath + "/WEB-INF/sounds/intercepted.mp3"));
        Image iCarsonWentz = new Image("",rCarsonWentz);
        Image iCarsonWentzCrying = new Image("",rCarsonWentzCrying);
        iCarsonWentzCrying.setSizeFull();

        //Soundplayer
        Audio aInterception = new Audio(null);
        aInterception.setSource(rIntercepted);
        
        //Subwindow for CryingWentz
        Window subWindow = new Window("CryingCarsonWentz");
        VerticalLayout vlSub = new VerticalLayout();
        vlSub.addComponent(iCarsonWentzCrying);
        vlSub.addComponent(aInterception);
        subWindow.setContent(vlSub);
        
      
        
        bInterwentzion.addClickListener(e -> {
        	subWindow.center();
        	subWindow.setSizeFull();
        	addWindow(subWindow);
        	aInterception.play();
        });
        
        layout.addComponents(lHeadline, iCarsonWentz, bInterwentzion);
        layout.setComponentAlignment(lHeadline, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(iCarsonWentz, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(bInterwentzion, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
