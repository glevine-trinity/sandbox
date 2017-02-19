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
      val playerImg = new Image("file:EvilHibbs.jpg",200,200,false,true)
      val enemyImg = new Image("file:EvilLewis.png",150,150,false,true)
      val playerBulletImg = new Image("file:HibbsBullet.png",50,50,true,true)
      val enemyBulletImg = new Image("file:LewisWeapon.jpg",50,50,false,true)
      
      val player = new Player(playerImg, new Vec2(450,700), playerBulletImg)
      var playerBulletList = List[Bullet]()
      var enemyBulletList = List[Bullet]()
      var rightPressed, leftPressed, shootPressed = false
      val swarmRows = 2
      val swarmCols = 5
      var enemiesList = List[Enemy]()
      for(r <- 0 until swarmRows) {
        for(c <- 0 until swarmCols) {
          enemiesList ::= new Enemy(enemyImg, new Vec2(c*190+25,r*170), enemyBulletImg)
        }
      }
      
      val enemySwarm = new EnemySwarm(swarmRows,swarmCols,enemiesList)
      
      canvas.onKeyPressed = (ke:KeyEvent) => {
        ke.code match {
          case KeyCode.Right => rightPressed = true
          case KeyCode.Left => leftPressed = true
          case KeyCode.Space => shootPressed = true
          case _ =>
        }
      }
      
      canvas.onKeyReleased = (ke:KeyEvent) => {
        ke.code match {
          case KeyCode.Right => rightPressed = false
          case KeyCode.Left => leftPressed = false
          case KeyCode.Space => shootPressed = false
          case _ =>
        }
      }
      
      content = canvas
      g.fill = Color.Black
      g.fillRect(0,0,99,99)
      
      canvas.requestFocus()
      var priorTime:Long = 0
      val timer = AnimationTimer(time => {
        if(time - priorTime > 1e9/60) {
          priorTime = time
          g.fill = Color.White
          g.fillRect(0,0, canvas.width.value, canvas.height.value)
          playerBulletList.foreach { bullet => bullet.timeStep()}
          playerBulletList.foreach { bullet => bullet.display(g)}
          enemyBulletList.foreach { bullet => bullet.timeStep()}
          enemyBulletList.foreach { bullet => bullet.display(g)}
          if(rightPressed) player.moveRight()
          if(leftPressed) player.moveLeft()
          if(shootPressed) playerBulletList ::= player.shoot()
          enemySwarm.display(g)
          enemyBulletList ::= enemySwarm.shoot()
          player.display(g)
        }
      })
      timer.start
    }
  }
}