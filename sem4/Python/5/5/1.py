import math
from hypothesis import given
from hypothesis.strategies import floats

# Определяем функцию distance
def distance(x1, y1, x2, y2):
    return math.sqrt((x2 - x1)**2 + (y2 - y1)**2)

# Определяем стратегии для входных данных
coordinates = floats(min_value=-1000, max_value=1000)

# Напишем тест, который будет проверять, что результат функции distance неотрицательный
@given(coordinates, coordinates, coordinates, coordinates)
def test_distance_non_negative(x1, y1, x2, y2):
    assert distance(x1, y1, x2, y2) >= 0

# Теперь напишем тест, который будет проверять, что расстояние между точками равно 0,
# если эти точки совпадают
@given(coordinates)
def test_distance_zero_when_points_coincide(x):
    assert distance(x, x, x, x) == 0

# Запустим тесты
test_distance_non_negative()
test_distance_zero_when_points_coincide()