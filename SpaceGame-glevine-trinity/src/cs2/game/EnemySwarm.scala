package cs2.game

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.image.Image
import cs2.util.Vec2

/** contains the control and logic to present a coordinated set of Enemy objects.
 *  For now, this class generates a "grid" of enemy objects centered near the 
 *  top of the screen.
 *  
 *  @param nRows - number of rows of enemy objects
 *  @param nCols - number of columns of enemy objects
 */
class EnemySwarm(private val nRows:Int, private val nCols:Int, enemiesList:List[Enemy]) extends ShootsBullets {
	
  val r = scala.util.Random
	/** method to display all Enemy objects contained within this EnemySwarm
	 * 
	 *  @param g - the GraphicsContext to draw into
	 *  @return none/Unit
	 */
	def display(g:GraphicsContext) { 
	  for(r <- 0 until nRows) {
        for(c <- 0 until nCols) {
          enemiesList(r*5+c).display(g)
        }
      }
	}
  
  def shift() {
    for(n <- enemiesList) n.shiftPath()
  }
  
  /** overridden method of ShootsBullets. Creates a single, new bullet instance 
   *  originating from a random enemy in the swarm. (Not a bullet from every 
   *  object, just a single from a random enemy)
   *  
   *  @return Bullet - the newly created Bullet object fired from the swarm
   */
  def shoot():Bullet = { 
    enemiesList(r.nextInt(10)).shoot()
  }
}