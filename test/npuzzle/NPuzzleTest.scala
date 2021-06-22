package models

import models.classes.npuzzle.{Board, Down, Left, Right, State, Up}
import models.behaviors.{ Movement, Position }
import models.modules.text.TextNPuzzleService._
import models.modules.text.PositionService._
import org.scalatest.flatspec.AnyFlatSpec
import models.utilities.RNG
import models.modules.NPuzzleService

class NPUzzleTest extends AnyFlatSpec {

  "TextNPuzzleService" should "find the goal of a 2x2 board" in {
    val size = 2
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    assert(checkPosition(goalState, None).isRight)
    val (x, y) = position(goalState, None)
    assert(x == size - 1)
    assert(y == size - 1)
  }

  it should "find the goal of a 3x3 board" in {
    val size = 3
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    assert(checkPosition(goalState, None).isRight)
    val (x, y) = position(goalState, None)
    assert(x == size - 1)
    assert(y == size - 1)
  }
 
  it should "find the goal of a 4x4 board" in {
    val size = 4
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    assert(checkPosition(goalState, None).isRight)
    val (x, y) = position(goalState, None)
    assert(x == size - 1)
    assert(y == size - 1)
  }

  it should "successfully move up from goal location on a 4x4 board" in {
    val size = 4
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    val nextState = moves.move(goalState, Up)
    assert(nextState.isRight)
  }

  it should "successfully move left from goal location on a 4x4 board" in {
    val size = 4
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    val nextState = moves.move(goalState, Left)
    assert(nextState.isRight)
  }

  it should "unsuccesfully move down from goal location on a 4x4 board" in {
    val size = 4
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    val nextState = moves.move(goalState, Down)
    assert(nextState.isLeft)
  }
  
  private object pos extends Position
  private object movement extends Movement {
    override type _Position = Position
    override val position: Position = pos
  }

  it should "unsuccessfully move right from goal location on a 4x4 board" in {
    val size = 4
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    val nextState = moves.move(goalState, Right)
    assert(nextState.isLeft)
  }

  it should "not make any illegal moves" in {
    val goalState = 
      Board(Seq(
          Seq(Some(1), Some(2), Some(3)), 
          Seq(Some(4), Some(5), Some(6)), 
          Seq(Some(7), Some(8), None)))  
    
   val initialBoard = 
      Board(Seq(
          Seq(None, Some(1), Some(8)), 
          Seq(Some(3), Some(2), Some(5)), 
          Seq(Some(7), Some(4), Some(6)))) 
    
    val seed = 555
    val initialState = State(initialBoard, 0, 0, Nil, Some(initialBoard), RNG.RandomSeed(seed))
    val s1 = nextMove(initialState)
    val s2 = nextMove(s1)
    val s3 = nextMove(s2)
    val s4 = nextMove(s3)
    val s5 = nextMove(s4)
    val s6 = nextMove(s5)
    val s7 = nextMove(s6)
    val s8 = nextMove(s7)

    // TODO: run tests
    // println("initial board \r\n" + initialState.initialBoard.getOrElse(""))
    // for (move <- s8.moves) {
    //   println("moves: " + movement.availableMoves(move.board))
    //   println("position: " + movement.position.checkPosition(move.board, None).getOrElse(""))
    //   println("selected move: " + move.direction)
    //   println() 
    //   println("------")
    //   println(move.board)
    // }

  }

}