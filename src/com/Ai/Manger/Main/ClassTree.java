package com.Ai.Manger.Main;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ClassTree extends HBox{
    String text="";

    public String getText() {
        return text;
    }
    
    public ClassTree(String text,Image image){
        ImageView view=new ImageView(image);
        this.text=text;
        view.setFitWidth(16);
        view.setFitHeight(16);
        getChildren().addAll(new Label("  "),view,new Label("  "),new Label(text));
        
    }
}
