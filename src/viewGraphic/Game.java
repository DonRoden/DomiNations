package viewGraphic;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.HalfDomino;
import model.Lagia;
import model.Model;
import model.Player;

public class Game extends Parent {
	public static final double height = 800;
	public static final double width = height+400;
	public static final double sizeKing = height/Model.nbKings;
	public static final double sizePlayer = height/Model.player.length;
	static Group root = new Group();
	static Scene scene = new Scene(root, width, height+100);
	static Text playerName;
	static Text infoToDo;
	static Text mainPoints = new Text();
	static VBox previousTurn;
	static VBox nextTurn;
	static VBox mainBoard;
	static VBox sidePanel;
	static BorderPane mainPane;
	static BorderPane topPanel;
	static Rectangle cadre = new Rectangle(sizeKing*2/4, sizeKing/4, Color.grayRgb(0, 0));
	

	public static Scene gameView() {
		root = new Group();
		scene = new Scene(root, width, height+100);
		cadre.setStroke(Color.BLACK);
		cadre.setStrokeWidth(3);
		mainPane = new BorderPane();
		
		topPanel = new BorderPane();
		mainPane.setTop(topPanel);
		playerName = new Text();
		
		HBox pioches = new HBox();
		topPanel.setLeft(pioches);
		
		previousTurn = new VBox();
		double size = sizeKing/4;
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
		pioches.getChildren().add(previousTurn);
		
		infoToDo = new Text();
		pioches.getChildren().add(infoToDo);
		infoToDo.setWrappingWidth(200);
		infoToDo.prefHeight(50);
		infoToDo.setFont(new Font(20));
		
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
		pioches.getChildren().add(nextTurn);
		pioches.setMargin(previousTurn, new Insets(5,5,5,5));
		pioches.setMargin(nextTurn, new Insets(5,5,5,5));
		pioches.setMargin(infoToDo, new Insets(100,50,30,30));
		pioches.setTranslateX(50);
		
		root.getChildren().add(mainPane);
		return scene;
	}
	
	public static GridPane showBoard(int idPlayer, double size) {
		GridPane grid = new GridPane();
		
		HalfDomino[][] board = Model.player[idPlayer].getBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				Group box = new Group();
				ImageView type = showCase(board[i][j], Model.player[idPlayer]);
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
		
		infoToDo.setText("<- Placez la premi�re partie du domino");
		
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
					infoToDo.setText("<- Placez la deuxi�me partie du domino");
					
					int column = GridPane.getColumnIndex((Node)e.getSource());
					int row = GridPane.getRowIndex((Node)e.getSource());
					int boardSize = Model.player[Model.order[nbOrder]].board.length;
					
					for (int i = 0; i < grid.getChildren().size(); i++) {
						if(i == (column+1)*boardSize+row
								|| i == (column-1)*boardSize+row
								|| i == (column)*boardSize+row+1
								|| i == (column)*boardSize+row-1) {
							grid.getChildren().get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
								public void handle(MouseEvent e) {
									int idPlayer = Model.order[nbOrder];
									if (Model.player[idPlayer].isPlacable(column, row, 
											GridPane.getColumnIndex((Node)e.getSource()), 
											GridPane.getRowIndex((Node)e.getSource()), 
											Model.chosenDomino[nbOrder])) {
										
										Model.player[idPlayer].placeDomino(column, row, 
											GridPane.getColumnIndex((Node)e.getSource()), 
											GridPane.getRowIndex((Node)e.getSource()), 
											Model.chosenDomino[nbOrder]);
										
										box.getChildren().remove(rec);
										for (Node gridCase : grid.getChildren()) {
											gridCase.setOnMouseClicked(null);
											gridCase.setOnMouseEntered(null);
										}
										
										change(grid, nbOrder);
										chooseDomino(nbOrder, Model.lastTurn);
									}
									else {
										rec.setOpacity(0.3);
										rec.setVisible(false);
										clickableBoard(grid, nbOrder);
									}
								}
							});
						}
						else if (i == (column)*boardSize+row) {
							grid.getChildren().get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
								public void handle(MouseEvent e) {
									rec.setOpacity(0.3);
									clickableBoard(grid, Model.order[nbOrder]);
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
	
	public static void change(GridPane old, int nbOrder) {
		double sizeCase;
		if (Model.bigDuel && Model.player.length == 2)
			sizeCase = 19;
		else
			sizeCase = 13;
		mainBoard.getChildren().set(2, Game.showBoard(Model.order[nbOrder], height/sizeCase));
	}
	
	public static void chooseDomino(int nbOrder, boolean lastTurn) {
		
		Model.player[Model.order[nbOrder]].scoreBoard(Model.player[Model.order[nbOrder]].board);
		mainPoints.setText("Points : "+Model.player[Model.order[nbOrder]].totalScore);
		
		if (lastTurn) {
			Group group = (Group)previousTurn.getChildren().get(nbOrder);
			group.getChildren().remove(cadre);
			if (nbOrder >= Model.order.length-1)
				Main.end();
			else
				Main.keepGoing(nbOrder);
		}
		else {
			if (Model.player[Model.order[nbOrder]].listPlacable(Model.chosenDomino[nbOrder]).isEmpty()) {
				infoToDo.setText("Vous ne pouvez pas placer ce domino.\n"
						+ "Choisissez un autre domino ->");
			}
			else
				infoToDo.setText("Choisissez un domino ->");
		
			for (Node box : nextTurn.getChildren()) {
				double size = sizeKing/4;
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
	//					(Model.newOrder[nextTurn.getChildren().indexOf(e.getSource())]);
						if(Model.newOrder[nextTurn.getChildren().indexOf(e.getSource())] == -1) {
							for (Node toRemove : nextTurn.getChildren()) {
								toRemove.setOnMouseEntered(null);
								toRemove.setOnMouseExited(null);
								toRemove.setOnMouseClicked(null);
								((Group)toRemove).getChildren().remove(rec);
							}
							Group group = (Group)previousTurn.getChildren().get(nbOrder);
							group.getChildren().remove(cadre);
							Model.newOrder[nextTurn.getChildren().indexOf(e.getSource())] = Model.order[nbOrder];
							
							Rectangle black = new Rectangle(size*2, size, Color.RED);
							black.setOpacity(0.5);
							
							((Group)box).getChildren().add(black);
							Main.keepGoing(nbOrder);
						}
					}
				});
			}
		}
		

	}
	
	public static void iaChooseDomino(int nbOrder) {

		
		double sizeCase;
		if (Model.bigDuel && Model.player.length == 2)
			sizeCase = 19;
		else
			sizeCase = 13;
		GridPane board = Game.showBoard(Model.order[nbOrder], height/sizeCase);
		mainBoard = new VBox();	
		playerName = new Text(Model.player[Model.order[nbOrder]].name);
		playerName.setTranslateX(75);
		playerName.setFill(Model.player[Model.order[nbOrder]].color);
		mainPoints.setText("Points : " + Model.player[Model.order[nbOrder]].totalScore);
		mainPoints.setTranslateX(75);
		mainBoard.getChildren().add(playerName);
		mainBoard.getChildren().add(mainPoints);
		mainBoard.getChildren().add(board);
		Group group = (Group)previousTurn.getChildren().get(nbOrder);
		cadre.setStroke(Model.player[Model.order[nbOrder]].color);
		group.getChildren().add(cadre);
		
		sidePanel = new VBox();
		for (int i = 0; i < Model.player.length; i++) {
			if (i != Model.order[nbOrder]) {
				Model.player[i].scoreBoard(Model.player[i].board);
				Text name = new Text(Model.player[i].name);
				name.setTranslateX(15);
				Text points = new Text("Points : "+Model.player[i].totalScore);
				points.setTranslateX(15);
				name.setFill(Model.player[i].color);
				sidePanel.getChildren().add(name);
				sidePanel.getChildren().add(points);
				GridPane side = Game.showBoard(i, sizePlayer/(sizeCase-4));
				sidePanel.getChildren().add(side);
			}
		}
		sidePanel.setTranslateY(-200);
		
		mainPane.setRight(sidePanel);
		mainPane.setCenter(mainBoard);
		
		if (!Model.lastTurn) {
			Lagia ia = Model.player[Model.order[nbOrder]].ia;
			double size = sizeKing/4;
			int a = ia.chooseDomino(Model.onBoardDominos);
			Model.newOrder[a] = Model.order[nbOrder];
			
			Node box = ((Node)nextTurn.getChildren().get(a));
			Rectangle black = new Rectangle(size*2, size, Color.RED);
			black.setOpacity(0.5);
			
			((Group)box).getChildren().add(black);
			
			infoToDo.setText(Model.player[Model.order[nbOrder]].name + " a jou�, cliquez ici pour continuer");
			
			infoToDo.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					Group group = (Group)previousTurn.getChildren().get(nbOrder);
					group.getChildren().remove(cadre);
					infoToDo.setOnMouseClicked(null);
					Main.keepGoing(nbOrder);
				}
			});
		}
		else {
			if (nbOrder >= Model.order.length-1) {
				infoToDo.setText(Model.player[Model.order[nbOrder]].name + " a jou�, cliquez ici pour continuer");
				
				infoToDo.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {
						Group group = (Group)previousTurn.getChildren().get(nbOrder);
						group.getChildren().remove(cadre);
						infoToDo.setOnMouseClicked(null);
						Main.end();
					}
				});
			}
			else {
				infoToDo.setText(Model.player[Model.order[nbOrder]].name + " a jou�, cliquez ici pour continuer");
				
				infoToDo.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {
						Group group = (Group)previousTurn.getChildren().get(nbOrder);
						group.getChildren().remove(cadre);
						infoToDo.setOnMouseClicked(null);
						Main.keepGoing(nbOrder);
					}
				});
			}
		}
	}
	
	public static void placeDomino(int nbOrder) {
		placeDomino(nbOrder, true, false);
	}
	
	public static void placeDomino(int nbOrder, boolean clickable) {
		placeDomino(nbOrder, clickable, false);
	}
	
	public static void placeDomino(int nbOrder, boolean clickable, boolean lastTurn) {

		
		double sizeCase;
		if (Model.bigDuel && Model.player.length == 2)
			sizeCase = 19;
		else
			sizeCase = 13;
		GridPane board = Game.showBoard(Model.order[nbOrder], height/sizeCase);
		mainBoard = new VBox();	
		playerName = new Text(Model.player[Model.order[nbOrder]].name);
		playerName.setTranslateX(75);
		playerName.setFill(Model.player[Model.order[nbOrder]].color);
		mainPoints.setText("Points : " + Model.player[Model.order[nbOrder]].totalScore);
		mainPoints.setTranslateX(75);
		mainBoard.getChildren().add(playerName);
		mainBoard.getChildren().add(mainPoints);
		
		
		if (Model.player[Model.order[nbOrder]].listPlacable(Model.chosenDomino[nbOrder]).isEmpty())
		{
			Group group = (Group)previousTurn.getChildren().get(nbOrder);
			cadre.setStroke(Model.player[Model.order[nbOrder]].color);
			group.getChildren().add(cadre);
			chooseDomino(nbOrder, lastTurn);
		}
		else if (!clickable)
			chooseDomino(nbOrder, lastTurn);
		else {
			Group group = (Group)previousTurn.getChildren().get(nbOrder);
			cadre.setStroke(Model.player[Model.order[nbOrder]].color);
			group.getChildren().add(cadre);
			clickableBoard(board, nbOrder);
		}
		
		Model.player[Model.order[nbOrder]].scoreBoard(Model.player[Model.order[nbOrder]].board);
		
		
		
		
		mainBoard.getChildren().add(board);
		
		sidePanel = new VBox();
		for (int i = 0; i < Model.player.length; i++) {
			if (i != Model.order[nbOrder]) {
				Model.player[i].scoreBoard(Model.player[i].board);
				Text name = new Text(Model.player[i].name);
				name.setTranslateX(15);
				Text points = new Text("Points : "+Model.player[i].totalScore);
				points.setTranslateX(15);
				name.setFill(Model.player[i].color);
				sidePanel.getChildren().add(name);
				sidePanel.getChildren().add(points);
				GridPane side = Game.showBoard(i, sizePlayer/(sizeCase-4));
				sidePanel.getChildren().add(side);
			}
		}
		sidePanel.setTranslateY(-200);
		
		mainPane.setRight(sidePanel);
		mainPane.setCenter(mainBoard);
		

	}
	
	public static void iaPlaceDomino(int nbOrder) {
		Lagia ia = Model.player[Model.order[nbOrder]].ia;
		ia.choosePosition(Model.chosenDomino[nbOrder]);
		if (ia.choosedPosition != null) {
		Model.player[Model.order[nbOrder]].placeDomino(ia.choosedPosition[0], 
				ia.choosedPosition[1],
				ia.choosedPosition[2],
				ia.choosedPosition[3],
				Model.chosenDomino[nbOrder]);
		}
		iaChooseDomino(nbOrder);
	}
	
	public static void newTurn(boolean notLastTurn) {

		
		double size = sizeKing/4;
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
		
		if (notLastTurn) {
			Model.draw();
			for (int i = 0; i < nextTurn.getChildren().size(); i++) {
				Group children = ((Group)nextTurn.getChildren().get(i));
				children.getChildren().remove(1, children.getChildren().size());
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
		else {
			nextTurn.setOpacity(0);
		}
	}
	
	static ImageView showCase(HalfDomino tile) {
		return showCase(tile, new Player(0,Color.WHITE));
	}
	
	static ImageView showCase(HalfDomino tile, Player player) {
		if (tile.getType().equals("Castle")) {
			if (player.color == Color.CORNFLOWERBLUE) {
				return new ImageView(new Image("img/chateaubleu.jpg"));
			}
			if (player.color == Color.HOTPINK) {
				return new ImageView(new Image("img/chateaurose.jpg"));
			}
			if (player.color == Color.SEAGREEN) {
				return new ImageView(new Image("img/chateauvert.jpg"));
			}
			if (player.color == Color.CHOCOLATE) {
				return new ImageView(new Image("img/chateauorange.jpg"));
			}
			else {
				return new ImageView(new Image("img/chateau.jpg"));
			}
			
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
			if (tile.getCrown() == 3)
				return new ImageView(new Image("img/montagne3.jpg"));
			else if (tile.getCrown() == 2)
				return new ImageView(new Image("img/montagne2.jpg"));
			else if (tile.getCrown() == 1)
				return new ImageView(new Image("img/montagne1.jpg"));
			else
				return new ImageView(new Image("img/montagne.jpg"));
		}
		else if (tile.getType().equals("Mine")) {
			if (tile.getCrown() == 2)
				return new ImageView(new Image("img/mine2.jpg"));
			else if (tile.getCrown() == 1)
				return new ImageView(new Image("img/mine1.jpg"));
			else
				return new ImageView(new Image("img/mine.jpg"));
		}
		else if (tile.getType().equals("X"))
		{
			return new ImageView();
		}
		else {
			return new ImageView(new Image("img/vide.jpg"));
		}
	}

}
