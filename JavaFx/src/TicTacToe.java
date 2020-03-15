import javafx.scene.control.Button;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Cell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TicTacToe extends Application {

	private char currentPlayer = 'X';
	private Cell[][] cell = new Cell[3][3];
	private Label statusMesssage = new Label("X must play");
	Stage yeet = new Stage();
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		GridPane pane = new GridPane();
		
		for(int i = 0; i < 3; i ++) {
			
			for(int j = 0; j <3; j++) {
				
				cell[i][j]= new Cell();
				pane.add(cell[i][j], j, i);
			}
		}
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setBottom(statusMesssage);
		Button button = new Button("Restart");
		borderPane.setTop(button);
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                restart();
            } 
        };
        button.setOnAction(event); 
		Scene scene = new Scene(borderPane,450,300);
		primaryStage.setTitle("Tic Tac Toe, JAVA FX");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void restart() {
		try {
			start(yeet);
			currentPlayer = 'X';
			statusMesssage.setText("Game has been resetted.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
	public boolean isBoardFull() {
		for(int i = 0; i < 3; i ++) {
			
			for(int j = 0; j <3; j++) {
				
				if(cell[i][j].getPlayer() ==' ') {
					return false;
				}
				
			}
		}
		return true;
	}
	public boolean hasWon(char Player) {
		for(int i = 0; i < 3; i ++) {
			
				if(cell[i][0].getPlayer() == Player && cell[i][1].getPlayer() == Player && cell[i][2].getPlayer() == Player ) {
					return true;
				
			}
		}
		for(int i = 0; i < 3; i ++) {
			
			if(cell[0][i].getPlayer() == Player && cell[1][i].getPlayer() == Player && cell[2][i].getPlayer() == Player ) {
				return true;
			
		}
	}
		if(cell[0][0].getPlayer() == Player && cell[1][1].getPlayer() == Player && cell[2][2].getPlayer() == Player ) {
			return true;
		}
		if(cell[0][2].getPlayer() == Player && cell[1][1].getPlayer() == Player && cell[2][0].getPlayer() == Player ) {
			return true;
		}
		if(cell[0][1].getPlayer() == Player && cell[1][1].getPlayer() == Player && cell[2][1].getPlayer() == Player ) {
			return true;
		}
		if(cell[0][2].getPlayer() == Player && cell[1][2].getPlayer() == Player && cell[2][2].getPlayer() == Player ) {
			return true;
		}
		return false;
	}
	
	public class Cell extends Pane{
		private char player = ' ';
		boolean restartGame = false;
		public Cell() {
			setStyle("-fx-border-color : black");
			this.setPrefSize(500,500);
			this.setOnMouseClicked(e -> handleClick());
		}
		
		public char getPlayer() {
			return player;
		}
		public void setPlayer(char c) {
			player = c;
			if(player =='X') {
				
				Line line1 = new Line(10,10, this.getWidth()-10, this.getHeight()-10);
				line1.endXProperty().bind(this.widthProperty().subtract(10));
				line1.endYProperty().bind(this.heightProperty().subtract(10));
				
				Line line2 = new Line(10,this.getHeight()-10, this.getWidth()-10, 10);
				
				line1.setStroke(Color.RED);
				line1.setStrokeWidth(10);
				line2.setStroke(Color.RED);
				line2.setStrokeWidth(10);
				getChildren().addAll(line1,line2);
			}
			else if(player == 'O') {
				Ellipse ellipse = new Ellipse(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2-10, this.getHeight()/2-10);
				ellipse.centerXProperty().bind(this.widthProperty().divide(2));
				ellipse.centerYProperty().bind(this.heightProperty().divide(2));
				ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
				ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
				ellipse.setStroke(Color.BLACK);
				ellipse.setFill(Color.BLUE);
				
				getChildren().add(ellipse);
//				Image image = new Image("o.jpg");
//				getChildren().add(new ImageView(image));
			}
		}
		private void handleClick() {
			// TODO Auto-generated method stub
			if(player == ' '&& currentPlayer != ' ' && restartGame == false) {
				
				setPlayer(currentPlayer);
				
				if(hasWon(currentPlayer)) {
					statusMesssage.setText("Congratualatoins, " + currentPlayer + " won the Game!");
					restartGame = true;
					currentPlayer = ' ';
				}
				else if(isBoardFull()) {
					statusMesssage.setText(" Draw!");
					currentPlayer = ' ';
				}
				else {
					currentPlayer = (currentPlayer =='X'? 'O': 'X');
					statusMesssage.setText(currentPlayer + " Must Play.");
				}
			}
		}
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
