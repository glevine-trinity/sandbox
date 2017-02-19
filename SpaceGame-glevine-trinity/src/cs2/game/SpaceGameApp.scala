package cs2.game

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.canvas.Canvas
import scalafx.scene.Scene
import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.animation.AnimationTimer
import scalafx.scene.paint.Color
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode

/** main object that initiates the execution of the game, including construction
 *  of the window.
 *  Will create the stage, scene, and canvas to draw upon. Will likely contain or
 *  refer to an AnimationTimer to control the flow of the game.
 */
object SpaceGameApp extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Galaga-Game"
    scene = new Scene(1000,900) {
      val canvas = new Canvas(1000,900)
      val g = canvas.graphicsContext2D
      val playerImg = new Image("file:EvilHibbs.jpg",100,100,false, true)
      val enemyImg = new Image("file:EvilLewis.png")
      val bulletImg = new Image("file:Bullet.png")
      
      val player = new Player(playerImg, new Vec2(450,800), bulletImg)
      var rightPressed, leftPressed = false
      
      canvas.onKeyPressed = (ke:KeyEvent) => {
        ke.code match {
          case KeyCode.Right => rightPressed = true
          case _ =>
        }
      }
      
      canvas.onKeyReleased = (ke:KeyEvent) => {
        ke.code match {
          case KeyCode.Right => rightPressed = false
          case _ =>
        }
      }
      
      content = canvas
      g.fill = Color.Black
      g.fillRect(0,0,99,99)
      
      canvas.requestFocus()
      var priorTime:Long = 0
      val timer = AnimationTimer(t => {
        if(t - priorTime > 1e9/60) {
          priorTime = t
          if(rightPressed) player.moveRight()
          player.display(g)
        }
      })
      timer.start
    }
  }
}