/**
 * FOREST PANEL CLASS
 * 
 * DESCRIPTION:
 * The ForestPanel class extends JPanel and is the layout that displays the
 * level map for where the student needs to get through. When levels are
 * passed, the level markers turn yellow and a star is added at the bottom
 * right of the screen. Collect five stars to win the game!
 *    
 * CODE SOURCES:
 * http://stackoverflow.com/questions/16190536/how-to-display-strings-in-
 * jtextarea-jtextfield
 * 
 * IMAGE SOURCES:
 * http://www.clker.com/cliparts/f/9/8/1/1216181106356570529jean_victor_
 * balin_icon_star.svg.hi.png
 */

package Panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import BooleanForest.Game;
import Objects.GameButton;
import Objects.Owl;
import Objects.Star;

@SuppressWarnings("serial")
public class ForestPanel extends JPanel implements Panel, MouseListener {
	// Declare static final members of ForestPanel class:
	private static final String BUTTON_TEXT = "Let's keep going!";	// text for Level 1 GameButton

	private static final String BACKGROUND_SRC = "/Images/Backgrounds/ForestBackground.jpg";	// background url
	private static final String BOB_SRC = "/Images/Owls/Bob.png";							// Bob url
	private static final String CHLOE_SRC = "/Images/Owls/ChloeSmall.png";					// Chloe url
	private static final String DAVID_SRC = "/Images/Owls/DavidSmall.png";					// David url
	private static final String YELLOW_BUTTON_SRC = "/Images/ButtonYellow.png";				// yellow button url
	private static final String STAR_SRC = "/Images/Star.png";								// star url

	private static final int FOREST_WIDTH = 350;			// width of ForestPanel BobsWindow
	private static final int FOREST_HEIGHT = 177;			// height of ForestPanel BobsWindow
	private static final int FOREST_XCOORD = 70;			// pre-determined xCoord of ForestPanel BobsWindow
	private static final int FOREST_YCOORD = 107;			// pre-determined yCoord of ForestPanel BobsWindow
	private static final int BOB_XCOORD = 418;				// pre-determined xCoord of ForestPanel Bob
	private static final int BOB_YCOORD = 268;				// pre-determined yCoord of ForestPanel Bob

	private static final int MARKER_1_XCOORD = 24;			// x-coordinate for marker 1
	private static final int MARKER_2_XCOORD = 157;			// x-coordinate for marker 2
	private static final int MARKER_3_XCOORD = 307;			// x-coordinate for marker 3
	private static final int MARKER_4_XCOORD = 437;			// x-coordinate for marker 4
	private static final int MARKER_5_XCOORD = 587;			// x-coordinate for marker 5	
	private static final int MARKER_1_YCOORD = 366;			// y-coordinate for marker 1
	private static final int MARKER_2_YCOORD = 290;			// y-coordinate for marker 2
	private static final int MARKER_3_YCOORD = 254;			// y-coordinate for marker 3
	private static final int MARKER_4_YCOORD = 178;			// y-coordinate for marker 4
	private static final int MARKER_5_YCOORD = 144;			// y-coordinate for marker 5

	// Declare members of ForestPanel class:
	private Game theGame;									// reference to main Game
	private String bobsMessage;								// Bob's message
	private JTextArea bobsTextArea;							// text area for Bob's message
	private GameButton forestGameButton1;					// button to continue to level 1
	private GameButton forestGameButton2;					// button to continue to level 2
	private GameButton forestGameButton3;					// button to continue to level 3
	private GameButton forestGameButton4;					// button to continue to level 4
	private GameButton forestGameButton5;					// button to continue to level 5
	private GameButton forestGameButton;					// button to continue to get certificate
	private GameButton backToIntro;							// button to go back to introPanel
	private Owl chloe;										// child Owl Chloe
	private Owl david;										// child Owl David
	private List<Star> stars;								// list of Stars

	/**
	 * CONSTRUCTOR: The constructor calls initPanel() method.
	 * @param game
	 */
	public ForestPanel(Game game) {
		initPanel(game);									// calls initPanel()
	}

	/**
	 * OVERRIDDEN METHOD: Initializes JPanel dimensions and members of
	 * ForestPanel class. Creates a reference to the Game object passed in
	 * and initializes Bob's Window.
	 * @param game
	 */
	@Override
	public void initPanel(Game game) {
		this.theGame = game;								// create reference to game passed in

		// Set the dimensions and layout of the JPanel.
		setPreferredSize(new Dimension(Game.APPLET_WIDTH, Game.APPLET_HEIGHT));
		setDoubleBuffered(true);
		setLayout(null);

		// Initialize children Owl objects.
		david = new Owl(65, 395, "DAVID");					// set the coordinates of David for level 1
		chloe = new Owl(80, 410, "CHLOE");					// set the coordinates of Chloe for level 1

		// Initialize Bob's message.
		bobsMessage = "Welcome! Help Chloe and David get through the Boolean Logic Forest. Get started by "
				+ "clicking the button below!";

		// Initialize ArrayList of Star objects and fill with Stars
		// for each level passed.
		stars = new ArrayList<Star>();
		stars.add(new Star("ONE"));							// add Star for passing Level 1
		stars.add(new Star("TWO"));							// add Star for passing Level 2
		stars.add(new Star("THREE"));						// add Star for passing Level 3
		stars.add(new Star("FOUR"));						// add Star for passing Level 4
		stars.add(new Star("FIVE"));						// add Star for passing Level 5

		repaint();											// repaint
	}

	/**
	 * OVERRIDDEN METHOD: Overrides paintComponent() by drawing the
	 * background image, children owls, the level markers and stars
	 * earned. Calls methods to add text and buttons.
	 * @param graphic
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);							// call super method

		// Create new image icon and draw the background image.
		Image background = new ImageIcon(getClass().getResource(BACKGROUND_SRC)).getImage();
		g.drawImage(background, 0, 0, null);

		// Draw Bob.
		Image bob = new ImageIcon(getClass().getResource(BOB_SRC)).getImage();
		g.drawImage(bob, BOB_XCOORD, BOB_YCOORD, null);

		// Paint various components on the screen.
		updateOwlCoords();									// update children owl coordinates
		paintOwl(g, david);									// paint David
		paintOwl(g, chloe);									// paint Chloe
		paintLevelMarkers(g, theGame.getLevel());			// paint yellow level markers for passed levels
		paintStars(g);										// paint a star for each level passed

		// Add text and button.
		addText();											// add text
		addButtons();										// add button
	}

	/**
	 * METHOD: Update the coordinates of the children owls
	 * to be at the next level to play.
	 * @param none
	 */
	private void updateOwlCoords() {
		int currentLevelPassed = theGame.getLevel();		// get the current level that has been passed

		switch (currentLevelPassed) {
		case 1:
			// Set Chloe and David's coordinates to be at level 2 marker.
			// All coordinates are pre-determined.
			chloe.setXCoord(220);
			chloe.setYCoord(325);
			david.setXCoord(199);
			david.setYCoord(308);
			break;
		case 2:
			// Set Chloe and David's coordinates to be at level 3 marker.
			// All coordinates are pre-determined.
			chloe.setXCoord(358);
			chloe.setYCoord(305);
			david.setXCoord(337);
			david.setYCoord(289);
			break;
		case 3:
			// Set Chloe and David's coordinates to be at level 4 marker.
			// All coordinates are pre-determined.
			chloe.setXCoord(489);
			chloe.setYCoord(222);
			david.setXCoord(468);
			david.setYCoord(206);
			break;
		case 4:
			// Set Chloe and David's coordinates to be at level 5 marker.
			// All coordinates are pre-determined.
			chloe.setXCoord(602);
			chloe.setYCoord(198);
			david.setXCoord(588);
			david.setYCoord(183);
			break;
		default:
			break;
		}
	}

	/**
	 * METHOD: Paints the Owl object passed in at it's x- and
	 * y-coordinates. Only applies to Chloe and David.
	 * @param g
	 * @param owl
	 */
	private void paintOwl(Graphics g, Owl owl) {
		Image owlImage;										// declare local Image to use to print owls

		// Check to see if Owl is Chloe or David and draw the Owl.
		if (owl.getOwlName() == "CHLOE") {
			owlImage = new ImageIcon(getClass().getResource(CHLOE_SRC)).getImage();
			g.drawImage(owlImage, owl.getXCoord(), owl.getYCoord(), null);
		}
		else if (owl.getOwlName() == "DAVID") {
			owlImage = new ImageIcon(getClass().getResource(DAVID_SRC)).getImage();
			g.drawImage(owlImage, owl.getXCoord(), owl.getYCoord(), null);
		}
	}

	/**
	 * OVERRIDDEN METHOD: This method adds Bob's message to text
	 * window.
	 * @param g
	 * NOTE: Source is listed at the beginning of the class file.
	 */
	@Override
	public void addText() {
		// If bobsTextArea is not null, remove it from the JPanel.
		if (bobsTextArea != null) {
			remove(bobsTextArea);							// remove from ForestPanel
		}

		addParagraphText();									// add paragraph
	}

	@Override
	public void addTitleText() {
		// TODO Auto-generated method stub
	}

	/**
	 * OVERRIDDEN METHOD: This method creates a JTextArea for the text
	 * that fits inside the white text box. A custom font and color are
	 * used for the text and the background is transparent. The text is
	 * wrapped and words will always appear in full on a line (no
	 * hyphenated words).
	 * @param none
	 */
	@Override
	public void addParagraphText() {
		int currentLevelPassed = theGame.getLevel();

		switch (currentLevelPassed) {
		case 1:
			bobsMessage = "Great! Now that you've learned TRUE and FALSE, let's learn about AND.";
			break;
		case 2:
			bobsMessage = "Amazing! You are smart AND awesome. Let's keep learning. Now we'll learn about OR.";
			break;
		case 3:
			bobsMessage = "Good job! The children are learning so much! Let's go on to NOT.";
			break;
		case 4:
			bobsMessage = "Almost there! Let's put it all together!";
			break;
		case 5:
			bobsMessage = "Click the button for a surprise!";
			break;
		default:
			break;
		}

		// Create a JTextArea to fit inside white text window with wrapped
		// text, custom font and transparent background and add to JPanel.
		bobsTextArea = new JTextArea();						// initialize the JTextArea
		bobsTextArea.setFont(Game.PARAGRAPH_FONT);			// set the font
		bobsTextArea.setForeground(Game.LIGHT_BLUE);		// set font color
		bobsTextArea.setLineWrap(true);						// set line wrap to true
		bobsTextArea.setWrapStyleWord(true);				// set words to appear in full on a line
		bobsTextArea.setOpaque(false);						// set background to transparent
		// Set the size and location of the text to have margin of 10 pixels
		// from the edge of the white background. 
		bobsTextArea.setSize(FOREST_WIDTH - 20, FOREST_HEIGHT - 20);
		bobsTextArea.setLocation(FOREST_XCOORD + 10, FOREST_YCOORD + 10);
		bobsTextArea.setText(bobsMessage);					// set the text
		add(bobsTextArea);									// add to ForestPanel
	}

	/**
	 * METHOD: This method adds the forestGameButton and adds a
	 * MouseListener to button.
	 * @param none
	 */
	@Override
	public void addButtons() {
		int currentLevel = theGame.getLevel();
		addForestButton(currentLevel);						// add forestButton
	}

	/**
	 * METHOD: This method adds the forestGameButton and adds a
	 * MouseListener.
	 * @param none
	 */
	private void addForestButton(int currentLevel) {
		// Initialize forestGameButton with the correct text.		
		forestGameButton = new GameButton(BUTTON_TEXT, "WHITE");	// set text to get certificate
		// Set the x- and y-coordinates and the button width and height.
		int boundsXCoord = FOREST_XCOORD + FOREST_WIDTH / 2 -
				forestGameButton.getPreferredSize().width / 2;
		int boundsYCoord = FOREST_YCOORD + FOREST_HEIGHT -
				forestGameButton.getPreferredSize().height - 20;
		int boundsWidth = forestGameButton.getPreferredSize().width;
		int boundsHeight = forestGameButton.getPreferredSize().height;
		forestGameButton.setBounds(boundsXCoord, boundsYCoord, boundsWidth, boundsHeight);

		add(forestGameButton);
		forestGameButton.setVisible(true);							// add to ForestPanel
		forestGameButton.addMouseListener(this);					// add MouseListener
	}

	/**
	 * METHOD: Paints yellow markers to indicate a passed level.
	 * @param g
	 * @param int
	 */
	private void paintLevelMarkers(Graphics g, int level) {		
		// Depending on the level of the game, draw the appropriate
		// number of yellow level markers (yellow indicates passed level).
		// Only case 1 will break. This way, for each level, it will
		// continue to draw the level markers for all previous passed
		// levels.
		Image yellowButton = new ImageIcon(getClass().getResource(YELLOW_BUTTON_SRC)).getImage();
		switch (theGame.getLevel()) {
		case 5:
			// Draw marker 5.
			g.drawImage(yellowButton, MARKER_5_XCOORD, MARKER_5_YCOORD, null);
		case 4:
			// Draw marker 4.
			g.drawImage(yellowButton, MARKER_4_XCOORD, MARKER_4_YCOORD, null);
		case 3:
			// Draw marker 3.
			//			g.drawImage(yellowButton, MARKER_3_XCOORD, MARKER_3_YCOORD, null);
		case 2:
			// Draw marker 2.
			g.drawImage(yellowButton, MARKER_2_XCOORD, MARKER_2_YCOORD, null);
		case 1:
			// Draw marker 1.
			g.drawImage(yellowButton, MARKER_1_XCOORD, MARKER_1_YCOORD, null);
			break;
		default:
			break;
		}
	}

	/**
	 * METHOD: Paints stars on bottom right of screen for each
	 * passed level.
	 * @param g
	 */
	private void paintStars(Graphics g) {
		// Go through the stars ArrayList and paint each star at their
		// x- and y- coordinates for each level passed.
		for (int index = 0; index < theGame.getLevel(); index++) {
			Image starImage = new ImageIcon(getClass().getResource(STAR_SRC)).getImage();
			g.drawImage(starImage, stars.get(index).getXCoord(), stars.get(index).getYCoord(), null);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * OVERRIDDEN METHOD: This method overrides mousePressed() by
	 * getting the source of the button pressed and switching to the
	 * corresponding panel.
	 * @param e
	 */  
	@Override
	public void mousePressed(MouseEvent e) {
		GameButton source = (GameButton) e.getSource();				// get source of component that was clicked

		// If button goes to Level 1, change to Level 1 layout.
		if (theGame.getLevel() == 0) {
			theGame.changeLayoutCard("LEVEL_ONE");				// switch to Level 1
		}
		// If button goes to Level 2, change to Level 2 layout.
		else if (theGame.getLevel() == 1) {
			theGame.changeLayoutCard("LEVEL_TWO");				// switch to Level 2
		}
		// If button goes to Level 3, change to Level 3 layout.
		else if (theGame.getLevel() == 2) {
			theGame.changeLayoutCard("LEVEL_THREE");			// switch to Level 3
			// switch to Level 3
		}
		// If button goes to Level 4, change to Level 4 layout.
		else if (theGame.getLevel() == 3) {
			theGame.changeLayoutCard("LEVEL_FOUR");				// switch to Level 4
		}
		// If button goes to Level 5, change to Level 5 layout.
		else if (theGame.getLevel() == 4) {
			theGame.changeLayoutCard("LEVEL_FIVE");				// switch to Level 5	
		}
		// If button goes to Certificate, change to CertificatePanel.
		else if (theGame.getLevel() == 5) {
			theGame.changeLayoutCard("CERTIFICATE");				// switch to CertificatePanel
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}