package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

/** A graphical sprite object used for gaming or other visual displays
 *  
 *  @constructor create a new sprite based on the given image and initial location
 *  @param img the image used to display this sprite
 *  @param pos the initial position of the '''center''' of the sprite in 2D space
 */
abstract class Sprite (protected val img:Image, protected var pos:Vec2) {

  /** moves the sprite a relative amount based on a specified vector
   *  
   *  @param direction - an offset that the position of the sprite should be moved by
   *  @return none/Unit
   */
  def move (direction:Vec2) { }
  
  /** moves the sprite to a specific location specified by a vector (not a relative movement)
   *  
   *  @param location - the new location for the sprite's position
   *  @return none/Unit
   */
  def moveTo (location:Vec2) { }
  
  /** Method to display the sprite at its current location in the specified Graphics2D context
   *  
   *  @param g - a GraphicsContext object capable of drawing the sprite
   *  @return none/Unit
   */
  def display (g:GraphicsContext) { 
    g.drawImage(img, pos.x,pos.y)
  }
  
  def intersect(spr1:Sprite):Boolean = { //This implementation assums this is stationary and spr1 is approaching
    val spr1Top = spr1.pos.y
    val spr1Bottom = spr1.pos.y + spr1.img.height.toInt
    val spr1Left = spr1.pos.x
    val spr1Right = spr1.pos.x + spr1.img.width.toInt
    val thisTop = this.pos.y
    val thisBottom = this.pos.y + this.img.height.toInt
    val thisLeft = this.pos.x
    val thisRight = this.pos.x + this.img.width.toInt
    
    if((spr1Top >= thisTop && spr1Top <= thisBottom) && (spr1Left >= thisLeft && spr1Left <= thisRight)) true
    else if((spr1Top >= thisTop && spr1Top <= thisBottom) && (spr1Right >= thisLeft && spr1Right <= thisRight)) true
    else if((spr1Bottom >= thisTop && spr1Bottom <= thisBottom) && (spr1Left >= thisLeft && spr1Left <= thisRight)) true
    else if((spr1Bottom >= thisTop && spr1Bottom <= thisBottom) && (spr1Right >= thisLeft && spr1Right <= thisRight)) true
    else false 
  }
}