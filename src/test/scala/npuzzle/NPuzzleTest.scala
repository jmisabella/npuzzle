package npuzzle

import npuzzle.classes.{Board, Down, Left, Right, State, Up}
import npuzzle.modules.TextNPuzzleService._
import npuzzle.modules.PositionService._

import org.junit.Test

class NPUzzleTest {

  @Test
  def findGoalLocation_2x2(): Unit = {
    val size = 2
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    assert(checkPosition(goalState, None).isRight)
    val (x, y) = position(goalState, None)
    assert(x == size - 1)
    assert(y == size - 1)
  }

  @Test
  def findGoalLocation_3x3(): Unit = {
    val size = 3
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    assert(checkPosition(goalState, None).isRight)
    val (x, y) = position(goalState, None)
    assert(x == size - 1)
    assert(y == size - 1)
  }
  
  @Test
  def findGoalLocation_4x4(): Unit = {
    val size = 4
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    assert(checkPosition(goalState, None).isRight)
    val (x, y) = position(goalState, None)
    assert(x == size - 1)
    assert(y == size - 1)
  }

  @Test
  def successfullyMoveUpFromGoalLocation_4x4(): Unit = {
    val size = 4
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    val nextState = moves.move(goalState, Up)
    assert(nextState.isRight)
  }

  @Test
  def successfullyMoveLeftFromGoalLocation_4x4(): Unit = {
    val size = 4
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    val nextState = moves.move(goalState, Left)
    assert(nextState.isRight)
  }

  @Test
  def unsuccessfullyMoveDownFromGoalLocation_4x4(): Unit = {
    val size = 4
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    val nextState = moves.move(goalState, Down)
    assert(nextState.isLeft)
  }

  @Test
  def unsuccessfullyMoveRightFromGoalLocation_4x4(): Unit = {
    val size = 4
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    val nextState = moves.move(goalState, Right)
    assert(nextState.isLeft)
  }

    
}