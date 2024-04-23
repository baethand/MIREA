import numpy as np
import matplotlib.pyplot as plt

def generate_symmetric_sprite(size=5):
    # Создаем матрицу размера size x size
    sprite = np.zeros((size, size), dtype=np.uint8)

    # Заполняем первую половину столбцов матрицы случайными пикселями
    sprite[:, :size//2] = np.random.randint(0, 2, (size, size//2))

    # Если размер матрицы нечетный, то заполняем центральный столбец
    if size % 2 == 1:
        sprite[:, size//2] = np.random.randint(0, 2, size)

    # Зеркально отражаем первую половину столбцов матрицы относительно вертикальной оси симметрии
    if size % 2 == 0:
        sprite[:, size//2:] = np.fliplr(sprite[:, :size//2])
    else:
        sprite[:, size//2+1:] = np.fliplr(sprite[:, :size//2])

    return sprite

# Генерируем 10 случайных спрайтов и выводим их с помощью функции imshow
for i in range(10):
    sprite = generate_symmetric_sprite()
    plt.imshow(sprite, cmap='gray')
    plt.show()
