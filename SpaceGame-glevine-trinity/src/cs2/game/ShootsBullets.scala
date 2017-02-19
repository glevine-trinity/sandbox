package cs2.game

import scalafx.scene.image.Image

/** a trait representing the ability/requirement of an extending class to have 
 *  a shoot method that returns a newly created Bullet object
 */
trait ShootsBullets {
  val bulletImg = new Image("file:Bullet.png")
  /** an abstract method that must be overridden in order to instantiate an
   *  inheriting class
   * 
   *  @return Bullet - newly created Bullet object that is fired
   */
  def shoot():Bullet
  
}