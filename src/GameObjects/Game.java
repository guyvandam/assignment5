package GameObjects;

import GameObjects.ObjectRemovers.BallRemover;
import GameObjects.ObjectRemovers.BlockRemover;
import GeometryShapes.Ball;
import GeometryShapes.Point;
import GeometryShapes.Rectangle;
import Interfaces.Collidable;
import Interfaces.HitListener;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-03-28.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks; // the number of blocks in the game.
    private Counter remainingBalls; // the number of balls in the game.
    private GUI gui;

    private int guiWidth;
    private int guiHeight;
    private int widthORHeight = 20;


    /**
     * constractur function.
     *
     * @param guiWidth  the GUI window width
     * @param guiHeight the GUI window height.
     */
    public Game(int guiWidth, int guiHeight) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.guiWidth = guiWidth;
        this.guiHeight = guiHeight;
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
    }

    /**
     * main method. creates a new GameObjects.Game object, initializes everything and runs the game.
     *
     * @param args the command line arguments, we have no use for them in this main method.
     */
    public static void main(String[] args) {
        Game game = new Game(800, 600);
        game.initialize();
        game.run();
    }

    /**
     * @return an integer. the GUI width.
     */
    public int getGuiWidth() {
        return guiWidth;
    }

    /**
     * @return an integer. the GUI height.
     */
    public int getGuiHeight() {
        return guiHeight;
    }

    /**
     * @return a GUI object.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * @param newGui a GUI object. the future GUI to be
     */
    public void setGui(GUI newGui) {
        this.gui = newGui;
    }

    /**
     * @return a spriteCollection object.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * @return the width/height of the corner block.
     */
    public int getWidthORHeight() {
        return widthORHeight;
    }

    /**
     * @return a GameObjects.GameEnvironment object.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * adds the input Interfaces.Collidable to the Interfaces.Collidable list of the GameObjects.GameEnvironment object.
     *
     * @param c a Interfaces.Collidable object.
     */
    public void addCollidable(Collidable c) {
        this.getEnvironment().addCollidable(c);
    }

    /**
     * adds the input Interfaces.Sprite to the Interfaces.Sprite list of the GameObjects.SpriteCollection object.
     *
     * @param s a Interfaces.Sprite object.
     */
    public void addSprite(Sprite s) {
        this.getSprites().addSprite(s);
    }

    public void removeCollidable(Collidable c) {
        this.getEnvironment().removeCollidable(c);
    }

    public void removeSprite(Sprite s) {
        this.getSprites().removeSprite(s);
    }

    /**
     * Initialize a new game: creates the Blocks, Balls (and GameObjects.Paddle) and adds them to the game.
     */
    public void initialize() {
        this.setGui(new GUI("title", getGuiWidth(), getGuiHeight()));

        BlockRemover blockRemover = new BlockRemover(this, this.getRemainingBlocks());
        BallRemover ballRemover = new BallRemover(this, this.getRemainingBalls());
        this.addBorderBlocks(ballRemover);
        this.addBalls();
        this.addPaddle();
        this.addBlocks(blockRemover);

    }

    /**
     * adds a blue rectangle to the screen to use as a blue background.
     *
     * @param d a DrawSurface. the surface we want to add the rectangle to.
     */
    public void addBackgroundColor(DrawSurface d) {
        if (d != null) {
            d.setColor(Color.blue);
            d.fillRectangle(0, 0, this.getGuiWidth(), this.getGuiHeight());
        }
    }

    /**
     * adds the colored blocks to the GameObjects.Game, with the same pattern of the example in the task page.
     */
    public void addBlocks(HitListener hitListener) {
        java.awt.Color[] colors = {Color.gray, Color.red, Color.yellow, Color.cyan, Color.pink, Color.green};
//        java.awt.Color[] colors = {Color.gray, Color.red, Color.yellow};

        int blockWidth = 50, blockHeight = 20;
        int numOfRows = colors.length, numOfColumns = 12;
        int startX = this.getGuiWidth() - numOfColumns * blockWidth - this.getWidthORHeight() - 1, startY = 100;
        int rowCounter = 0, columnCounter;
        while (rowCounter < numOfRows) {
            columnCounter = 0;
            while (columnCounter < numOfColumns) {
                Block temp = new Block(new Rectangle(new Point(startX, startY), blockWidth, blockHeight),
                        colors[rowCounter]);
                temp.addToGame(this);
                temp.addHitListener(hitListener);
                startX += blockWidth;
                columnCounter++;
            }
            this.remainingBlocks.increase(columnCounter);
            startY += blockHeight;
            numOfColumns--;
            startX = this.getGuiWidth() - numOfColumns * blockWidth - this.getWidthORHeight() - 1;
            rowCounter++;

        }
    }

    /**
     * adds the paddle to the game.
     */
    public void addPaddle() {
        int paddleWidth = 60, paddleHeight = 10, paddleX = 50, paddleY = this.getGuiHeight() - this.getWidthORHeight()
                - paddleHeight;
        java.awt.Color paddleColor = Color.ORANGE;

        Paddle paddle = new Paddle(this.getGui().getKeyboardSensor(), new Rectangle(new Point(paddleX, paddleY),
                paddleWidth, paddleHeight), paddleColor, this.getEnvironment());
        paddle.addToGame(this);
    }

    /**
     * adds 2 balls to the game.
     */
    public void addBalls() {
        int ballSize = 3, dx = 4, dy = 4, numOfBalls = 3;
        java.awt.Color ballColor = Color.BLACK;
        Point startPoint = new Point(this.getWidthORHeight() * 4, this.getWidthORHeight() *4);

        Ball[] balls = {new Ball(startPoint, ballSize, ballColor), new Ball(startPoint, ballSize, ballColor),
                new Ball(startPoint, ballSize, ballColor)};

        balls[0].setVelocity(dx,dy);
        balls[1].setVelocity(-dx,-dy);
        balls[2].setVelocity(dx,-dy);

        for(Ball b: balls){
            b.addToGame(this);
            b.setGameEnvironment(this.getEnvironment());
        }

        this.getRemainingBalls().increase(numOfBalls);


//        // randomly picked start points so they wont look uniform.
//        Ball b1 = new Ball(new Point(this.getWidthORHeight() * 2, this.getWidthORHeight() * 2), ballSize, ballColor,
//                new Velocity(dx, dy), this.getEnvironment());
//        Ball b2 = new Ball(new Point(this.getWidthORHeight() + this.getGuiWidth() / 3,
//                this.getWidthORHeight() * 3), ballSize,
//                ballColor, new Velocity(dx, dy), this.getEnvironment());
//
//        b1.addToGame(this);
//        b2.addToGame(this);
    }

    /**
     * adds the border blocks to the game.
     */
    public void addBorderBlocks(HitListener ballRemover) {
        java.awt.Color blockColor = Color.gray;
        Block upper = new Block(new Rectangle(new Point(0, 0), this.getGuiWidth(), this.getWidthORHeight()),
                blockColor);
        Block lower = new Block(new Rectangle(new Point(this.getWidthORHeight(), this.getGuiHeight()
                - this.getWidthORHeight()),
                this.getGuiWidth() - 2 * this.getWidthORHeight(), this.getWidthORHeight()), blockColor);

        lower.addHitListener(ballRemover); //turns the lower/bottom block into the 'death block'

        Block left = new Block(new Rectangle(new Point(0, this.getWidthORHeight()), this.getWidthORHeight(),
                this.getGuiWidth()
                        - 2 * this.getWidthORHeight()), blockColor);
        Block right = new Block(new Rectangle(new Point(this.getGuiWidth() - this.getWidthORHeight(),
                this.getWidthORHeight()),
                this.getWidthORHeight(), this.getGuiWidth() - 2 * this.getWidthORHeight()), blockColor);

        Block[] blocks = {upper, lower, right, left};
        for (Block b : blocks) {
            b.addToGame(this);
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;


        Sleeper sleeper = new Sleeper();

        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = this.getGui().getDrawSurface();
            this.addBackgroundColor(d);
            this.sprites.drawAllOn(d);
            this.getGui().show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

            if (this.getRemainingBlocks().getValue() == 0 || this.getRemainingBalls().getValue() == 0) {
                this.getGui().close();
                return;
            }
        }
    }
}
