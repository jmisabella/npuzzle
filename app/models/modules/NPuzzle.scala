package models.modules

import models.behaviors.{ Bot, Distance, Movement, Position }

object PositionService extends Position

private object MovementService extends Movement {
  override type _Position = Position
  override val position: Position = PositionService
}
private object DistanceService extends Distance {
  override type _Position = Position
  override val position: Position = PositionService
}

object NPuzzleService extends Bot {
  override type _Move = Movement
  override type _Distance = Distance
  override val moves: Movement = MovementService
  override val distance: Distance = DistanceService
}
