def distance(x1, y1, x2, y2):
    """
    Calculate the distance between two points in a two-dimensional plane.

    Args:
        x1 (float): X-coordinate of the first point.
        y1 (float): Y-coordinate of the first point.
        x2 (float): X-coordinate of the second point.
        y2 (float): Y-coordinate of the second point.

    Returns:
        float: The distance between the two points.

    Examples:
        >>> distance(0, 0, 3, 4)  # standard Pythagorean triple, 3-4-5 triangle
        5.0
        >>> distance(0, 0, 0, 0)  # distance between identical points
        0.0
        >>> distance(1, 2, 1, 2)  # distance between identical points with nonzero coordinates
        0.0
        >>> distance(-1, -1, 1, 1)  # distance between points on the origin's axes
        2.8284271247461903
    """
    dist = ((x2 - x1)**2 + (y2 - y1)**2) ** 0.5
    print(f"The distance between points ({x1}, {y1}) and ({x2}, {y2}) is {dist}")
    return dist

if __name__ == "__main__":
    import doctest
    doctest.testmod()



import doctest

def distance(x1, y1, x2, y2):
    """
    Эта функция вычисляет и возвращает евклидово расстояние между двумя точками (x1, y1) и (x2, y2).
    
    Args:
    x1, y1, x2, y2: Координаты двух точек.
    
    Returns:
    Евклидово расстояние между двумя точками.
    
    >>> distance(0, 0, 0, 0)
    0.0
    >>> distance(0, 0, 1, 1)
    1.4142135623730951
    >>> distance(-1, -1, 1, 1)
    2.8284271247461903
    """
    return ((x2 - x1)**2 + (y2 - y1)**2) ** 0.5

# Запуск модуля doctest для проверки тестов в docstring
doctest.testmod()

# Запись примеров doctest в отдельный файл
with open("distance_tests.txt", "w") as file:
    file.write("""
    >>> distance(0, 0, 0, 0)
    0.0
    >>> distance(0, 0, 1, 1)
    1.4142135623730951
    >>> distance(-1, -1, 1, 1)
    2.8284271247461903
    """)

# Запуск модуля doctest снова для проверки тестов в отдельном файле
doctest.testfile("distance_tests.txt")