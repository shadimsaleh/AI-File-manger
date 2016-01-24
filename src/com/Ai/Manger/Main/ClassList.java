package com.Ai.Manger.Main;

import java.text.SimpleDateFormat;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ClassList extends Group{
    
    String pathFile="";

    public String getPathFile() {
        return pathFile;
    }
    
    public CheckBox isSelected=new CheckBox("");
    
    public ClassList(String name,long filesize,long lastModified){
        
        
        this.pathFile=name;
        String text=(name.length() >15) ? name.substring(0,15):name;
        Image img=new Image(MainWindow.class.getResourceAsStream("Image/files_1.png"));
        
        //Image cheack
        if (name.endsWith(".psd"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/file-photoshop-icon.png"));
        else if (name.endsWith(".css"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/css3.png"));
        else if (name.endsWith(".pdf"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/file-pdf-icon.png"));
        else if (name.endsWith(".txt"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/Text.png"));
        else if (name.endsWith(".html") || name.endsWith(".js") || name.endsWith(".java") || name.endsWith(".xml") || name.endsWith(".cpp")
                 || name.endsWith(".json") || name.endsWith(".py") || name.endsWith(".php") || name.endsWith(".php3") || name.endsWith(".php4")
                 || name.endsWith(".php5") || name.endsWith(".phps") || name.endsWith(".pyd") || name.endsWith(".phtml") || name.endsWith(".phpt"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/file-code.png"));
        else if (name.endsWith(".webm") || name.endsWith(".mkv") || name.endsWith(".flv") || name.endsWith(".vob") || name.endsWith(".ogv")
                 || name.endsWith(".ogg") || name.endsWith(".drc") || name.endsWith(".gifv") || name.endsWith(".mng")
                 || name.endsWith(".avi") || name.endsWith(".wmv") || name.endsWith(".rm") || name.endsWith(".rmvb") || name.endsWith(".mp4")
                 || name.endsWith(".mp4") || name.endsWith(".m4v") || name.endsWith(".asf") || name.endsWith(".mpg") || name.endsWith(".flv"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/file-video-icon.png"));
        else if (name.endsWith(".zip") || name.endsWith(".ios") || name.endsWith(".rar"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/file-zip-icon.png"));
        else if (name.endsWith(".png") || name.endsWith(".gif") || name.endsWith(".ico") || name.endsWith(".jpg") || name.endsWith(".tif") || name.endsWith(".bmb"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/file-picture.png"));
        else if (name.endsWith(".ai"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/file-illustrator-icon.png"));
        else if (name.endsWith(".wma") || name.endsWith(".aac") || name.endsWith(".mp3") || name.endsWith(".ogg") || name.endsWith(".au"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/file-sound.png"));
        else if (name.endsWith(".dll"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/dll-icon.png"));
        else if (name.endsWith(".jar"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/jar-icon.png"));
        else if (name.endsWith(".lib"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/library-icon.png"));
        else if (name.endsWith(".c"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/Letter-C-icon.png"));
        else if (name.endsWith(".cpp"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/Files-Cpp-icon.png"));
        else if (name.endsWith(".h"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/Letter-H-icon.png"));
        else if (name.endsWith(".exe"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/exe-icon.png"));
        else if (name.endsWith(".docx"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/wordpress.png"));
        else if (name.endsWith(".apk"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/android.png"));
        else if (name.endsWith(".bat"))
            img=new Image(MainWindow.class.getResourceAsStream("Image/Misc-file-exe-icon.png"));
        SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        ImageView view=new ImageView(img);
        view.setFitWidth(16);
        view.setFitHeight(16);
        Label tow=new Label("   "+filesize+"MB");
        tow.setLayoutX(180);
        tow.setLayoutY(2);
        view.setLayoutX(40);
        view.setLayoutY(2);
        Label filename=new Label("  "+text);
        filename.setLayoutX(60);
        filename.setLayoutY(2);
        Label dataformat=new Label(format.format(lastModified));
        dataformat.setLayoutX(280);
        dataformat.setLayoutY(2);
        isSelected.setLayoutX(10);
        isSelected.setLayoutY(2);
        getChildren().addAll(isSelected,view,filename,tow,dataformat);
    }
}