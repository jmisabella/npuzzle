package npuzzle

import npuzzle.NPUzzleTest

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(classOf[Suite])
@Suite.SuiteClasses(Array(classOf[npuzzle.RNGTest], classOf[npuzzle.MovementTest], classOf[npuzzle.PositionTest], classOf[npuzzle.DistanceTest], classOf[npuzzle.NPUzzleTest]))
class NPuzzleTestSuite
