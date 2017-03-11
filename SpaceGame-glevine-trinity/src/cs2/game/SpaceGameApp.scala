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
import scala.collection.mutable.Buffer

/** main object that initiates the execution of the game, including construction
 *  of the window.
 *  Will create the stage, scene, and canvas to draw upon. Will likely contain or
 *  refer to an AnimationTimer to control the flow of the game.
 */
object SpaceGameApp extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Galaga-Game"
    scene = new Scene(1000,900) {
      val canvasWidth = 1000
      val canvasHeight = 900
      val canvas = new Canvas(canvasWidth,canvasHeight)
      val g = canvas.graphicsContext2D
      val playerImg = new Image("file:EvilHibbs.jpg",150,150,false,true)
      val enemyImg = new Image("file:EvilLewis.png",100,100,false,true)
      val playerBulletImg = new Image("file:HibbsBullet.png",50,50,true,true)
      val enemyBulletImg = new Image("file:LewisWeapon.jpg",50,50,false,true)
      
      val player = new Player(playerImg, new Vec2(425,750), playerBulletImg)
      var playerBulletBuffer = Buffer[Bullet]()
      var enemyBulletBuffer = Buffer[Bullet]()
      
      val swarmRows = 2
      val swarmCols = 5
      var enemiesList = List[Enemy]()
      for(r <- 0 until swarmRows) {
        for(c <- 0 until swarmCols) {
          enemiesList ::= new Enemy(enemyImg, new Vec2(c*175+100,r*150), enemyBulletImg)
        }
      }
      
      val enemySwarm = new EnemySwarm(swarmRows,swarmCols,enemiesList)
      
      val pressedKeys = scala.collection.mutable.Set[String]()
      
      onKeyPressed = (ke:KeyEvent) => {
        if(ke.code == KeyCode.Left) pressedKeys += "Left"
        if(ke.code == KeyCode.Right) pressedKeys += "Right"
        if(ke.code == KeyCode.Up) pressedKeys += "Up"
        if(ke.code == KeyCode.Down) pressedKeys += "Down"
        if(ke.code == KeyCode.Space) pressedKeys += "Space"
      }
      
      onKeyReleased = (ke:KeyEvent) => {
        if(ke.code == KeyCode.Left) pressedKeys -= "Left"
        if(ke.code == KeyCode.Right) pressedKeys -= "Right"
        if(ke.code == KeyCode.Up) pressedKeys -= "Up"
        if(ke.code == KeyCode.Down) pressedKeys -= "Down"  
        if(ke.code == KeyCode.Space) pressedKeys -= "Space"
      }
      
      content = canvas
      
      canvas.requestFocus()
      
      
      
      var priorTime:Long = 0
      var shootCountdown = 0
      val timer = AnimationTimer(time => {
        if(time - priorTime > 1e9/60) {
          priorTime = time
          g.fill = Color.Aquamarine
          g.fillRect(0,0, canvas.width.value, canvas.height.value)
          
          for(m <- playerBulletBuffer) {
            for(n <- enemyBulletBuffer) {
              if(m.intersect(n)) {
                playerBulletBuffer -= m
                enemyBulletBuffer -= n
              }
            }
          }
          
          
          
          playerBulletBuffer.foreach { bullet => bullet.timeStep()}
          playerBulletBuffer.foreach { bullet => bullet.display(g)}
          enemyBulletBuffer.foreach { bullet => bullet.timeStep()}
          enemyBulletBuffer.foreach { bullet => bullet.display(g)}
          
          if(pressedKeys("Right")) player.moveRight()
          if(pressedKeys("Left")) player.moveLeft()
          if(pressedKeys("Up")) player.moveUp()
          if(pressedKeys("Down")) player.moveDown()
          
          if(shootCountdown == 0) {
            if(pressedKeys("Space")) playerBulletBuffer += player.shoot()
            enemyBulletBuffer += enemySwarm.shoot()
            shootCountdown = 10
          } else { shootCountdown -= 1 }
          
          enemySwarm.shift()
          enemySwarm.display(g)
          player.display(g)
        }
      })
      timer.start
    }
  }
}