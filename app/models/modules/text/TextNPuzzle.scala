package models.modules.text

import models.behaviors.{ Bot, Distance, Movement, Position, TextOutput }

object PositionService extends Position

private object MovementService extends Movement {
  override type _Position = Position
  override val position: Position = PositionService
}
private object DistanceService extends Distance {
  override type _Position = Position
  override val position: Position = PositionService
}

object TextNPuzzleService extends Bot with TextOutput {
  override type _Move = Movement
  override type _Distance = Distance
  override val moves: Movement = MovementService
  override val distance: Distance = DistanceService
}
