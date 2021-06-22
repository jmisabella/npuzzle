package models

import models.behaviors.{ Position, Distance }
import models.classes.npuzzle.Board
import org.scalatest.flatspec.AnyFlatSpec

class DistanceTest extends AnyFlatSpec {
    // 0 moves
    val goalState = 
      Board(Seq(
          Seq(Some(1), Some(2), Some(3)), 
          Seq(Some(4), Some(5), Some(6)), 
          Seq(Some(7), Some(8), None)))  
    // 1 move
    val movedUp = 
      Board(Seq(
          Seq(Some(1), Some(2), Some(3)), 
          Seq(Some(4), Some(5), None), 
          Seq(Some(7), Some(8), Some(6))))  
    // 1 move 
    val movedLeft = 
      Board(Seq(
          Seq(Some(1), Some(2), Some(3)), 
          Seq(Some(4), Some(5), Some(6)), 
          Seq(Some(7), None, Some(8))))  
    // 2 moves
    val movedLeftLeft = //
      Board(Seq(
          Seq(Some(1), Some(2), Some(3)), 
          Seq(Some(4), Some(5), Some(6)), 
          Seq(None, Some(7), Some(8))))  
    val movedLeftUp = //
      Board(Seq(
          Seq(Some(1), Some(2), Some(3)), 
          Seq(Some(4), None, Some(6)), 
          Seq(Some(7), Some(5), Some(8))))  
    // 3 moves 
    val movedLeftLeftUp = 
      Board(Seq(
          Seq(Some(1), Some(2), Some(3)), 
          Seq(None, Some(5), Some(6)), 
          Seq(Some(4), Some(7), Some(8))))  
    val movedLeftUpLeft = 
      Board(Seq(
          Seq(Some(1), Some(2), Some(3)), 
          Seq(None, Some(4), Some(6)), 
          Seq(Some(7), Some(5), Some(8))))  
    val movedLeftUpUp = 
      Board(Seq(
          Seq(Some(1), None, Some(3)), 
          Seq(Some(4), Some(2), Some(6)), 
          Seq(Some(7), Some(5), Some(8))))  
    val movedLeftUpRight = 
      Board(Seq(
          Seq(Some(1), Some(2), Some(3)), 
          Seq(Some(4), Some(6), None), 
          Seq(Some(7), Some(5), Some(8))))  
    // 2 moves 
    val movedUpUp = //
      Board(Seq(Seq(Some(1), Some(2), None), 
          Seq(Some(4), Some(5), Some(3)), 
          Seq(Some(7), Some(8), Some(6))))  
    // 3 moves
    val movedUpUpLeft = 
      Board(Seq(Seq(Some(1), None, Some(2)), 
          Seq(Some(4), Some(5), Some(3)), 
          Seq(Some(7), Some(8), Some(6))))  
    // 2 moves 
    val movedUpLeft = 
      Board(Seq(
          Seq(Some(1), Some(2), Some(3)), 
          Seq(Some(4), None, Some(5)), 
          Seq(Some(7), Some(8), Some(6))))  
    // 3 moves
    val movedUpLeftLeft = 
      Board(Seq(
          Seq(Some(1), Some(2), Some(3)), 
          Seq(None, Some(4), Some(5)), 
          Seq(Some(7), Some(8), Some(6))))  
    val movedUpLeftUp = 
      Board(Seq(
          Seq(Some(1), None, Some(3)), 
          Seq(Some(4), Some(2), Some(5)), 
          Seq(Some(7), Some(8), Some(6))))  
    val movedUpLeftDown = 
      Board(Seq(
          Seq(Some(1), Some(2), Some(3)), 
          Seq(Some(4), Some(8), Some(5)), 
          Seq(Some(7), None, Some(6))))  

  "Distance module" should "determine distance between 2 matching 3x3 boards to be 0" in {
    case object dist extends Distance {
      case object pos extends Position
      override type _Position = Position
      override val position: Position = pos 
    }
    val size = 3
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    val matchingState = goalState // exact copy
    assert(dist.distance(matchingState, goalState) == 0, "distance - distance of 2 matching states should be 0") 
  }

  it should "determine distance of one tile difference on 3x3 board to be 1" in {
    case object dist extends Distance {
      case object pos extends Position
      override type _Position = Position
      override val position: Position = pos 
    }
    
    assert(dist.distance(movedUp, goalState) == 1, "distance - moved Up - distance between goal and 1-off should be 1")
    assert(dist.distance(movedLeft, goalState) == 1, "distance - moved Left - distance between goal and 1-off should be 1")
  }

  it should "determine distance of a 2-tile difference on a 3x3 board to be 2" in {
    case object dist extends Distance {
      case object pos extends Position
      override type _Position = Position
      override val position: Position = pos 
    }

    assert(dist.distance(movedUpUp, goalState) == 2, "distance - moved Up,Up - distance between goal and 2-off should be 2")
    assert(dist.distance(movedUpLeft, goalState) == 2, "distance - moved Up,Left - distance between goal and 2-off should be 2")
    assert(dist.distance(movedLeftLeft, goalState) == 2, "distance - moved Left,Left - distance between goal and 2-off should be 2")
    assert(dist.distance(movedLeftUp, goalState) == 2, "distance - moved Left,Up - distance between goal and 2-off should be 2")
  }

  it should "determine distance of a 3-tile difference on a 3x3 board to be 3" in {
    case object dist extends Distance {
      case object pos extends Position
      override type _Position = Position
      override val position: Position = pos 
    }

    assert(dist.distance(movedUpUpLeft, goalState) == 3, "distance - moved Up,Up,Left - distance between goal and 3-off should be 3")
    assert(dist.distance(movedUpLeftLeft, goalState) == 3, "distance - moved Up,Left,Left - distance between goal and 3-off should be 3")
    assert(dist.distance(movedUpLeftUp, goalState) == 3, "distance - moved Up,Left,Up - distance between goal and 3-off should be 3")
    assert(dist.distance(movedUpLeftLeft, goalState) == 3, "distance - moved Up,Left,Left - distance between goal and 3-off should be 3")
    assert(dist.distance(movedLeftLeftUp, goalState) == 3, "distance - moved Left,Left,Up - distance between goal and 3-off should be 3")
    assert(dist.distance(movedLeftUpRight, goalState) == 3, "distance - moved Left,Up,Right - distance between goal and 3-off should be 3")
    assert(dist.distance(movedLeftUpLeft, goalState) == 3, "distance - moved Left,Up,Left - distance between goal and 3-off should be 3")
    assert(dist.distance(movedLeftUpUp, goalState) == 3, "distance - moved Left,Up,Up - distance between goal and 3-off should be 3")
  }

}