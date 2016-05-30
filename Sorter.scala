
import scala.annotation.tailrec
import scala.collection.JavaConverters._
import java.io.PrintWriter

  object Sorter {
    def compare(a: Integer, b: Integer): Boolean = {
      if (a < b) true
      else false
    }
    // 5ая лаба
    final def mergeOfListSort(xs: List[Integer], compare: (Integer, Integer) => Boolean): List[Integer] = {
      @tailrec
      def merge(resultList: List[Integer], xs: List[Integer], ys: List[Integer]): List[Integer] = (xs, ys) match {
        case (_, Nil) => resultList.reverse ::: xs
        case (Nil, _) => resultList.reverse ::: ys
        case (x :: xs1, y :: ys1) =>
          if (compare(x, y)) merge(x :: resultList, xs1, ys)
          else merge(y :: resultList, xs, ys1)
      }
      val medium = xs.length / 2
      if (medium == 0) xs
      else {
        val (ys, zs) = xs splitAt medium
        merge(Nil, mergeOfListSort(ys, compare), mergeOfListSort(zs, compare))
      }
    }

    final def sortScores(list: java.util.List[Integer]) = {
      mergeOfListSort(list.asScala.toList, compare).asJava
    }
    // 6ая
    def numberOfCourseInCage(array: Array[Int]) = {
      (array.distinct.map(x => (array.count(_ == x), x))).foldLeft(0) {
        (i: Int, x: (Int, Int)) => println("Клетка №: " + x._2 + ". Количество ходов в клетку: " + x._1);
          i
      }
    }

    // 7ая
    def translateNotation(filePath : String, fileName : String ): Unit =
    {
      val fileText = io.Source.fromFile(filePath).mkString.split("\n")
      val path = "c:\\Users\\Dima\\IdeaProjects\\untitled1\\newGameNotation\\"
      val out = new PrintWriter( path + fileName )
      fileText.map(
        x => x match{
          case "user 0 1"=> out.print("ход сдедан пользователем в клетку 0 крестиком\n\r")
          case "user 1 1"=> out.print("ход сдедан пользователем в клетку 1 крестиком\n\r")
          case "user 2 1"=> out.print("ход сдедан пользователем в клетку 2 крестиком\n\r")
          case "user 3 1"=> out.print("ход сдедан пользователем в клетку 3 крестиком\n\r")
          case "user 4 1"=> out.print("ход сдедан пользователем в клетку 4 крестиком\n\r")
          case "user 5 1"=> out.print("ход сдедан пользователем в клетку 5 крестиком\n\r")
          case "user 6 1"=> out.print("ход сдедан пользователем в клетку 6 крестиком\n\r")
          case "user 7 1"=> out.print("ход сдедан пользователем в клетку 7 крестиком\n\r")
          case "user 8 1"=> out.print("ход сдедан пользователем в клетку 8 крестиком\n\r")

          case "user 0 2"=> out.print("ход сдедан пользователем в клетку 0 ноликом\n\r")
          case "user 1 2"=> out.print("ход сдедан пользователем в клетку 1 ноликом\n\r")
          case "user 2 2"=> out.print("ход сдедан пользователем в клетку 2 ноликом\n\r")
          case "user 3 2"=> out.print("ход сдедан пользователем в клетку 3 ноликом\n\r")
          case "user 4 2"=> out.print("ход сдедан пользователем в клетку 4 ноликом\n\r")
          case "user 5 2"=> out.print("ход сдедан пользователем в клетку 5 ноликом\n\r")
          case "user 6 2"=> out.print("ход сдедан пользователем в клетку 6 ноликом\n\r")
          case "user 7 2"=> out.print("ход сдедан пользователем в клетку 7 ноликом\n\r")
          case "user 8 2"=> out.print("ход сдедан пользователем в клетку 8 ноликом\n\r")

          case "comp 0 1"=> out.print("ход сдедан компьютером в клетку 0 крестиком\n\r")
          case "comp 1 1"=> out.print("ход сдедан компьютером в клетку 1 крестиком\n\r")
          case "comp 2 1"=> out.print("ход сдедан компьютером в клетку 2 крестиком\n\r")
          case "comp 3 1"=> out.print("ход сдедан компьютером в клетку 3 крестиком\n\r")
          case "comp 4 1"=> out.print("ход сдедан компьютером в клетку 4 крестиком\n\r")
          case "comp 5 1"=> out.print("ход сдедан компьютером в клетку 5 крестиком\n\r")
          case "comp 6 1"=> out.print("ход сдедан компьютером в клетку 6 крестиком\n\r")
          case "comp 7 1"=> out.print("ход сдедан компьютером в клетку 7 крестиком\n\r")
          case "comp 8 1"=> out.print("ход сдедан компьютером в клетку 8 крестиком\n\r")

          case "comp 0 2"=> out.print("ход сдедан компьютером в клетку 0 ноликом\n\r")
          case "comp 1 2"=> out.print("ход сдедан компьютером в клетку 1 ноликом\n\r")
          case "comp 2 2"=> out.print("ход сдедан компьютером в клетку 2 ноликом\n\r")
          case "comp 3 2"=> out.print("ход сдедан компьютером в клетку 3 ноликом\n\r")
          case "comp 4 2"=> out.print("ход сдедан компьютером в клетку 4 ноликом\n\r")
          case "comp 5 2"=> out.print("ход сдедан компьютером в клетку 5 ноликом\n\r")
          case "comp 6 2"=> out.print("ход сдедан компьютером в клетку 6 ноликом\n\r")
          case "comp 7 2"=> out.print("ход сдедан компьютером в клетку 7 ноликом\n\r")
          case "comp 8 2"=> out.print("ход сдедан компьютером в клетку 8 ноликом\n\r")

          case "end end end" => out.print ("игра окончена\n\r")
        case _ =>
      })
      out.close()
    }
  }


