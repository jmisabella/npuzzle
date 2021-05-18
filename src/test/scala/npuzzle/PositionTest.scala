package npuzzle

import org.junit.Test
import npuzzle.behaviors.Position
import npuzzle.classes.Board

class PositionTest {

  @Test
  def knowWhenValuesDoNotBelongToBoard_2x2(): Unit = {
    case object pos extends Position
    val size = 2
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    assert(pos.checkPosition(goalState, None).isRight, "checkPosition - in a 2x2 board, checking position of the empty tile is a valid operation")
    assert(pos.checkPosition(goalState, Some(1)).isRight, "checkPosition - in a 2x2 board, checking position of 1 is a valid operation")
    assert(pos.checkPosition(goalState, Some(2)).isRight, "checkPosition - in a 2x2 board, checking position of 2 is a valid operation")
    assert(pos.checkPosition(goalState, Some(3)).isRight, "checkPosition - in a 2x2 board, checking position of 3 is a valid operation")
    assert(pos.checkPosition(goalState, Some(4)).isLeft, "checkPosition - in a 2x2 board, checking position of 4 is an invalid operation")
    assert(pos.checkPosition(goalState, Some(5)).isLeft, "checkPosition - in a 2x2 board, checking position of 5 is an invalid operation")
    assert(pos.checkPosition(goalState, Some(6)).isLeft, "checkPosition - in a 2x2 board, checking position of 6 is an invalid operation")
    assert(pos.checkPosition(goalState, Some(7)).isLeft, "checkPosition - in a 2x2 board, checking position of 7 is an invalid operation")
    assert(pos.checkPosition(goalState, Some(8)).isLeft, "checkPosition - in a 2x2 board, checking position of 8 is an invalid operation")
    assert(pos.checkPosition(goalState, Some(9)).isLeft, "checkPosition - in a 2x2 board, checking position of 9 is an invalid operation")
    assert(pos.checkPosition(goalState, Some(0)).isLeft, "checkPosition - in a 2x2 board, checking position of 0 is an invalid operation")
    assert(pos.checkPosition(goalState, Some(-1)).isLeft, "checkPosition - in a 2x2 board, checking position of -1 is an invalid operation")
  }

  @Test
  def knowWhenValuesDoNotBelongToBoard_3x3(): Unit = {
    case object pos extends Position
    val size = 3
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    assert(pos.checkPosition(goalState, None).isRight, "checkPosition - in a 3x3 board, checking position of the empty tile is a valid operation")
    assert(pos.checkPosition(goalState, Some(1)).isRight, "checkPosition - in a 2x2 board, checking position of 1 is a valid operation")
    assert(pos.checkPosition(goalState, Some(2)).isRight, "checkPosition - in a 2x2 board, checking position of 2 is a valid operation")
    assert(pos.checkPosition(goalState, Some(3)).isRight, "checkPosition - in a 2x2 board, checking position of 3 is a valid operation")
    assert(pos.checkPosition(goalState, Some(4)).isRight, "checkPosition - in a 2x2 board, checking position of 4 is a valid operation")
    assert(pos.checkPosition(goalState, Some(5)).isRight, "checkPosition - in a 2x2 board, checking position of 5 is a valid operation")
    assert(pos.checkPosition(goalState, Some(6)).isRight, "checkPosition - in a 2x2 board, checking position of 6 is a valid operation")
    assert(pos.checkPosition(goalState, Some(7)).isRight, "checkPosition - in a 2x2 board, checking position of 7 is a valid operation")
    assert(pos.checkPosition(goalState, Some(8)).isRight, "checkPosition - in a 2x2 board, checking position of 8 is a valid operation")
    assert(pos.checkPosition(goalState, Some(9)).isLeft, "checkPosition - in a 2x2 board, checking position of 9 is an invalid operation")
    assert(pos.checkPosition(goalState, Some(0)).isLeft, "checkPosition - in a 2x2 board, checking position of 0 is an invalid operation")
    assert(pos.checkPosition(goalState, Some(-1)).isLeft, "checkPosition - in a 2x2 board, checking position of -1 is an invalid operation")
  }
  
  @Test
  def knowWhenValuesDoNotBelongToBoard_4x4(): Unit = {
    case object pos extends Position
    val size = 4
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    assert(pos.checkPosition(goalState, None).isRight, "checkPosition - in a 4x4 board, checking position of the empty tile is a valid operation")
    assert(pos.checkPosition(goalState, Some(1)).isRight, "checkPosition - in a 4x4 board, checking position of 1 is a valid operation")
    assert(pos.checkPosition(goalState, Some(2)).isRight, "checkPosition - in a 4x4 board, checking position of 2 is a valid operation")
    assert(pos.checkPosition(goalState, Some(3)).isRight, "checkPosition - in a 4x4 board, checking position of 3 is a valid operation")
    assert(pos.checkPosition(goalState, Some(4)).isRight, "checkPosition - in a 4x4 board, checking position of 4 is a valid operation")
    assert(pos.checkPosition(goalState, Some(5)).isRight, "checkPosition - in a 4x4 board, checking position of 5 is a valid operation")
    assert(pos.checkPosition(goalState, Some(6)).isRight, "checkPosition - in a 4x4 board, checking position of 6 is a valid operation")
    assert(pos.checkPosition(goalState, Some(7)).isRight, "checkPosition - in a 4x4 board, checking position of 7 is a valid operation")
    assert(pos.checkPosition(goalState, Some(8)).isRight, "checkPosition - in a 4x4 board, checking position of 8 is a valid operation")
    assert(pos.checkPosition(goalState, Some(9)).isRight, "checkPosition - in a 4x4 board, checking position of 9 is a valid operation")
    assert(pos.checkPosition(goalState, Some(10)).isRight, "checkPosition - in a 4x4 board, checking position of 10 is a valid operation")
    assert(pos.checkPosition(goalState, Some(11)).isRight, "checkPosition - in a 4x4 board, checking position of 11 is a valid operation")
    assert(pos.checkPosition(goalState, Some(12)).isRight, "checkPosition - in a 4x4 board, checking position of 12 is a valid operation")
    assert(pos.checkPosition(goalState, Some(13)).isRight, "checkPosition - in a 4x4 board, checking position of 13 is a valid operation")
    assert(pos.checkPosition(goalState, Some(14)).isRight, "checkPosition - in a 4x4 board, checking position of 14 is a valid operation")
    assert(pos.checkPosition(goalState, Some(15)).isRight, "checkPosition - in a 4x4 board, checking position of 15 is a valid operation")
    assert(pos.checkPosition(goalState, Some(16)).isLeft, "checkPosition - in a 4x4 board, checking position of 16 is an invalid operation")
    assert(pos.checkPosition(goalState, Some(0)).isLeft, "checkPosition - in a 4x4 board, checking position of 0 is an invalid operation")
    assert(pos.checkPosition(goalState, Some(-1)).isLeft, "checkPosition - in a 4x4 board, checking position of -1 is an invalid operation")
  }

  @Test
  def knowPosition_2x2(): Unit = {
    case object pos extends Position
    val size = 2
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    assert(pos.position(goalState, Some(1)) == (0, 0), "position - in 2x2 board, position of 1 is (0, 0)")
    assert(pos.position(goalState, Some(2)) == (1, 0), "position - in 2x2 board, position of 2 is (1, 0)")
    assert(pos.position(goalState, Some(3)) == (0, 1), "position - in 2x2 board, position of 3 is (0, 1)")
    assert(pos.position(goalState, None) == (1, 1), "position - in 2x2 board, position of blank tile is (1, 1)")
  }

  @Test
  def knowPosition_3x3(): Unit = {
    case object pos extends Position
    val size = 3
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    assert(pos.position(goalState, Some(1)) == (0, 0), "position - in 3x3 board, position of 1 is (0, 0)")
    assert(pos.position(goalState, Some(2)) == (1, 0), "position - in 3x3 board, position of 1 is (1, 0)")
    assert(pos.position(goalState, Some(3)) == (2, 0), "position - in 3x3 board, position of 1 is (2, 0)")
    assert(pos.position(goalState, Some(4)) == (0, 1), "position - in 3x3 board, position of 1 is (0, 1)")
    assert(pos.position(goalState, Some(5)) == (1, 1), "position - in 3x3 board, position of 1 is (1, 1)")
    assert(pos.position(goalState, Some(6)) == (2, 1), "position - in 3x3 board, position of 1 is (2, 1)")
    assert(pos.position(goalState, Some(7)) == (0, 2), "position - in 3x3 board, position of 1 is (0, 2)")
    assert(pos.position(goalState, Some(8)) == (1, 2), "position - in 3x3 board, position of 1 is (1, 2)")
    assert(pos.position(goalState, None) == (2, 2), "position - in 3x3 board, position of blank tile is (2, 2)")
  }

}
