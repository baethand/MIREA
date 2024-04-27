import numpy as np
import matplotlib.pyplot as plt
from scipy.spatial import Voronoi, voronoi_plot_2d
from skimage import io

# Загрузите исходное изображение
image = io.imread("C:\\3d\\ddccb51c95b3a2f9d68ed543ff4c792f.jpg")

# Получите размеры изображения
height, width, _ = image.shape

# Сгенерируйте N случайных точек на изображении
N = 10000  # Вы можете изменить N, чтобы получить разные уровни детализации вашей диаграммы Вороного
points = np.column_stack((np.random.randint(0, width, N), np.random.randint(0, height, N)))

# Создайте диаграмму Вороного из этих точек
vor = Voronoi(points)

# Построение - это создаст график, похожий на то, что показано в вашем примере.
fig, ax = plt.subplots()

for region in vor.regions:
    if not -1 in region and len(region) > 0:
        polygon = [vor.vertices[i] for i in region]
        # Проверьте, находятся ли координаты полигона в пределах изображения
        if all(0 <= x < width for x, _ in polygon) and all(0 <= y < height for _, y in polygon):
            ax.fill(*zip(*polygon), color=image[int(polygon[0][1]), int(polygon[0][0])]/255)  # Используйте цвет из исходного изображения

ax.set_xlim([0,width])
ax.set_ylim([0,height])
ax.invert_yaxis()  # Инвертируйте ось y, чтобы соответствовать системе координат изображения
ax.axis('off')  # Отключите оси для чистого отображения диаграммы Вороного

plt.show()