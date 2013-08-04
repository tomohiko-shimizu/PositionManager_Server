import scala.io.Source
import java.io.PrintWriter
object MapConverter{
  def main(args:Array[String]) = {
    val convertMap = Map(
      10 -> Point(1, 1),
      11 -> Point(1, 3),
      12 -> Point(1, 5),
       1 -> Point(2, 2),
       2 -> Point(2, 4),
       3 -> Point(2, 6),
      13 -> Point(3, 1),
      14 -> Point(3, 3),
      15 -> Point(3, 5),
       4 -> Point(4, 2),
       5 -> Point(4, 4),
       6 -> Point(4, 6),
      16 -> Point(5, 1),
      17 -> Point(5, 3),
      18 -> Point(5, 5),
       7 -> Point(6, 2),
       8 -> Point(6, 4),
       9 -> Point(6, 6)
    )
    val maxRow = convertMap.maxBy{_._2.row}._2.row
    val maxColumn = convertMap.maxBy{_._2.column}._2.column
    val csv = Source.fromFile("""map.csv""").getLines.toList.map{_.split(",")}.tail
    val positions = csv.map{line=>
      Position(line(1).toInt, line(0).toInt, line(2).toInt, line(3))
    }
    val writer = new PrintWriter("positions.csv")
    writer.println("row,column,id")
    val results = positions.flatMap{position =>
      convertMap.get(position.position).map{position -> _}
    }.map{case (position, basePoint) =>
      val row  = (position.row - 1) * position.row
      val column = (position.column - 1) * position.column
      new Point(basePoint.row + row, basePoint.column + column)
    }.zip(positions)
    results.map{case (point, position) =>
      CsvRow(point.row, point.column, position.id)
    }.groupBy{
     _.row
    }.toList.sortBy{case (rowNumber, pointList)=>
      rowNumber
    }.flatMap{case (rowNumber, pointList) =>
      pointList.sortBy{_.column}
    }.map{case CsvRow(row, column, id) =>
      List(row, column, id).mkString(",")
    }.foreach{writer.println}
    writer.close
    val logWriter = new PrintWriter("mapConvertLog")
    results.foreach{logWriter.println}
  }
}
case class CsvRow(row:Int, column:Int, id:String)
case class Point(row:Int, column:Int)
case class Position(row:Int, column:Int, position:Int, id:String)
