package cs2.game

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.canvas.Canvas
import scalafx.scene.Scene
import scalafx.scene.image.Image
import cs2.util.Vec2

/** main object that initiates the execution of the game, including construction
 *  of the window.
 *  Will create the stage, scene, and canvas to draw upon. Will likely contain or
 *  refer to an AnimationTimer to control the flow of the game.
 */
object SpaceGameApp extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Galaga-Game"
    scene = new Scene(800,600) {
      val canvas = new Canvas(800,600)
      val g = canvas.graphicsContext2D
      val playerImg = new Image("file:EvilHibbs.png")
      val enemyImg = new Image("file:EvilLewis.png")
      val bulletImg = new Image("file:Bullet.png")
      
      val player = new Player(playerImg, new Vec2(400,600), bulletImg)
    }
  }

}