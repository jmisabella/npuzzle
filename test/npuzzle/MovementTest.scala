package models

import models.behaviors.{ Movement, Position }
import models.classes.npuzzle.{ Board, Up, Down, Left, Right, Move }

import org.scalatest.flatspec.AnyFlatSpec

class MovementTest extends AnyFlatSpec {

  private object pos extends Position
  private object movement extends Movement {
    override type _Position = Position
    override val position: Position = pos
  }

  "Movment module" should "get available moves from a 3x3 board" in {
    val size = 3 
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    assert(pos.position(goalState, None) == (2, 2), "position - from goal state of a 3x3 board, position of empty tile is (2, 2)")
    val availableMoves = movement.availableMoves(goalState)
    assert(availableMoves.contains(Up), "availableMoves - from goal state, Up is an available move")
    assert(availableMoves.contains(Left), "availableMoves - from goal state, Left is an available move")
    assert(!availableMoves.contains(Right), "availableMoves - from goal state, Right is not an available move")
    assert(!availableMoves.contains(Down), "availableMoves - from goal state, Down is not an available move")
    val legalMoves = movement.getMoves(goalState)
    assert(legalMoves.length == 2)
    assert(legalMoves.count(m => m.direction == Some(Up)) == 1, "getMoves - from goal state, Up is an available move")
    assert(legalMoves.count(m => m.direction == Some(Left)) == 1, "getMoves - from goal state, Left is an available move")
    assert(legalMoves.count(m => m.direction == Some(Down)) == 0, "getMoves - from goal state, Down is an available move")
    assert(legalMoves.count(m => m.direction == Some(Right)) == 0, "getMoves - from goal state, Right is an available move")
    val allMoves = movement.getAllMoves(goalState)
    assert(allMoves.length == 4, "getAllMoves should yield 4 results at all times")
    assert(allMoves.count(_.isLeft) == 2, "getAllMoves - from goal state, there should be 2 invalid moves")
    assert(allMoves.count(_.isRight) == 2, "getAllMoves - from goal state, there should be 2 valid moves")
    assert(allMoves.count(m => m.getOrElse(Move()).direction == Some(Up)) == 1, "getAllMoves - from goal state, there is 1 valid Up move")
    assert(allMoves.count(m => m.getOrElse(Move()).direction == Some(Left)) == 1, "getAllMoves - from goal state, there is 1 valid Left move")
    assert(allMoves.count(m => m.getOrElse(Move()).direction == Some(Down)) == 0, "getAllMoves - from goal state, there isn't a valid Down move")
    assert(allMoves.count(m => m.getOrElse(Move()).direction == Some(Right)) == 0, "getAllMoves - from goal state, there isn't a valid Right move")
  } 

  it should "make move in 3x3 board" in {
    val size = 3 
    val solution: Seq[Option[Int]] = (1 until size * size).map(Some(_)) ++ Seq(None)
    val goalState = Board(solution, size)
    assert(pos.position(goalState, None) == (2, 2), "position - from goal state of a 3x3 board, position of empty tile is (2, 2)")
    val upResult = movement.move(goalState, Up)
    val downResult = movement.move(goalState, Down)
    val leftResult = movement.move(goalState, Left)
    val rightResult = movement.move(goalState, Right)
    assert(upResult.isRight, "move - from goal state, Up is a valid move")
    assert(downResult.isLeft, "move - from goal state, Down is an invalid move")
    assert(leftResult.isRight, "move - from goal state, Right is an invalid move")
    assert(rightResult.isLeft, "move - from goal state, Left is a valid move")
  }

}