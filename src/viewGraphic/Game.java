package viewGraphic;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.HalfDomino;
import model.Model;

public class Game extends Parent {
	public static final double height = 1000;
	public static final double width = 1900;
	static Group root = new Group();
	static Scene scene = new Scene(root, height+200, height);
	static Text playerName;
	static VBox previousTurn;
	static VBox nextTurn;
	static Group mainBoard;
	static VBox sidePanel;
	static BorderPane mainPane;
	static BorderPane topPanel;
	
	public static Scene gameView() {
		mainPane = new BorderPane();
		
		topPanel = new BorderPane();
		mainPane.setTop(topPanel);
		playerName = new Text();
		topPanel.setTop(playerName);
		
		
		previousTurn = new VBox();
		double size = height*1/3/4;
		for (int i = 0; i < Model.onBoardDominos.size(); i++) {
			Group box = new Group();
			HBox domino = new HBox();
			ImageView half1 = Game.showCase(Model.onBoardDominos.get(i).getHalf(0));
			half1.setFitHeight(size);
			half1.setFitWidth(size);
			ImageView half2 = Game.showCase(Model.onBoardDominos.get(i).getHalf(1));
			half2.setFitHeight(size);
			half2.setFitWidth(size);
			
			domino.getChildren().addAll(half1, half2);
			box.getChildren().add(domino);
			previousTurn.getChildren().add(box);
			Model.chosenDomino[i] = Model.onBoardDominos.get(i);
		}
		topPanel.setLeft(previousTurn);
		
		Model.draw();
		nextTurn = new VBox();
		for (int i = 0; i < Model.onBoardDominos.size(); i++) {
			Group box = new Group();
			HBox domino = new HBox();
			ImageView half1 = Game.showCase(Model.onBoardDominos.get(i).getHalf(0));
			half1.setFitHeight(size);
			half1.setFitWidth(size);
			ImageView half2 = Game.showCase(Model.onBoardDominos.get(i).getHalf(1));
			half2.setFitHeight(size);
			half2.setFitWidth(size);
			domino.getChildren().addAll(half1, half2);
			box.getChildren().add(domino);
			nextTurn.getChildren().add(box);
		}
		topPanel.setRight(nextTurn);
		
		root.getChildren().add(mainPane);
		return scene;
	}
	
	public static GridPane showBoard(int idPlayer, double size) {
		GridPane grid = new GridPane();
		
		HalfDomino[][] board = Model.player[idPlayer].getBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				Group box = new Group();
				ImageView type = showCase(board[i][j]);
				type.setFitWidth(size);
				type.setFitHeight(size);
				box.getChildren().add(type);
				grid.add(box, i, j);

				Rectangle rec = new Rectangle(size, size, Color.LIGHTGRAY);
				rec.setOpacity(0.3);
				rec.setVisible(false);
				box.getChildren().add(rec);
			}
		}
		
		return grid;
	}
	
	public static void clickableBoard(GridPane grid, int nbOrder) {
		for (int i = 0; i < grid.getChildren().size(); i++) {
				
			Group box = (Group)grid.getChildren().get(i);
			Rectangle rec = (Rectangle)box.getChildren().get(1);
			
			EventHandler<MouseEvent> entering = new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					rec.setVisible(true);
				}
			};
			
			box.setOnMouseEntered(entering);
			
			box.setOnMouseExited(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					rec.setVisible(false);
				}
			});
			
			box.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					int column = grid.getColumnIndex((Node)e.getSource());
					int row = grid.getRowIndex((Node)e.getSource());
					
					for (int i = 0; i < grid.getChildren().size(); i++) {
						if(i == (column+1)*11+row
								|| i == (column-1)*11+row
								|| i == (column)*11+row+1
								|| i == (column)*11+row-1) {
							grid.getChildren().get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
								public void handle(MouseEvent e) {
									int idPlayer = Model.order[nbOrder];
									if (Model.player[idPlayer].isPlacable(column, row, 
											grid.getColumnIndex((Node)e.getSource()), 
											grid.getRowIndex((Node)e.getSource()), 
											Model.chosenDomino[nbOrder])) {
										Model.player[idPlayer].placeDomino(column, row, 
											grid.getColumnIndex((Node)e.getSource()), 
											grid.getRowIndex((Node)e.getSource()), 
											Model.chosenDomino[nbOrder]);
										Group group = (Group)previousTurn.getChildren().get(nbOrder);
										group.getChildren().remove(group.getChildren().size()-1);
										box.getChildren().remove(rec);
										for (Node gridCase : grid.getChildren()) {
											gridCase.setOnMouseClicked(null);
											gridCase.setOnMouseEntered(null);
										}
										
										chooseDomino(nbOrder);
									}
									else {
										rec.setVisible(false);
										clickableBoard(grid, idPlayer);
									}
								}
							});
						}
						else {
							grid.getChildren().get(i).setOnMouseEntered(null);
							grid.getChildren().get(i).setOnMouseClicked(null);
						}
					}
					
					rec.setOpacity(0.6);
					box.setOnMouseExited(null);
				}
			});
		}
	}
	
	public static void chooseDomino(int nbOrder) {
		for (Node box : nextTurn.getChildren()) {
			double size = height*1/3/4;
			Rectangle rec = new Rectangle(size*2, size, Color.LIGHTGRAY);
			rec.setOpacity(0.3);
			rec.setVisible(false);
			((Group)box).getChildren().add(rec);
			
			box.setOnMouseEntered(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					rec.setVisible(true);
				}
			});
			
			box.setOnMouseExited(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					rec.setVisible(false);
				}
			});
			
			box.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					System.out.println(Model.newOrder[nextTurn.getChildren().indexOf(e.getSource())]);
					if(Model.newOrder[nextTurn.getChildren().indexOf(e.getSource())] == -1) {
						for (Node toRemove : nextTurn.getChildren()) {
							toRemove.setOnMouseEntered(null);
							toRemove.setOnMouseExited(null);
							toRemove.setOnMouseClicked(null);
							((Group)toRemove).getChildren().remove(1);
						}
						
						System.out.println(Model.order[nbOrder]);
						Model.newOrder[nextTurn.getChildren().indexOf(e.getSource())] = Model.order[nbOrder];
						Main.keepGoing(nbOrder);
						System.out.println("Good");
					}
				}
			});
		}
	}
	
	public static void placeDomino(int nbOrder) {
		playerName = new Text("Joueur "+(Model.order[nbOrder]+1));
		topPanel.setTop(playerName);
		
		Rectangle cadre = new Rectangle(height*2/3/4, height/3/4, Color.grayRgb(0, 0));
		cadre.setStroke(Color.BLACK);
		cadre.setStrokeWidth(3);
		Group group = (Group)previousTurn.getChildren().get(nbOrder);
		group.getChildren().add(cadre);
		
		mainBoard = new Group();	
		GridPane board = Game.showBoard(Model.order[nbOrder], height*2/3/11);
		clickableBoard(board, nbOrder);
		mainBoard.getChildren().add(board);
		
		sidePanel = new VBox();
		for (int i = 0; i < Model.player.length; i++) {
			if (i != Model.order[nbOrder]) {
				sidePanel.getChildren().add(new Text("Joueur "+(i+1)));
				GridPane side = Game.showBoard(i, height*2/6/11);
				sidePanel.getChildren().add(side);
			}
		}
		
		mainPane.setRight(sidePanel);
		mainPane.setCenter(mainBoard);
	}
	
	public static void newTurn() {
		double size = height*1/3/4;
		for (int i = 0; i < previousTurn.getChildren().size(); i++) {
			ImageView half1 = Game.showCase(Model.onBoardDominos.get(i).getHalf(0));
			half1.setFitHeight(size);
			half1.setFitWidth(size);
			ImageView half2 = Game.showCase(Model.onBoardDominos.get(i).getHalf(1));
			half2.setFitHeight(size);
			half2.setFitWidth(size);
			((HBox)((Group)previousTurn.getChildren().get(i)).getChildren().get(0)).getChildren().set(0, half1);
			((HBox)((Group)previousTurn.getChildren().get(i)).getChildren().get(0)).getChildren().set(1, half2);
			Model.chosenDomino[i] = Model.onBoardDominos.get(i);
		}
		
		Model.draw();
		for (int i = 0; i < nextTurn.getChildren().size(); i++) {
			ImageView half1 = Game.showCase(Model.onBoardDominos.get(i).getHalf(0));
			half1.setFitHeight(size);
			half1.setFitWidth(size);
			ImageView half2 = Game.showCase(Model.onBoardDominos.get(i).getHalf(1));
			half2.setFitHeight(size);
			half2.setFitWidth(size);
			((HBox)((Group)nextTurn.getChildren().get(i)).getChildren().get(0)).getChildren().set(0, half1);
			((HBox)((Group)nextTurn.getChildren().get(i)).getChildren().get(0)).getChildren().set(1, half2);
		}
	}
	
	static ImageView showCase(HalfDomino tile) {
		if (tile.getType().equals("Chateau")) {
			return new ImageView(new Image("img/chateau.jpg"));
		}
		else if (tile.getType().equals("Champs")) {
			if (tile.getCrown() == 1)
				return new ImageView(new Image("img/champs1.jpg"));
			else
				return new ImageView(new Image("img/champs.jpg"));
		}
		else if (tile.getType().equals("Foret")) {
			if (tile.getCrown() == 1)
				return new ImageView(new Image("img/foret1.jpg"));
			else
				return new ImageView(new Image("img/foret.jpg"));
		}
		else if (tile.getType().equals("Mer")) {
			if (tile.getCrown() == 1)
				return new ImageView(new Image("img/mer1.jpg"));
			else
				return new ImageView(new Image("img/mer.jpg"));
		}
		else if (tile.getType().equals("Prairie")) {
			if (tile.getCrown() == 2)
				return new ImageView(new Image("img/prairie2.jpg"));
			else if (tile.getCrown() == 1)
				return new ImageView(new Image("img/prairie1.jpg"));
			else
				return new ImageView(new Image("img/prairie.jpg"));
		}
		else if (tile.getType().equals("Montagne")) {
			if (tile.getCrown() == 2)
				return new ImageView(new Image("img/montagne2.jpg"));
			else if (tile.getCrown() == 1)
				return new ImageView(new Image("img/montagne1.jpg"));
			else
				return new ImageView(new Image("img/montagne.jpg"));
		}
		else if (tile.getType().equals("Mine")) {
			if (tile.getCrown() == 3)
				return new ImageView(new Image("img/mine3.jpg"));
			else if (tile.getCrown() == 2)
				return new ImageView(new Image("img/mine2.jpg"));
			else if (tile.getCrown() == 1)
				return new ImageView(new Image("img/mine1.jpg"));
			else
				return new ImageView(new Image("img/mine.jpg"));
		}
		else {
			return new ImageView(new Image("img/vide.jpg"));
		}
	}

}
