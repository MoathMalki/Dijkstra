
package application;

//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.util.Duration;
//import javafx.scene.shape.Line;
//import javafx.scene.shape.Polygon;
//import javafx.animation.KeyFrame;
//import javafx.animation.PathTransition;
//import javafx.animation.Timeline;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Line;
//import javafx.scene.shape.Polygon;
//import javafx.scene.shape.MoveTo;
//import javafx.scene.shape.Path;
//import javafx.scene.shape.LineTo;
//import javafx.util.Duration;

public class Main extends Application {

    static ArrayList<Capital> Countrys;
    static Capital sourceCity = null;
    static Capital destinationCity = null;
    Pane root = new Pane();
    ComboBox<Label> source = new ComboBox<Label>();
    ComboBox<Label> Target = new ComboBox<Label>();
    static float mapHieght = 715;
    static float mapWidth = 1200;

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Stage primaryStage = new Stage();

        Scene scene = new Scene(root, 1580, 715);
        primaryStage.setTitle("Djkstra Project");
        root.setStyle("-fx-background-color: #000000;");
        initialize();
        Label names[] = new Label[Countrys.size()];
        Label s = new Label("Sorce:");
        s.setFont(new Font(30));
        s.setTextFill(Color.BEIGE);
        Label d = new Label("Target:");
        d.setFont(new Font(30));
        d.setTextFill(Color.BEIGE);
        source.setStyle("-fx-background-color: #90EE90;");
        Target.setStyle("-fx-background-color: #90EE90;");
        for (int i = 0, j = 0; i < names.length; i++, j++) {
            names[i] = new Label();
            names[i].setFont(new Font(20));
            names[i].setTextFill(Color.BLACK);
            names[i].setText(Countrys.get(i).name);
            source.getItems().add(names[i]);
            names[j] = new Label();
            names[j].setFont(new Font(20));
            names[j].setTextFill(Color.BLACK);
            names[j].setText(Countrys.get(j).name);
            Target.getItems().add(names[j]);
        }
        source.setTranslateX(1370);
        source.setTranslateY(50);
        source.setPrefSize(180, 50);
        Target.setTranslateX(1370);
        Target.setTranslateY(150);
        Target.setPrefSize(180, 50);
        s.setTranslateX(1200);
        s.setTranslateY(50);
        d.setTranslateX(1200);
        d.setTranslateY(150);

        source.setOnAction(e -> {
            sourceCity = Dijkstra.allNodes.get(source.getValue().getText());
            if (sourceCity != null) {
            	sourceCity.getTest().setStyle("-fx-background-color: #0000FF;\r\n" + "        -fx-background-radius:100;\r\n");
            	

            
            }
        });
        Target.setOnAction(i -> {
            destinationCity = Dijkstra.allNodes.get(Target.getValue().getText());
            if (destinationCity != null) {
                destinationCity.getTest().setStyle("-fx-background-color: #FF0000;\r\n" + "        -fx-background-radius:100;\r\n");
            }
        });

        Button run = new Button("Run");
        run.setFont(new Font(30));
        run.setTranslateX(1240);
        run.setTranslateY(220);
        run.setMinWidth(140);
        run.setMinHeight(70);
        run.setAlignment(Pos.CENTER);
        run.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, new CornerRadii(25), Insets.EMPTY)));

        TextArea path = new TextArea();
        path.setTranslateX(1280);
        path.setTranslateY(320);
        path.setMinSize(270, 220);
        path.setMaxSize(270, 220);
        path.setEditable(false);
        path.setStyle("-fx-background-color: #87CEEB;");

        Label p = new Label("Path:");
        p.setFont(new Font(30));
        p.setTranslateX(1210);
        p.setTranslateY(320);
        p.setTextFill(Color.BEIGE);

        TextField t1 = new TextField();
        t1.setTranslateX(1350);
        t1.setTranslateY(570);
        t1.setPrefSize(190, 50);
        t1.setEditable(false);
        t1.setFont(new Font(20));
        t1.setStyle("-fx-background-color: #90EE90;");

        Label t = new Label("Distance:");
        t.setFont(new Font(30));
        t.setTranslateX(1200);
        t.setTranslateY(570);
        t.setTextFill(Color.BEIGE);
   

        run.setOnAction(e -> {  
            int v = 0, w = 0;
            for (int i = 0; i < Countrys.size(); i++) {
                if (sourceCity.getFullName().equals(Countrys.get(i).getFullName()))
                    v = i;
                if (destinationCity.getFullName().equals(Countrys.get(i).getFullName()))
                    w = i;
            }
            if (sourceCity != null && destinationCity != null) {
                Dijkstra graph = new Dijkstra(Countrys, Countrys.get(v), Countrys.get(w));
                graph.generateDijkstra();
                drawPathOnMap(graph.pathTo(Countrys.get(w)));
                root.getChildren().add(group);
                path.setText(graph.getPathString());
                t1.setText(graph.distanceString + " KM");
            }
        });

        Button reset = new Button("Reset");
        reset.setPrefSize(140, 70);
        reset.setAlignment(Pos.CENTER);
        reset.setTranslateX(1420);
        reset.setTranslateY(220);
        reset.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, new CornerRadii(25), Insets.EMPTY)));
       reset.setFont(new Font(30));

        reset.setOnAction(action -> {
            sourceCity.getTest().setStyle("-fx-background-color: #000000;\r\n" + "        -fx-background-radius:100;\r\n");
            destinationCity.getTest().setStyle("-fx-background-color: #000000;\r\n" + "        -fx-background-radius:100;\r\n");
            sourceCity = new Capital();
            destinationCity = new Capital();
            group.getChildren().clear();
            root.getChildren().remove(group);
            source.setValue(new Label(""));
            Target.setValue(new Label(""));	
            path.setText(null);
            t1.setText(null);
        }); 

        Button exit = new Button("Exit");
        exit.setPrefSize(100, 50);
        exit.setAlignment(Pos.CENTER);
        exit.setTranslateX(1450); // Adjust the position as necessary
        exit.setTranslateY(650); // Adjust the position as necessary
        exit.setStyle("-fx-background-color: #FF0000;\r\n" + "        -fx-background-radius:10;\r\n");
        exit.setFont(new Font(20));

        exit.setOnAction(e -> {
            Platform.exit();
        });

        root.getChildren().addAll(source, Target, run, path, reset,t1, s, d, p, t, exit);
        primaryStage.setScene(scene);// set the scene
        primaryStage.show();
    }

    public void initialize() {
        Image image1 = new Image("C:\\\\Users\\\\97056\\\\Desktop\\\\WorkSpace\\\\Dj\\\\eeee.png"); 
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitHeight(mapHieght);
        imageView1.setFitWidth(mapWidth);
        imageView1.setVisible(true);
        root.getChildren().add(imageView1);
        for (int i = 0; i < Countrys.size(); i++) {

            Button b = new Button();
            Countrys.get(i).setTest(b);
            b.setUserData(Countrys.get(i));
            b.setTranslateX(getX(Countrys.get(i).x));// 600(600-40)
            b.setTranslateY(getY(Countrys.get(i).y));// 357.5(357.5+100)

            b.setMinWidth(10);
            b.setMinHeight(10);
            b.setMaxWidth(10);
            b.setMaxHeight(10);
            b.setStyle("-fx-background-color: #000000;\r\n" + "        -fx-background-radius:100;\r\n");
            b.setOnAction(event -> {
          	b.setStyle("-fx-background-color: #FF0000;\r\n" + "        -fx-background-radius:100;\r\n");
                if (sourceCity == null) {
                    sourceCity = (Capital) b.getUserData();
                    Label l = new Label();
                    l.setFont(new Font(20));
                    l.setTextFill(Color.BLACK);
                    l.setText(sourceCity.name);
                    source.setValue(l);
                } else if (destinationCity == null && sourceCity != null) {
                    destinationCity = (Capital) b.getUserData();
                    Label l = new Label();
                    l.setFont(new Font(20));
                    l.setTextFill(Color.BLACK);
                    l.setText(destinationCity.name);
                    Target.setValue(l);
                }
            });

            Label lb = new Label(Countrys.get(i).name);
            lb.setFont(new Font(10));
            lb.setTextFill(Color.BLACK);
            lb.setTranslateX(getX(Countrys.get(i).x));
            lb.setTranslateY(getY(Countrys.get(i).y) - 15);
            
            lb.setOnMouseEntered(event -> {
                lb.setFont(new Font(20));  
            });
            lb.setOnMouseExited(event -> {
                lb.setFont(new Font(10));  
            });

            root.getChildren().add(b);
            root.getChildren().add(lb);
        }

    }

    Group group = new Group();

    private void drawPathOnMap(Capital[] cities) {
       for (int i = 0; i < cities.length - 1; i++) {
            Line line = new Line(getX(cities[i].x) +5 , getY(cities[i].y )+5 ,
                    getX(cities[i + 1].x) +5 , getY(cities[i + 1].y) +5);
            line.setStroke(Color.BLACK);
            line.setStrokeWidth(2);
            group.getChildren().add(line);
        }
    }
 /*   private void drawPathOnMap(Capital[] cities) {
        for (int i = 0; i < cities.length - 1; i++) {
            double startX = getX(cities[i].x)+5;
            double startY = getY(cities[i].y)+5;
            double endX = getX(cities[i + 1].x)+5;
            double endY = getY(cities[i + 1].y)+5;
            
            Line line = new Line(startX , startY , endX  , endY );
            line.setStroke(Color.BLACK);
            line.setStrokeWidth(2);
            
            double angle = Math.atan2(endY - startY, endX - startX);
            
            double arrowLength = 10;
            
            double arrowX1 = endX - arrowLength * Math.cos(angle + Math.PI / 6); // 30 degrees
            double arrowY1 = endY - arrowLength * Math.sin(angle + Math.PI / 6);
            double arrowX2 = endX - arrowLength * Math.cos(angle - Math.PI / 6);
            double arrowY2 = endY - arrowLength * Math.sin(angle - Math.PI / 6);
            
            Line arrowLine1 = new Line(endX, endY, arrowX1, arrowY1);
            arrowLine1.setStroke(Color.BLACK);
            arrowLine1.setStrokeWidth(2);
            
            Line arrowLine2 = new Line(endX, endY, arrowX2, arrowY2);
            arrowLine2.setStroke(Color.BLACK);
            arrowLine2.setStrokeWidth(2);
            
            group.getChildren().addAll(line, arrowLine1, arrowLine2);
        }
    }*/

 
   
    
    
    private float getX(float xCountry) {
        float div = mapWidth / 1200;
        return (( 3.334f *xCountry) - 45) * div + mapWidth / 2;
    }

    private float getY(float yCountry) {
        float div = mapHieght / 715;
        return ((-3.97222f * yCountry) - 22.5f) * div + mapHieght / 2;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Countrys = Dijkstra.readFile();

        launch(args);
    }	
}

//#0000FF
//#FFFF00
//#FF0000

// 25.0330
//121.5654
//Taipei 