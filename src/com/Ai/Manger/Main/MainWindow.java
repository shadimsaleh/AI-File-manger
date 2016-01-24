/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Ai.Manger.Main;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rami
 */
public class MainWindow extends Application implements Initializable{
    @FXML TreeView<ClassTree> tree=new TreeView<>();
    @FXML ListView<ClassList> list=new ListView<>();
    @FXML TextField pathFile=new TextField();
    @FXML TextField Search=new TextField();
    @FXML Button ButtonPath=new Button();
    @FXML CheckBox Read=new CheckBox();
    @FXML CheckBox Write=new CheckBox();
    
    public static void main(String[] args){
        launch(args);
    }
    
    @FXML void selectAll(){
        for (int i=0;i<list.getItems().size();i++){
            list.getItems().get(i).isSelected.setSelected(true);
        }
    }
    @FXML void UnselectAll(){
        for (int i=0;i<list.getItems().size();i++){
            list.getItems().get(i).isSelected.setSelected(false);
        }
    }
    
    @FXML void setReadable(){
        for (int i=0;i<list.getItems().size();i++){
                File f=new File(pathFile.getText()+list.getItems().get(i).getPathFile());
                f.setReadable(Read.isSelected());
                f.setWritable(Write.isSelected());
        }
    }
    
    @FXML void delete(){
        for (int i=0;i<list.getItems().size();i++){
            if (list.getItems().get(i).isSelected.isSelected()==true){
                System.out.println(new File(pathFile.getText()+list.getItems().get(i).getPathFile()).delete());
            }
        }
        loadFile(pathFile.getText());
    }
    
    @FXML public void search(){
        System.out.println(Search.getText());
        File f=new File(pathFile.getText());
        FilenameFilter filter=new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().contains(Search.getText().toLowerCase());
            }
        };
        File[] files=f.listFiles(filter);
        list.getItems().clear();
        for (int i=0;i<files.length;i++){
                list.getItems().add(new ClassList(files[i].getName(), files[i].length()/1000000, files[i].lastModified()));
        }
    }
    
    void loadFile(String path){
        File f=new File(path);
        list.getItems().clear();
        for (File t:f.listFiles()){
            list.getItems().add(new ClassList(t.getName(), t.length()/1000000, t.lastModified()));
        }
        
    }
    
    
    
    @FXML void listClick() throws Exception{
        if (new File(pathFile.getText()+list.getSelectionModel().getSelectedItem().getPathFile()+File.separator).isDirectory()){
            pathFile.appendText(list.getSelectionModel().getSelectedItem().getPathFile()+File.separator);
            list.getItems().clear();
            loadFile(pathFile.getText());
        }else{
            Runtime.getRuntime().exec("cmd /c "+pathFile.getText()+list.getSelectionModel().getSelectedItem().getPathFile());
        }
    }
    
    @FXML void back(){
        String backPath=new File(pathFile.getText()).getParent();
        if (backPath!=null){
            pathFile.setText(backPath+File.separator);
            loadFile(backPath);
            pathFile.setText(pathFile.getText().replace(File.separator+File.separator, File.separator));
        }
    }
    
    TreeItem<ClassTree> MakeTree(TreeItem<ClassTree> root,String text,Image image){
        TreeItem<ClassTree>tr=new TreeItem<ClassTree>(new ClassTree(text, image));
        root.getChildren().add(tr);
        return tr;
    }

    @FXML public void TreeClick(){
        if (tree.getSelectionModel().getSelectedItem().getValue().getText()==System.getProperty("user.name")){
            loadFile(System.getProperty("user.home")+File.separator);
            pathFile.setText(System.getProperty("user.home")+File.separator);
        }else if (tree.getSelectionModel().getSelectedItem().getValue().getText()=="Program Files"){
            loadFile(System.getenv("ProgramFiles")+File.separator);
            pathFile.setText(System.getenv("ProgramFiles")+File.separator);
        }else if (tree.getSelectionModel().getSelectedItem().getValue().getText()=="About"){
            Alert ala=new Alert(Alert.AlertType.INFORMATION);
            ala.setTitle("About");
            ala.setHeaderText("About");
            ala.setContentText("Version:1.0\nAI File manger is free and open source program to explorer your program and open and make edit was maked by javafx.\nStart develop time:23/1/2016\nEmail:Rami_kw@hotmail.com");
            ala.setResizable(false);
            ala.show();
        }else{
            loadFile(tree.getSelectionModel().getSelectedItem().getValue().getText());
            pathFile.setText(tree.getSelectionModel().getSelectedItem().getValue().getText());
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TreeItem<ClassTree> rootItem=new TreeItem<>(new ClassTree("Computer", new Image(MainWindow.class.getResourceAsStream("Image/Computer alt 2.png"))));
        tree.setRoot(rootItem);
        rootItem.setExpanded(true);
        Image img=new Image(MainWindow.class.getResourceAsStream("Image/device-drive-icon.png"));
        //Root File
        File[] list=File.listRoots();
        String mainPath=list[0].toString();
        for (File f:list){
            TreeItem<ClassTree> tr=MakeTree(rootItem, f.toString(), img);
            String[] f3=f.list();
        }
        MakeTree(rootItem, System.getProperty("user.name"), new Image(MainWindow.class.getResourceAsStream("Image/user-id-icon.png")));
        MakeTree(rootItem, "Program Files", new Image(MainWindow.class.getResourceAsStream("Image/Application-icon.png")));
        loadFile(mainPath);
        pathFile.setText(mainPath);
        MakeTree(rootItem, "About", new Image(MainWindow.class.getResourceAsStream("Image/Help.png")));
    }    

    @Override
    public void start(Stage win) throws Exception {
        
        Parent root=FXMLLoader.load(MainWindow.class.getResource("MainWindow.fxml"));
        Scene sc=new Scene(root);
        
        win.setMinWidth(631);
        win.setMinHeight(400);
        win.getIcons().add(new Image(MainWindow.class.getResourceAsStream("Image/files.png")));
        win.setScene(sc);
        win.setTitle("AI File manger");
        win.show();
    }
    
}
