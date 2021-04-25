package property;

import Logic.GameLogic;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import square.PropertySquare;
import square.Square;

public class House extends Property {

	public House(boolean isOccupy, int level, PropertySquare squareBuild, String pictureURL) {
		// TODO Auto-generated constructor stub
		super(isOccupy,level,squareBuild);
		setRentArea(squareBuild.getPrice());
		setPictureURL(pictureURL);
		createImage();
		addImageToSquare((Square)getSquareBuild());
	}
	

	private void setRentArea(int price) {
		// TODO Auto-generated method stub
		super.setRent(calculateIncome());
	}


	@Override
	public int calculateIncome() {
		// TODO Auto-generated method stub
		int rent = 0;
		if (getLevel() == 1) {
			double rentRatio = 0.35;
			rent = (int) Math.round(rentRatio * getSquareBuild().getPrice());

		}
		return rent;
	}

	@Override
	public void createImage() {
		// TODO Auto-generated method stub
		Image charImage = new Image(getPictureURL());
		this.areaImage = new ImageView(charImage);
		this.areaImage.setFitHeight(100);
		this.areaImage.setFitWidth(100);
	}
	

	public ImageView getAreaImage() {
		return areaImage;
	}


	public void setAreaImage(ImageView areaImage) {
		this.areaImage = areaImage;
	}


	@Override
	public void addImageToSquare(Square currentSquare) {
		// TODO Auto-generated method stub
		ImageView imageViewProperty = getAreaImage();
		//ImageView imageViewCharPlayer2 = GameLogic.player2.getCharacterImage();
		currentSquare.add(imageViewProperty, 0, 1);
		//this.add(imageViewCharPlayer1,7, 7);
		//this.add(imageViewCharPlayer2,7, 7);
		
		currentSquare.setValignment(imageViewProperty, VPos.TOP);
		currentSquare.setHalignment(imageViewProperty, HPos.CENTER);
	}
	
	public void upgrade() {
		if (GameLogic.player1.isTurn()) {
			PropertySquare currentSq = (PropertySquare) GameLogic.player1.getCurrentSquare();
			currentSq.getProperty().areaImage.setImage(null);
			currentSq.setProperty(new House(true, 1, currentSq, "blueHotel.png"));
			GameLogic.player1.setMoney(GameLogic.player1.getMoney() - currentSq.getUpgradeCost());
			
		}
		else if (GameLogic.player2.isTurn()) {
			PropertySquare currentSq = (PropertySquare) GameLogic.player1.getCurrentSquare();
			currentSq.getProperty().areaImage.setImage(null);
			currentSq.setProperty(new House(true, 1, currentSq, "redHotel.png"));
			GameLogic.player1.setMoney(GameLogic.player1.getMoney() - currentSq.getUpgradeCost());
		}
	}
	
	public void removeImage() {
		this.areaImage = null;
	}
	
}