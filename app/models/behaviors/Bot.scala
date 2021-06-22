package models.behaviors

import models.classes.npuzzle.{ Board, Direction, Down, Left, Move, Right, State, Up }
import models.utilities.RNG

import scala.annotation.tailrec

trait Bot {
  type _Distance <: Distance
  type _Move <: Movement
  val distance: _Distance
  val moves: _Move

  def random(state: State, incrementMoveCount: Boolean = true): State = {
    val nextMoves = moves.getAllMoves(state.board)
    var (randomInt, nextSeed) = state.seed.boundedPositiveInt(Direction.count)
    var potentialMove: Either[String, models.classes.npuzzle.Move] = nextMoves(randomInt)

    while (potentialMove.isLeft) {
      randomInt = (randomInt + 1) % 4
      potentialMove = nextMoves(randomInt)
    }
    val move = potentialMove.getOrElse(models.classes.npuzzle.Move())
    val updatedMoves: Seq[Move] = incrementMoveCount match {
      case false => state.moves
      case true => state.moves ++ Seq(move)
    }
    state.copy(board = move.board, seed = nextSeed, moves = updatedMoves)
  }

  @tailrec
  final def nextMove(state: State): State = {
    val goal = Board(state.board.solution, state.board.length)
    if (state.remainingStepsToExitLocalOptima > 0) {
      val r = random(state)
      r.copy(remainingStepsToExitLocalOptima = state.remainingStepsToExitLocalOptima - 1, currentDistance = distance.distance(r.board, goal))
    } else {
      val nextMoves: Seq[(Int, Move)] = moves.getMoves(state.board).map { m =>
        val score = distance.distance(m.board, goal)
        (score, m)
      }
      if (nextMoves.count(_._1 < state.currentDistance) > 0) {
        var next: (Int, Move) = nextMoves.head
        for (m <- nextMoves) {
          if (m._1 < state.currentDistance) {
            next = m
          }
        }
        state.copy(board = next._2.board,
          currentDistance = next._1,
          moves = state.moves ++ Seq(next._2))
      } else {
        // none of the moves are better than current position... so we'll make N random moves to hopefully exit local optima
        nextMove(state.copy(remainingStepsToExitLocalOptima = state.board.length - 0))
      }
    }
  }

  def newGame(size: Int): State = {
    val maxMoves = size * size * size * size * size * size
    var state: State = State(size)
    var tries = 0
    while (tries < size * size * size) {
      for (_ <- 1 until size * size * size) {
        state = random(state, false)
        // state = random(state, true)
      }
      state = state.copy(initialBoard = Some(state.board))
      for (_ <- 0 to maxMoves) {
        if (!state.goalReached) {
          state = nextMove(state)
        }
      }

      if (state.goalReached) {
        return state
      } else {
        tries += 1
        state = State(Board(size))
      }
    }
    state
  }

}
