encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e ))

// This is basically a modification of P09.
def encodeDirect[A](ls: List[A]): List[(Int, A)] =
  if (ls.isEmpty) Nil
  else {
    val (packed, next) = ls span { _ == ls.head }
    (packed.length, packed.head) :: encodeDirect(next)
  }