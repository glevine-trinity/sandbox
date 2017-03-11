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

  //The 900 and 1000 checks are the canvas boundaries
  def moveDown() { 
    if(initPos.y+150<900) initPos.y = initPos.y+15
  }
  def moveUp() { 
    if(initPos.y>0) initPos.y = initPos.y-15
  }
  def moveLeft() { 
    if(initPos.x>0) initPos.x = initPos.x-15
  }
  def moveRight() { 
    if(initPos.x+150<1000) initPos.x = initPos.x+15
  }
  
  /** creates a new Bullet instance beginning from the player, with an 
   *  appropriate velocity
   * 
   *  @return Bullet - the newly created Bullet object that was fired
   */
  def shoot():Bullet = {
    new Bullet(bulletPic,new Vec2(initPos.x+25,initPos.y-50),new Vec2(0,-20)) }
}