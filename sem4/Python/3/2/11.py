from PIL import Image
import numpy as np
import matplotlib.pyplot as plt

# Загрузите вашу текстуру
texture = Image.open("C:\\3d\\texture.png")
texture = np.array(texture)

# Загрузите вашу карту высот
height_map = Image.open("C:\\3d\\heightmap.png")
height_map = np.array(height_map)

# Параметры камеры
x, y = 1000, 1000
screen_width = 640
z = 1000
horizon = 320  # Положение горизонта

# Создаем пустой массив для хранения изображения
image = np.zeros((screen_width, z, 3))

# Функция для рисования вертикальной линии
def draw_vertical_line(image, x, y, height, color):
    image[x, y:y+height] = color

# Сканирование начинается от наиболее удаленного от камеры слоя
for i in range(z-1, -1, -1):
    for j in range(screen_width):
        # Вычисляем координаты в текстуре и карте высот
        tx = x + j - screen_width // 2
        ty = y + i

        # Получаем цвет из текстуры и высоту из карты высот
        color = texture[ty % texture.shape[0], tx % texture.shape[1]]
        height = height_map[ty % height_map.shape[0], tx % height_map.shape[1]]

        # Размещаем столбец на изображении
        draw_vertical_line(image, j, horizon, height, color)

# Нормализация данных
image = image / np.max(image)

# Отображаем изображение
plt.imshow(image)
plt.show()