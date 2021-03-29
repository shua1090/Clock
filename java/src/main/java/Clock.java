import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/** 
 * mvn clean compile exec:java
*/

import java.util.Date;

class Position {
    public int x, y;
    public Position(int x, int y) {
        this.x = x; this.y = y;
    }
}

class currentTime extends Date{
    private static final long serialVersionUID = 1L;

    currentTime(){
        super(System.currentTimeMillis());
    }

    void update(){
        super.setTime(System.currentTimeMillis());
    }
    
    
    Position getMinuteHandPos(){
        int length = 180;
        
        int time = this.getMinutes();
        int x = 200 + (int) (length * Math.sin(Math.toRadians(time * 6)));
        int y = 200 - (int) (length * Math.cos(Math.toRadians(time * 6)));
        return new Position(x, y);
    }

    Position getHourHandPos(){
        int length = 100;

        int time = this.getHours() % 12;
        int x = 200 + (int) (length * Math.sin(Math.toRadians(time * 30)));
        int y = 200 - (int) (length * Math.cos(Math.toRadians(time * 30)));
        return new Position(x, y);
    }

    Position getSecondHandPos(){
        int length = 200;
        int time = this.getSeconds();
        
        int x = 200 + (int) (length * Math.sin(Math.toRadians(time * 6)));
        int y = 200 - (int) (length * Math.cos(Math.toRadians(time * 6)));
        return new Position(x, y);
    }

}


public class Clock extends Application {
    
    private Canvas can;
    private GraphicsContext gc;
    private Stage page;
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("clock.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 400, 400, Color.TRANSPARENT);


        page = new Stage(StageStyle.TRANSPARENT);

        page.setScene(scene);

        page.setTitle("Hello World");
        page.centerOnScreen();
        page.show();

        Circle dragger = new Circle(200, 200, 200);
        dragger.setFill(Color.WHITE);

        can = new Canvas(400, 400);
        gc = can.getGraphicsContext2D();
        root.getChildren().addAll(dragger, can);
        
        final currentTime best = new currentTime();

        can.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new AnimationTimer() {
                    
                    @Override
                    public void handle(long l) {
                        gc.clearRect(0, 0, 400, 400);

                        Position minutePos = best.getMinuteHandPos();
                        Position secondPos = best.getSecondHandPos();
                        Position hourPos = best.getHourHandPos();

                        for (int i = 0; i < 60; i++){

                            int length = 160;
                            double angle = Math.toRadians(i * 6);
                            
                            int len = 190;

                            if (i % 5 == 0){
                                int x = 200 + (int) (length * Math.sin(angle));
                                int y = 200 - (int) (length * Math.cos(angle));
                                
                                String toWrite = String.valueOf(i / 5);
                                if (i == 0) toWrite = "12";

                                gc.setFont(new Font(22));
                                gc.setStroke(Color.BLUE);
                                gc.strokeText( (toWrite.length() < 2) ? toWrite : toWrite + " ", x+1, y+1);

                                len = 170;

                            }

                            drawLine(200 + (int) (len * Math.sin(angle)), (200 - (int) (len * Math.cos(angle))), 200 + (int) (200 * Math.sin(angle)), (200 - (int) (200 * Math.cos(angle))), Color.RED);
                        }

                        drawLine(200, 200, minutePos.x, minutePos.y, Color.BLACK);
                        drawLine(200, 200, secondPos.x, secondPos.y, Color.BLUE);
                        drawLine(200, 200, hourPos.x, hourPos.y, Color.GREEN);
                        best.update();
                    }
                }.start();
            }
        });

        can.setFocusTraversable(true);

        can.setOnKeyPressed(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event) {

                if (event.getCode() == KeyCode.Q) {
                    page.close();
                    stage.close();
                }
            }
            
        });

    }
    void drawLine(int startx, int starty, int endx, int endy, Color paint){
        gc.setStroke(paint);
        gc.strokeLine(startx, starty, endx, endy);
    }

    public static void main(String[] args) {
        launch(args);
    } 

}
