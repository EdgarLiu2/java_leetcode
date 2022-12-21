package edgar.try_new.jdk15;

/**
 * Created by Edgar.Liu on 2022/12/21
 */
public class NewSealedClass {
}


abstract sealed class Shape
        permits Circle, Rectangle, Square {
}

final class Circle extends Shape {
}

sealed class Rectangle extends Shape
        permits TransparentRectangle, FilledRectangle {
}

final class TransparentRectangle extends Rectangle {
}

final class FilledRectangle extends Rectangle {
}

non-sealed class Square extends Shape {
}
