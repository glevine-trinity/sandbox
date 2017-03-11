package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2

/** An enemy representation for a simple game based on sprites. Handles all 
 *  information regarding the enemy's position, movements, and abilities.
 *  
 *  @param pic the image representing the enemy
 *  @param initPos the initial position of the '''center''' of the enemy
 *  @param bulletPic the image of the bullets fired by this enemy
 */
class Enemy(pic:Image, var initPos:Vec2, bulletPic:Image) 
                  extends Sprite(pic, initPos) with ShootsBullets {
  val r = scala.util.Random
  /** creates a new Bullet instance beginning from this Enemy, with an appropriate velocity
   * 
   *  @return Bullet - the newly created Bullet object that was fired
   */
  def shoot():Bullet = {
    new Bullet(bulletPic,new Vec2(initPos.x+25,initPos.y+50),new Vec2(r.nextInt(5),3))
  }
  
  def shiftPath() {
    initPos.x = initPos.x+5
    initPos.y = initPos.y+0.5
  }
 
}