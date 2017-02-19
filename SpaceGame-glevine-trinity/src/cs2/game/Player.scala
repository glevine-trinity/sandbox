package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

/** The player representation for a simple game based on sprites. Handles all 
 *  information regarding the player's positions, movements, and abilities.
 *  
 *  @param avatar the image representing the player
 *  @param initPos the initial position of the '''center''' of the player
 *  @param bulletPic the image of the bullets fired by this player
 */
class Player(avatar:Image, initPos:Vec2, bulletPic:Image) 
                extends Sprite(avatar, initPos) with ShootsBullets {

  /** moves the player sprite one "step" to the left.  The amount of this 
   *  movement will likely need to be tweaked in order for the movement to feel 
   *  natural.
   *  
   *  @return none/Unit
   */
  def moveLeft() { }
  
  /** moves the player sprite one "step" to the right (see note above)
   * 
   *  @return none/Unit
   */
  def moveRight() { 
    initPos.x = initPos.x+30
    println("SDF")
  }
  
  /** creates a new Bullet instance beginning from the player, with an 
   *  appropriate velocity
   * 
   *  @return Bullet - the newly created Bullet object that was fired
   */
  def shoot():Bullet = {new Bullet(avatar,initPos, initPos) }
  
  override def display(g:GraphicsContext) {
    g.drawImage(avatar, initPos.x,initPos.y)
    //println("Works")
    
  }
}