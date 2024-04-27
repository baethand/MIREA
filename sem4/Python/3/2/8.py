import numpy as np
import matplotlib.pyplot as plt
from skimage import io

# Загрузите исходное изображение
image = io.imread("C:\\3d\\ddccb51c95b3a2f9d68ed543ff4c792f.jpg")

# Получите размеры изображения
height, width, _ = image.shape

# Сгенерируйте N случайных точек на изображении
N = 1000  # Вы можете изменить N, чтобы получить разные уровни детализации вашей диаграммы Вороного
points = np.column_stack((np.random.randint(0, width, N), np.random.randint(0, height, N)))

# Создайте пустое изображение для диаграммы Вороного
voronoi_image = np.zeros((height, width, 3), dtype=np.uint8)

# Пройдите через каждую точку в изображении
for y in range(height):
    for x in range(width):
        # Найдите ближайшую точку
        distances = np.sum((points - np.array([x, y])) ** 2, axis=1)
        nearest_point = points[np.argmin(distances)]

        # Присвойте цвет ближайшей точки
        voronoi_image[y, x] = image[nearest_point[1], nearest_point[0]]

# Показать изображение
plt.imshow(voronoi_image)
plt.show()